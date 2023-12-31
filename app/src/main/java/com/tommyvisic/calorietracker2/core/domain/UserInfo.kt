package com.tommyvisic.calorietracker2.core.domain

/**
 * The user profile that is setup during onboarding.
 */
data class UserInfo(
    val gender: Gender,
    val age: Int,
    val weight: Float,
    val height: Int,
    val activityLevel: ActivityLevel,
    val goal: Goal,
    val carbRatio: Float,
    val proteinRatio: Float,
    val fatRatio: Float,
)
