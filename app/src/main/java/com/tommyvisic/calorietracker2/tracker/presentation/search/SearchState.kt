package com.tommyvisic.calorietracker2.tracker.presentation.search

/**
 * UI state observe by the view. This is held by the corresponding view model.
 */
data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val trackableFood: List<TrackableFoodUiState> = emptyList()
)