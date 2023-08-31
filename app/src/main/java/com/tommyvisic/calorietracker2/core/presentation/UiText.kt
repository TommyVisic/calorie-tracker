package com.tommyvisic.calorietracker2.core.presentation

import android.content.Context

/**
 * An abstraction over getting string data. Can be a plain string or a resource.
 */
sealed class UiText {
    data class DynamicString(val text: String) : UiText()
    data class StringResource(val resId: Int) : UiText()

    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> text
            is StringResource -> context.getString(resId)
        }
    }
}