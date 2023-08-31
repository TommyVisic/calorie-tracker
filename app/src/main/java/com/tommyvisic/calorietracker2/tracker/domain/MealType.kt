package com.tommyvisic.calorietracker2.tracker.domain

/**
 * A domain-level object that models the type of meal (e.g. breakfast, lunch, or dinner)
 */
sealed class MealType(val name: String) {
    data object Breakfast : MealType("breakfast")
    data object Lunch : MealType("lunch")
    data object Dinner : MealType("dinner")
    data object Snack : MealType("snack")

    companion object {
        fun fromString(name: String): MealType {
            return when(name) {
                "breakfast" -> Breakfast
                "lunch" -> Lunch
                "dinner" -> Dinner
                "snack" -> Snack
                else -> Snack
            }
        }
    }
}