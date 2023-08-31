package com.tommyvisic.calorietracker2.tracker.presentation.overview

import com.tommyvisic.calorietracker2.tracker.domain.TrackedFood
import java.time.LocalDate

/**
 * UI state held by the view model and observed by the view.
 */
data class TrackerOverviewState(
    val totalCarbs: Int = 0,
    val totalProtein: Int = 0,
    val totalFat: Int = 0,
    val totalCalories: Int = 0,
    val carbsGoal: Int = 0,
    val proteinGoal: Int = 0,
    val fatGoal: Int = 0,
    val calorieGoal: Int = 0,
    val date: LocalDate = LocalDate.now(),
    val trackedFoods: List<TrackedFood> = emptyList(),
    val meals: List<Meal> = defaultMeals
)
