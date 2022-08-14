package com.example.coffee_app.db

import android.content.Context
import android.os.CpuUsageInfo
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.coffee_app.model.Coffee

class CoffeeDatabase  {

    @Dao
    interface  CoffeeDao{
        @Query("SELECT * FROM coffee")
        fun getAll(): List<Coffee>

        @Insert
        fun insert(coffee: Coffee)

        @Insert(onConflict = IGNORE)
        fun insertList(coffee: List<Coffee>)

        @Delete
        fun delete(coffee: Coffee)

        @Update
        fun update(coffee: Coffee)
    }

    @Database(entities = [Coffee::class], version = 1)
    abstract class AppDatabase : RoomDatabase(){
        abstract fun  coffeeDao(): CoffeeDao
    }

}











