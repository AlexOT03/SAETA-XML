package com.example.saeta.API.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saeta.API.Route
import com.example.saeta.API.RouteApi
import com.example.saeta.API.Stop
import com.example.saeta.API.Trip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RouteViewModel : ViewModel() {
    private val _routes = MutableLiveData<List<Route>>()
    val routes: LiveData<List<Route>> get() = _routes
    private val _route = MutableLiveData<Route>()
    val route: LiveData<Route> get() = _route

    private val _goingRoute = MutableLiveData<Trip>()
    val goingRoute: LiveData<Trip> get() = _goingRoute

    private val _returnRoute = MutableLiveData<Trip>()
    val returnRoute: LiveData<Trip> get() = _returnRoute

    private val _goings = MutableLiveData<List<Stop>>()
    val goings: LiveData<List<Stop>> get() = _goings
    private val _returns = MutableLiveData<List<Stop>>()
    val returns: LiveData<List<Stop>> get() = _returns

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        fetchRoutes()
        fetchStops()
    }

    private fun fetchRoutes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RouteApi.apiService.getRoutes().execute()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _routes.postValue(it)
                        _error.postValue(null)
                    } ?: run {
                        _error.postValue("No data available")
                    }
                } else {
                    _error.postValue("Failed to fetch data")
                }
            } catch (e: HttpException) {
                _error.postValue("HTTP error: ${e.message}")
            } catch (e: IOException) {
                _error.postValue("Network error: ${e.message}")
            } catch (e: Exception) {
                _error.postValue("Unexpected error: ${e.message}")
            }
        }
    }
    private fun fetchStops() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RouteApi.apiService.getStops().execute()
                if (response.isSuccessful) {
                    response.body()?.let {stopsResponse ->
                        val goings = stopsResponse.flatMap { it.goings }
                        val returns = stopsResponse.flatMap { it.returns }
                        _goings.postValue(goings)
                        _returns.postValue(returns)
                        _error.postValue(null)
                    } ?: run {
                        _error.postValue("No data available")
                    }
                } else {
                    _error.postValue("Failed to fetch data")
                }
            } catch (e: HttpException) {
                _error.postValue("HTTP error: ${e.message}")
            } catch (e: IOException) {
                _error.postValue("Network error: ${e.message}")
            } catch (e: Exception) {
                _error.postValue("Unexpected error: ${e.message}")
            }
        }
    }
}