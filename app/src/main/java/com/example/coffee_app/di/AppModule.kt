package com.example.coffee_app.di

import com.example.coffee_app.api.CoffeeAPI
import com.example.coffee_app.cons.Cons.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit): CoffeeAPI {
        return retrofit.create(CoffeeAPI::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}












