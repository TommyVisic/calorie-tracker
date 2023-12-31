package com.tommyvisic.calorietracker2.tracker.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.tommyvisic.calorietracker2.ui.theme.CarbsColor
import com.tommyvisic.calorietracker2.ui.theme.FatColor
import com.tommyvisic.calorietracker2.ui.theme.ProteinColor

/**
 * The horizontal bar in the tracker feature that shows how much carbs, protein, and fat you've
 * scarfed down today.
 */
@Composable
fun NutrientsBar(
    carbs: Int,
    protein: Int,
    fat: Int,
    calories: Int,
    calorieGoal: Int,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colors.background
    val caloriesExceededColor = MaterialTheme.colors.error
    val carbsWidthRatio = remember {
        Animatable(0f)
    }
    val proteinWidthRatio = remember {
        Animatable(0f)
    }
    val fatWidthRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = carbs) {
        carbsWidthRatio.animateTo(
            targetValue = carbs * 4f / calorieGoal
        )
    }
    LaunchedEffect(key1 = protein) {
        proteinWidthRatio.animateTo(
            targetValue = protein * 4f / calorieGoal
        )
    }
    LaunchedEffect(key1 = fat) {
        fatWidthRatio.animateTo(
            targetValue = fat * 9f / calorieGoal
        )
    }

    Canvas(modifier = modifier) {
        if (calories <= calorieGoal) {
            val carbsWidth = carbsWidthRatio.value * size.width
            val proteinWidth = proteinWidthRatio.value * size.width
            val fatWidth = fatWidthRatio.value * size.width

            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = FatColor,
                size = Size(
                    width = carbsWidth + proteinWidth + fatWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = ProteinColor,
                size = Size(
                    width = carbsWidth + proteinWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = CarbsColor,
                size = Size(
                    width = carbsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
        } else {
            drawRoundRect(
                color = caloriesExceededColor,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }
}