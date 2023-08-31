package com.tommyvisic.calorietracker2.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.tommyvisic.calorietracker2.R
import com.tommyvisic.calorietracker2.core.data.Preferences
import com.tommyvisic.calorietracker2.core.domain.FilterDigits
import com.tommyvisic.calorietracker2.core.presentation.UiEvent
import com.tommyvisic.calorietracker2.core.presentation.UiText
import com.tommyvisic.calorietracker2.navigation.Route

/**
 * The view model for the age screen. Demonstrates use of a use case (filterDigits), which is
 * injected into the view model.
 */
@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterDigits: FilterDigits // <-- Here's a domain layer use case
) : ViewModel() {

    var ageText by mutableStateOf("20")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAgeTextChange(ageText: String) {
        if (ageText.length <=3) {
            this.ageText = filterDigits(ageText)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val age = ageText.toIntOrNull()
            if (age == null) {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_invalid_age))
                )
            }
            else {
                preferences.saveAge(age)
                _uiEvent.send(UiEvent.Success)
            }
        }
    }
}