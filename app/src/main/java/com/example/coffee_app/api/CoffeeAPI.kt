package com.example.coffee_app.api

import com.example.coffee_app.model.Coffee
import retrofit2.Response
import retrofit2.http.GET

interface CoffeeAPI {
    @GET("EVO1")
    suspend fun  getAllCoffee(): Response<List<Coffee>>
}