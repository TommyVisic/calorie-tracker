package com.tommyvisic.calorietracker2.tracker.domain

import com.google.common.truth.Truth.assertThat
import com.tommyvisic.calorietracker2.core.data.Preferences
import com.tommyvisic.calorietracker2.core.domain.ActivityLevel
import com.tommyvisic.calorietracker2.core.domain.Gender
import com.tommyvisic.calorietracker2.core.domain.Goal
import com.tommyvisic.calorietracker2.core.domain.UserInfo
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class CalculateMealNutrientsTest {
    private lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setup() {
        val preferences = mockk<Preferences>(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goal = Goal.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )

        calculateMealNutrients = CalculateMealNutrients(preferences)
    }

    @Test
    fun caloriesForBreakfastProperlyCalculated() {
        val trackedFoods = listOf(
            TrackedFood(
                name = "",
                carbs = 1,
                protein = 1,
                fat = 1,
                imageUrl = "",
                MealType.Breakfast,
                amount = 100,
                LocalDate.of(2022, 1, 1),
                calories = 17
            )
        )

        val result = calculateMealNutrients(trackedFoods)
        val nutrients = result.nutrientsByMealType[MealType.Breakfast]
        assertThat(nutrients?.calories).isEqualTo(17)
    }
}