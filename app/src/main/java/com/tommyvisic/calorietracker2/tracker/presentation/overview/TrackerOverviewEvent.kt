package com.tommyvisic.calorietracker2.tracker.presentation.overview

import com.tommyvisic.calorietracker2.tracker.domain.TrackedFood

/**
 * Events that flow from the view to the view model. Basically, instead of having individual methods
 * on the view model, we have one method that is parameterized by these event types.
 */
sealed class TrackerOverviewEvent {
    data object OnNextDayClick : TrackerOverviewEvent()
    data object OnPreviousDayClick : TrackerOverviewEvent()
    data class OnToggleMealClick(val meal: Meal) : TrackerOverviewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood) : TrackerOverviewEvent()
}
