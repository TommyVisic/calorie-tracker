package com.tommyvisic.calorietracker2.core.presentation

/**
 * A UI event that travels from the view model to the view.
 */
sealed class UiEvent {
    data object Success : UiEvent()
    data object NavigateUp : UiEvent()
    data class ShowSnackbar(val message: UiText) : UiEvent()
}