package com.tommyvisic.calorietracker2.onboarding.di

import com.tommyvisic.calorietracker2.onboarding.domain.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingModule {

    @Provides
    fun provideValidateNutrients(): ValidateNutrients {
        return ValidateNutrients()
    }
}