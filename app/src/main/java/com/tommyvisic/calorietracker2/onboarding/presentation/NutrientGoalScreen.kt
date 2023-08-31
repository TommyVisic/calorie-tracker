package com.tommyvisic.calorietracker2.onboarding.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.tommyvisic.calorietracker2.core.presentation.UiEvent
import com.tommyvisic.calorietracker2.R
import com.tommyvisic.calorietracker2.ui.ActionButton
import com.tommyvisic.calorietracker2.ui.spacing

/**
 * Supports the user in choosing carb, protein, and fat intake ratios.
 */
@Composable
fun NutrientGoalScreen(
    scaffoldState: ScaffoldState,
    onNextClick: () -> Unit,
    viewModel: NutrientGoalViewModel = hiltViewModel()
) {

    // We can receive success events & ShowSnackbar events here.
    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackbar -> scaffoldState.snackbarHostState.showSnackbar(
                    event.message.asString(context)
                )
                else -> Unit
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(MaterialTheme.spacing.large)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            UnitTextField(
                value = viewModel.state.carbRatio,
                onValueChange = {
                    viewModel.onEvent(NutrientGoalEvent.OnCarbRatioEnter(it))
                },
                unit = stringResource(R.string.percent_carbs)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            UnitTextField(
                value = viewModel.state.proteinRatio,
                onValueChange = {
                    viewModel.onEvent(NutrientGoalEvent.OnProteinRatioEnter(it))
                },
                unit = stringResource(R.string.percent_proteins)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            UnitTextField(
                value = viewModel.state.fatRatio,
                onValueChange = {
                    viewModel.onEvent(NutrientGoalEvent.OnFatRatioEnter(it))
                },
                unit = stringResource(R.string.percent_fats)
            )
        }

        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(R.string.next),
            onClick = { viewModel.onEvent(NutrientGoalEvent.OnNextClick) })
    }
}