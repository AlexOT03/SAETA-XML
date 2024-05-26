package com.example.saeta.API.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saeta.API.Route
import com.example.saeta.API.RouteApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RouteViewModel : ViewModel() {
    private val _routes = MutableLiveData<List<Route>>()
    val routes: LiveData<List<Route>> get() = _routes

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        fetchRoutes()
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
}