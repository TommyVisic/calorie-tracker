package com.tommyvisic.calorietracker2.core.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.tommyvisic.calorietracker2.core.data.Preferences
import com.tommyvisic.calorietracker2.core.data.DefaultPreferences
import com.tommyvisic.calorietracker2.core.domain.FilterDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun  provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("shared_prefs", MODE_PRIVATE)
    }

    @Provides
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences {
        return DefaultPreferences(sharedPreferences)
    }

    @Provides
    fun provideFilterDigitsUseCase(): FilterDigits {
        return FilterDigits()
    }
}