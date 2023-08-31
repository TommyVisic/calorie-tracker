package com.tommyvisic.calorietracker2.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tommyvisic.calorietracker2.core.domain.Gender
import com.tommyvisic.calorietracker2.core.data.Preferences
import com.tommyvisic.calorietracker2.navigation.Route
import com.tommyvisic.calorietracker2.core.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The view model for the gender select screen. Super simple.
 */
@HiltViewModel
class GenderViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var gender by mutableStateOf<Gender>(Gender.Male)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGenderClick(gender: Gender) {
        this.gender = gender
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGender(gender)
            _uiEvent.send(UiEvent.Success)
        }
    }
}