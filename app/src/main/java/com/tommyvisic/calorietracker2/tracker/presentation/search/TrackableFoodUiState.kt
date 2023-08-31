package com.tommyvisic.calorietracker2.tracker.presentation.search

import com.tommyvisic.calorietracker2.tracker.domain.TrackableFood

/**
 * UI state for a food item returned from the open food search API
 */
data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amountText: String = String()
)