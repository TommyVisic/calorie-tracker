package com.tommyvisic.calorietracker2.core.data

import android.content.SharedPreferences
import com.tommyvisic.calorietracker2.core.domain.ActivityLevel
import com.tommyvisic.calorietracker2.core.domain.Gender
import com.tommyvisic.calorietracker2.core.domain.Goal
import com.tommyvisic.calorietracker2.core.domain.UserInfo

/**
 * The concrete implementation of the preferences. This uses Android shared preferences to persist
 * the data.
 */
class DefaultPreferences(
    private val sharedPrefs: SharedPreferences
) : Preferences {
    override fun saveGender(gender: Gender) {
        sharedPrefs.edit()
            .putString(Preferences.GENDER_KEY, gender.name)
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPrefs.edit()
            .putInt(Preferences.AGE_KEY, age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPrefs.edit()
            .putFloat(Preferences.WEIGHT_KEY, weight)
            .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPrefs.edit()
            .putInt(Preferences.HEIGHT_KEY, height)
            .apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPrefs.edit()
            .putString(Preferences.ACTIVITY_LEVEL_KEY, level.name)
            .apply()
    }

    override fun saveGoal(type: Goal) {
        sharedPrefs.edit()
            .putString(Preferences.GOAL_TYPE_KEY, type.name)
            .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPrefs.edit()
            .putFloat(Preferences.CARB_RATIO_KEY, ratio)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPrefs.edit()
            .putFloat(Preferences.PROTEIN_RATIO_KEY, ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPrefs.edit()
            .putFloat(Preferences.FAT_RATIO_KEY, ratio)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        return UserInfo(
            Gender.fromString(sharedPrefs.getString(Preferences.GENDER_KEY, null)),
            sharedPrefs.getInt(Preferences.AGE_KEY, -1),
            sharedPrefs.getFloat(Preferences.WEIGHT_KEY, -1f),
            sharedPrefs.getInt(Preferences.HEIGHT_KEY, -1),
            ActivityLevel.fromString(sharedPrefs.getString(Preferences.ACTIVITY_LEVEL_KEY, null)),
            Goal.fromString(sharedPrefs.getString(Preferences.GOAL_TYPE_KEY, null)),
            sharedPrefs.getFloat(Preferences.CARB_RATIO_KEY, -1f),
            sharedPrefs.getFloat(Preferences.PROTEIN_RATIO_KEY, -1f),
            sharedPrefs.getFloat(Preferences.FAT_RATIO_KEY, -1f)
        )
    }

    override fun saveShouldShowOnboarding(show: Boolean) {
        sharedPrefs.edit()
            .putBoolean(Preferences.SHOULD_SHOW_ONBOARDING_KEY, show)
            .apply()
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return sharedPrefs.getBoolean(Preferences.SHOULD_SHOW_ONBOARDING_KEY, true)
    }
}