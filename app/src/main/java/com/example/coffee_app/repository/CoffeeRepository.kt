package com.example.coffee_app.repository

import com.example.coffee_app.api.CoffeeAPI
import javax.inject.Inject

class CoffeeRepository @Inject constructor(private val api:CoffeeAPI) {

    suspend fun getCoffee() = api.getAllCoffee()

}