package com.example.coffee_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Coffee(

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id :Int,

    @ColumnInfo
    @SerializedName("name")
    @Expose
    var name: String,

    @ColumnInfo
    @SerializedName("description")
    @Expose
    var description: String,

    @ColumnInfo
    @SerializedName("difficulty")
    @Expose
    var difficulty: Double,

    @ColumnInfo
    @SerializedName("hardness")
    @Expose
    var hardness: Double,

    @ColumnInfo
    @SerializedName("preparation")
    @Expose
    var preparation: String,

    @ColumnInfo
    @SerializedName("image")
    @Expose
    var image: String,

    @ColumnInfo
    @SerializedName("isFavorite")
    @Expose
    var isFavorite: Boolean
)






