package com.tommyvisic.calorietracker2.tracker.domain

import com.tommyvisic.calorietracker2.core.data.Preferences
import com.tommyvisic.calorietracker2.core.domain.ActivityLevel
import com.tommyvisic.calorietracker2.core.domain.Gender
import com.tommyvisic.calorietracker2.core.domain.Goal
import com.tommyvisic.calorietracker2.core.domain.UserInfo
import kotlin.math.roundToInt

/**
 * The calculate meal nutrients use case. This is our most complicated use case. It has the
 * calculation for body-mass ratio, daily calorie requirements, and mode.
 */
class CalculateMealNutrients(
    private val preferences: Preferences
) {
    operator fun invoke(trackedFoods: List<TrackedFood>): Result {
        val nutrientsByMealType = trackedFoods
            .groupBy { it.mealType }
            .mapValues { entry ->
                val mealType = entry.key
                val foods = entry.value

                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    protein = foods.sumOf { it.protein },
                    fat = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    mealType = mealType
                )
            }

        val totalCarbs = nutrientsByMealType.values.sumOf { it.carbs }
        val totalProtein = nutrientsByMealType.values.sumOf { it.protein }
        val totalFat = nutrientsByMealType.values.sumOf { it.fat }
        val totalCalories = nutrientsByMealType.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()
        val calorieGoal = dailyCalorieRequirement(userInfo)
        val carbsGoal = (calorieGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (calorieGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (calorieGoal * userInfo.fatRatio / 9f).roundToInt()

        return Result(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            calorieGoal = calorieGoal,
            totalCarbs = totalCarbs,
            totalProtein = totalProtein,
            totalFat = totalFat,
            totalCalories = totalCalories,
            nutrientsByMealType = nutrientsByMealType
        )
    }

    private fun bmr(userInfo: UserInfo): Int {
        return when(userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }
            is Gender.Female -> {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67f * userInfo.age).roundToInt()
            }
        }
    }

    private  fun dailyCalorieRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val calorieAdjustment = when(userInfo.goal) {
            is Goal.LoseWeight -> -500
            is Goal.KeepWeight -> 0
            is Goal.GainWeight -> 500
        }

        return (bmr(userInfo) * activityFactor + calorieAdjustment).roundToInt()
    }

    data class MealNutrients(
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val calories: Int,
        val mealType: MealType
    )

    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val calorieGoal: Int,
        val totalCarbs: Int,
        val totalProtein: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val nutrientsByMealType: Map<MealType, MealNutrients>
    )
}