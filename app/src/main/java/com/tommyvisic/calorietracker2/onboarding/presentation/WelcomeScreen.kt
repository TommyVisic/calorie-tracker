package com.tommyvisic.calorietracker2.onboarding.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.tommyvisic.calorietracker2.ui.ActionButton
import com.tommyvisic.calorietracker2.R
import com.tommyvisic.calorietracker2.navigation.Route
import com.tommyvisic.calorietracker2.core.presentation.UiEvent
import com.tommyvisic.calorietracker2.ui.spacing

/**
 * The first screen the user sees during onboarding. It's just a hello message and a next button.
 */
@Composable
fun WelcomeScreen(
    onNextClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        ActionButton(
            text = stringResource(id = R.string.next),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onNextClick)
    }
}