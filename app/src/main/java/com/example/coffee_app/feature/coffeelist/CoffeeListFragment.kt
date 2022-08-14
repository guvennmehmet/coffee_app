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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Type
import kotlin.system.exitProcess

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

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getAllCoffee()
            binding.swipeRefresh.isRefreshing = false
        }

        adapter = CoffeeAdapter(listOf(),this)
        viewModel.coffeeLiveData.observe(viewLifecycleOwner ) {
            binding.coffeeRv.layoutManager = LinearLayoutManager(requireContext())
            adapter = CoffeeAdapter(it,this)
            binding.coffeeRv.adapter = adapter

            binding.loading.visibility = View.GONE
            if (it.isEmpty()) {
                binding.noInternet.visibility = View.VISIBLE
                binding.coffeeRv.visibility = View.GONE
            } else {
                binding.noInternet.visibility = View.GONE
                binding.coffeeRv.visibility = View.VISIBLE
            }
        }

        viewModel.isOnline.observe(viewLifecycleOwner) {
            if(!it) {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage("İnternet yok.")
                    .setNegativeButton("TEKRAR DENE") { _, _ ->
                        viewModel.getAllCoffee()
                    }
                    .setPositiveButton("ÇIKIŞ YAP") {_, _ ->
                        exitProcess(0)
                    }
                    .show()
            }
        }

        return binding.root
    }

    override fun coffeeOnClick(coffee: Coffee) {
        (activity as MainActivity).goToCoffeeDetailFragment(coffee)
    }

    override fun changeFavorite(coffee: Coffee) {
        viewModel.changeFavorite(coffee)
    }
}