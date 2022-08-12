package com.example.coffee_app.feature.coffeelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffee_app.model.Coffee
import com.example.coffee_app.repository.CoffeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeListViewModel @Inject constructor(private val repository: CoffeeRepository):
    ViewModel(){

    val _coffe = MutableLiveData<List<Coffee>>()

    val coffee: LiveData<List<Coffee>>
        get() = _coffe

    init {
        getAllCoffee()
    }

    private fun getAllCoffee() = viewModelScope.launch {
        repository.getCoffee().let { response ->
            if (response.isSuccessful) {
                _coffe.postValue(response.body())
            } else {
                Log.e("myLog","error")
            }
        }
    }


}