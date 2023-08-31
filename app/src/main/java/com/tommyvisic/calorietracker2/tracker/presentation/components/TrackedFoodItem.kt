package com.tommyvisic.calorietracker2.tracker.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tommyvisic.calorietracker2.R
import com.tommyvisic.calorietracker2.tracker.domain.TrackedFood
import com.tommyvisic.calorietracker2.ui.spacing

/**
 * Shows a food item that has been tracked and is now visible under one of the meals in the tracker
 * feature.
 */
@Composable
fun TrackedFoodItem(
    trackedFood: TrackedFood,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(MaterialTheme.spacing.extraSmall)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(MaterialTheme.colors.surface)
            .padding(end = MaterialTheme.spacing.medium)
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(trackedFood.imageUrl)
                .crossfade(true)
                .error(R.drawable.ic_burger)
                .fallback(R.drawable.ic_burger)
                .build(),
            contentDescription = trackedFood.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(
                    RoundedCornerShape(
                        topStart = 5.dp,
                        bottomStart = 5.dp
                    )
                )
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = trackedFood.name,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            Text(
                text = stringResource(
                    R.string.nutrient_info,
                    trackedFood.amount,
                    trackedFood.calories
                ),
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.delete),
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { onDeleteClick() }
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                UnitDisplayColumn(
                    name = stringResource(R.string.carbs),
                    amount = trackedFood.carbs,
                    unit = stringResource(R.string.grams),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                UnitDisplayColumn(
                    name = stringResource(R.string.protein),
                    amount = trackedFood.protein,
                    unit = stringResource(R.string.grams),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                UnitDisplayColumn(
                    name = stringResource(R.string.fat),
                    amount = trackedFood.fat,
                    unit = stringResource(R.string.grams),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp
                )
            }
        }
    }
}