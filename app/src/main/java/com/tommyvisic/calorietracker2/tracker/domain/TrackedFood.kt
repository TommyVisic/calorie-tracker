package com.tommyvisic.calorietracker2.tracker.domain

import java.time.LocalDate

/**
 * Domain layer model for Food that has been tracked by the user (marked as eaten).
 */
data class TrackedFood(
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val mealType: MealType,
    val amount: Int,
    val date: LocalDate,
    val calories: Int,
    val id: Int? = null
)
