package com.tommyvisic.calorietracker2.tracker.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.tommyvisic.calorietracker2.tracker.presentation.overview.Meal
import com.tommyvisic.calorietracker2.R
import com.tommyvisic.calorietracker2.ui.spacing

/**
 * The breakfast, lunch, dinner, and snack sections in the tracker feature.
 */
@Composable
fun ExpandableMeal(
    meal: Meal,
    onToggleClick: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
    ) {

    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggleClick() }
                .padding(MaterialTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(meal.drawableRes),
                contentDescription = meal.name.asString(context)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = meal.name.asString(context),
                        style = MaterialTheme.typography.h3
                    )
                    Icon(
                        imageVector =
                        if (meal.isExpanded)
                            Icons.Default.KeyboardArrowUp
                        else
                            Icons.Default.KeyboardArrowDown,
                        contentDescription =
                        if (meal.isExpanded)
                            stringResource(R.string.collapse)
                        else
                            stringResource(R.string.extend)
                    )

                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    UnitDisplay(
                        amount = meal.calories,
                        unit = stringResource(R.string.kcal),
                        amountTextSize = 30.sp
                    )
                    Row {
                        UnitDisplayColumn(
                            name = stringResource(R.string.carbs),
                            amount = meal.carbs,
                            unit = stringResource(R.string.grams)
                        )
                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                        UnitDisplayColumn(
                            name = stringResource(R.string.protein),
                            amount = meal.protein,
                            unit = stringResource(R.string.grams)
                        )
                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                        UnitDisplayColumn(
                            name = stringResource(R.string.fat),
                            amount = meal.fat,
                            unit = stringResource(R.string.grams)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        AnimatedVisibility(visible = meal.isExpanded) {
            content()
        }
    }
}