package com.tommyvisic.calorietracker2.tracker.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import java.time.LocalDate
import com.tommyvisic.calorietracker2.R
import java.time.format.DateTimeFormatter

@Composable
fun FriendlyDateText(
    date: LocalDate
): String {
    val today = LocalDate.now()
    return when(date) {
        today -> stringResource(R.string.today)
        today.minusDays(1) -> stringResource(R.string.yesterday)
        today.plusDays(1) -> stringResource(R.string.tomorrow)
        else -> DateTimeFormatter.ofPattern("MMMM dd").format(date)
    }
}