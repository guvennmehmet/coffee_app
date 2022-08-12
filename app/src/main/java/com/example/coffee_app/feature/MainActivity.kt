package com.example.coffee_app.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coffee_app.R
import com.example.coffee_app.ViewModel.MainActivityViewModel
import com.example.coffee_app.feature.coffeelist.CoffeeAdapter
import com.example.coffee_app.databinding.ActivityMainBinding
import com.example.coffee_app.feature.coffeedetail.CoffeeDetailFragment
import com.example.coffee_app.feature.coffeelist.CoffeeListFragment
import com.example.coffee_app.model.Coffee
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CoffeeAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goToCoffeeListFragment()
    }
    private fun goToCoffeeListFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, CoffeeListFragment())
            .commit()
    }

    fun goToCoffeeDetailFragment(coffee: Coffee) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, CoffeeDetailFragment(coffee))
            .addToBackStack("detail")
            .commit()
    }
}