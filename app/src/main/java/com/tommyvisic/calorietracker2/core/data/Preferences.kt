package com.tommyvisic.calorietracker2.core.data

import com.tommyvisic.calorietracker2.core.domain.ActivityLevel
import com.tommyvisic.calorietracker2.core.domain.Gender
import com.tommyvisic.calorietracker2.core.domain.Goal
import com.tommyvisic.calorietracker2.core.domain.UserInfo

/**
 * The user profile or the user's preferences.
 */
interface Preferences {
    fun saveGender(gender: Gender)
    fun saveAge(age: Int)
    fun saveWeight(weight: Float)
    fun saveHeight(height: Int)
    fun saveActivityLevel(level: ActivityLevel)
    fun saveGoal(type: Goal)
    fun saveCarbRatio(ratio: Float)
    fun saveProteinRatio(ratio: Float)
    fun saveFatRatio(ratio: Float)

    fun loadUserInfo(): UserInfo

    fun saveShouldShowOnboarding(show: Boolean)
    fun loadShouldShowOnboarding(): Boolean

    companion object {
        const val GENDER_KEY = "gender"
        const val AGE_KEY = "age"
        const val WEIGHT_KEY = "weight"
        const val HEIGHT_KEY = "height"
        const val ACTIVITY_LEVEL_KEY = "activity_level"
        const val GOAL_TYPE_KEY = "goal_type"
        const val CARB_RATIO_KEY = "carb_ratio"
        const val PROTEIN_RATIO_KEY = "protein_ratio"
        const val FAT_RATIO_KEY = "fat_ratio"
        const val SHOULD_SHOW_ONBOARDING_KEY = "should_show_onboarding"
    }
}