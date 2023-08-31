package com.tommyvisic.calorietracker2.tracker.presentation.search

import com.tommyvisic.calorietracker2.tracker.domain.MealType
import com.tommyvisic.calorietracker2.tracker.domain.TrackableFood
import java.time.LocalDate

/**
 * These events originate in the view and flow to the view model to indicate user action.
 */
sealed class SearchEvent {
    data class OnQueryChange(val query: String) : SearchEvent()
    data object OnSearch : SearchEvent()
    data class OnToggleTrackableFood(val food: TrackableFood) : SearchEvent()
    data class OnAmountForFoodChange(
        val food: TrackableFood,
        val amountText: String
    ) : SearchEvent()
    data class OnTrackFoodClick(
        val food: TrackableFood,
        val mealType: MealType,
        val date: LocalDate
    ) : SearchEvent()
    data class OnSearchFocusChange(val isFocusChange: Boolean) : SearchEvent()
}