package com.example.saeta.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("routes")
    fun getRoutes(): Call<List<Route>>
    @GET("Routes/{id}")
    fun getRouteById(@Path("id") id: Int): Call<Route>
    @GET("Routes/Going/{routeID}/{goingID}")
    fun getGoingRoute(@Path("routeID") routeID: Int, @Path("goingID") goingID: Int): Call<Trip>
    @GET("Routes/Return/{routeID}/{returnID}")
    fun getReturnRoute(@Path("routeID") routeID: Int, @Path("returnID") returnID: Int): Call<Trip>
    @GET("Routes/Goings/{routeID}")
    fun getGoings( @Path("routeID") routeID: Int): Call<List<Stop>>
    @GET("Routes/Returns/{routeID}")
    fun getReturns(@Path("routeID") routeID: Int): Call<List<Stop>>
}