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
 * The view model for the height screen. Demonstrates the use of a use case.
 */
@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterDigits: FilterDigits
) : ViewModel() {

    var heightText by mutableStateOf("180")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onHeightTextChange(heightText: String) {
        if (heightText.length <=3)
        {
            this.heightText = filterDigits(heightText)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val height = heightText.toIntOrNull()
            if (height == null) {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_invalid_height))
                )
            }
            else
            {
                preferences.saveHeight(height)
                _uiEvent.send(UiEvent.Success)
            }
        }
    }
}