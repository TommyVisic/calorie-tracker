package com.tommyvisic.calorietracker2.onboarding.domain

import com.tommyvisic.calorietracker2.R
import com.tommyvisic.calorietracker2.core.presentation.UiText

/**
 * A use case to make sure the nutrient ratios are valid.
 */
class ValidateNutrients {
    operator fun invoke(
        carbRatioText: String,
        proteinRatioText: String,
        fatRatioText: String,
    ): Result {
        val carbRatio = carbRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()

        if (carbRatio == null || proteinRatio == null || fatRatio == null) {
            return Result.Error(message = UiText.StringResource(R.string.error_invalid_values))
        }

        if (carbRatio + proteinRatio + fatRatio != 100) {
            return Result.Error(message = UiText.StringResource(R.string.error_not_100_percent))
        }

        return Result.Success(
            carbRatio / 100f,
            proteinRatio / 100f,
            fatRatio / 100f
        )
    }

    sealed class Result {
        data class Success(
            val carbRatio: Float,
            val proteinRatio: Float,
            val fatRatio: Float
        ) : Result()

        data class Error(val message: UiText) : Result()
    }
}