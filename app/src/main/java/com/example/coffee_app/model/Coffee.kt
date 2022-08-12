package com.example.coffee_app.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coffee(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("description")
    @Expose
    var description: String,

    @SerializedName("difficulty")
    @Expose
    var difficulty: Double,

    @SerializedName("hardness")
    @Expose
    var hardness: Double,

    @SerializedName("preparation")
    @Expose
    var preparation: String,

    @SerializedName("image")
    @Expose
    var image: String,

    @SerializedName("isFavorite")
    @Expose
    var isFavorite: Boolean
)






