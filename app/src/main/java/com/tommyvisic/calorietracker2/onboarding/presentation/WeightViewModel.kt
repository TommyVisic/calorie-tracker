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
import com.tommyvisic.calorietracker2.core.presentation.UiEvent
import com.tommyvisic.calorietracker2.core.presentation.UiText
import com.tommyvisic.calorietracker2.navigation.Route

/**
 * The view model for the weight screen.
 */
@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var weightText by mutableStateOf("80.0")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightTextChange(weightText: String) {
        val text = weightText.take(5)

        if (text.isBlank()) {
            this.weightText = ""
            return
        }

        val number = text.trim().toFloatOrNull()
        if (number != null && number > 0) {
            this.weightText = text
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val weight = weightText.toFloatOrNull()
            if (weight == null) {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_invalid_weight))
                )
            }
            else
            {
                preferences.saveWeight(weight)
                _uiEvent.send(UiEvent.Success)
            }
        }
    }
}