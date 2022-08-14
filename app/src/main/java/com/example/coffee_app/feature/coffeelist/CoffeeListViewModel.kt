package com.example.coffee_app.feature.coffeelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffee_app.db.CoffeeDatabase
import com.example.coffee_app.model.Coffee
import com.example.coffee_app.repository.CoffeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CoffeeListViewModel @Inject constructor(
    private val repository: CoffeeRepository,
    private val db: CoffeeDatabase.AppDatabase,
    ): ViewModel(){

    val coffeeLiveData = MutableLiveData<List<Coffee>>()
    val isOnline = MutableLiveData<Boolean>()

    init {
        getAllCoffee()
    }

    fun getAllCoffee() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getCoffee().body()?.let {
                    db.coffeeDao().insertList(it)
                }
                isOnline.postValue(true)
            } catch (e: IOException) {
                isOnline.postValue(false)
            }

            coffeeLiveData.postValue(db.coffeeDao().getAll())
        }
    }

    fun changeFavorite(coffee: Coffee) {
        coffee.isFavorite = !coffee.isFavorite

        viewModelScope.launch(Dispatchers.IO) {
            db.coffeeDao().update(coffee)
        }
    }

}
