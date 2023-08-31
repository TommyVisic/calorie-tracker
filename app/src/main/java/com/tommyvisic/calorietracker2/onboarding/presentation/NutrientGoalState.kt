package com.tommyvisic.calorietracker2.onboarding.presentation

/**
 * Simple UI state for the carb, protein, and fat ratios. This way we can bundle changes together
 * instead of having individual text state on the view model for each field.
 */
data class NutrientGoalState(
    val carbRatio: String = "40",
    val proteinRatio: String = "30",
    val fatRatio: String = "30"
)