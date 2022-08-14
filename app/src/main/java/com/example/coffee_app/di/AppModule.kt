package com.example.coffee_app.di

import android.content.Context
import androidx.room.Room
import com.example.coffee_app.api.CoffeeAPI
import com.example.coffee_app.cons.Cons.BASE_URL
import com.example.coffee_app.db.CoffeeDatabase
import com.example.coffee_app.model.Coffee
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit): CoffeeAPI {
        return retrofit.create(CoffeeAPI::class.java)
    }

    @Provides
    fun getRetrofitInstance():Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRoom(@ApplicationContext context: Context): CoffeeDatabase.AppDatabase{
        return Room.databaseBuilder(
            context,
            CoffeeDatabase.AppDatabase::class.java,"coffee_database"
        ).build()
    }

}












