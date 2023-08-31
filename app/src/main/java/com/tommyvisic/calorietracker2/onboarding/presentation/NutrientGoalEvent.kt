package com.tommyvisic.calorietracker2.onboarding.presentation

/**
 * These events originate in the view and flow to the view model. For example when the carb ratio
 * text changes, NutrientGoalEvent.OnCarbRatioEnter is sent to the view model.
 */
sealed class NutrientGoalEvent {
    data class OnCarbRatioEnter(val ratio: String) : NutrientGoalEvent()
    data class OnProteinRatioEnter(val ratio: String) : NutrientGoalEvent()
    data class OnFatRatioEnter(val ratio: String) : NutrientGoalEvent()
    object OnNextClick : NutrientGoalEvent()
}