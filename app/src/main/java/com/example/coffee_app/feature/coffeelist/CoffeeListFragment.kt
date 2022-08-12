package com.example.coffee_app.feature.coffeelist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee_app.feature.MainActivity
import com.example.coffee_app.databinding.FragmentCoffeeListBinding
import com.example.coffee_app.model.Coffee
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Type

@AndroidEntryPoint
class CoffeeListFragment() : Fragment(), CoffeeClickListener {

    private lateinit var binding: FragmentCoffeeListBinding
    private lateinit var adapter: CoffeeAdapter
    private val viewModel: CoffeeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCoffeeListBinding.inflate(inflater,container,false)
        adapter = CoffeeAdapter(listOf(),this)
        viewModel.coffee.observe(viewLifecycleOwner ) {
            binding.coffeeRv.layoutManager = LinearLayoutManager(requireContext())

            val sharedPref = activity?.getSharedPreferences("favorite", Context.MODE_PRIVATE)
            val favoriteCoffee = sharedPref?.getString("favoriteIDs", null)

            val intListType: Type = object : TypeToken<List<Int>>() {}.type

            var favoriteCoffeeIDs: List<Int> = listOf()

            if(favoriteCoffee != null) {
                favoriteCoffeeIDs = Gson().fromJson(favoriteCoffee, intListType)
            }

            for (item in it) {
                if (favoriteCoffeeIDs.contains(item.id)) {
                    item.isFavorite = true
                }
            }

            adapter = CoffeeAdapter(it,this)
            binding.coffeeRv.adapter = adapter
        }
        return binding.root
    }

    override fun coffeeOnClick(coffee: Coffee) {
        (activity as MainActivity).goToCoffeeDetailFragment(coffee)
    }

    override fun changeFavorite(coffee: Coffee) {
        val preferences = activity?.getSharedPreferences("favorite", Context.MODE_PRIVATE)
        val editor = preferences?.edit()

        val currentFavoriteList = preferences?.getString("favoriteIDs", null)
        val intListType: Type = object : TypeToken<ArrayList<Int>>() {}.type

        var favoriteCoffeeIDs: ArrayList<Int> = arrayListOf()

        if(currentFavoriteList != null) {
            favoriteCoffeeIDs = Gson().fromJson(currentFavoriteList, intListType)
        }

        if (!coffee.isFavorite) {
            favoriteCoffeeIDs.add(coffee.id)
            coffee.isFavorite = true
        } else {
            favoriteCoffeeIDs.remove(coffee.id)
            coffee.isFavorite = false
        }


        val json = Gson().toJson(favoriteCoffeeIDs)

        editor?.putString("favoriteIDs", json)
        editor?.commit()
    }
}