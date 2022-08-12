package com.example.coffee_app.feature.coffeedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.coffee_app.R
import com.example.coffee_app.databinding.FragmentCoffeeDetailBinding
import com.example.coffee_app.model.Coffee


class CoffeeDetailFragment(val coffee: Coffee) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCoffeeDetailBinding.inflate(inflater, container, false)

        if (coffee.isFavorite) {
            binding.favouriteButton.setImageDrawable(ContextCompat.getDrawable(binding.favouriteButton.context,
                R.drawable.ic_baseline_star_24
            ))
        } else {
            binding.favouriteButton.setImageDrawable(ContextCompat.getDrawable(binding.favouriteButton.context,
                R.drawable.ic_baseline_star_outline_24
            ))
        }

        binding.coffeeNameText.text = coffee.name
        binding.coffeeDescriptionText.text = coffee.description
        binding.preparationText.text = coffee.preparation
        binding.difficultyRatingBar.rating = coffee.difficulty.toFloat()
        binding.hardnessRatingBar.rating = coffee.hardness.toFloat()

        Glide.with(this)
            .load(coffee.image)
            .into(binding.coffeeImage)

        return binding.root
    }


}