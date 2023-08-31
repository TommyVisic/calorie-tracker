package com.tommyvisic.calorietracker2.core.domain

/**
 * A simple use case. Ensures a string contains only digits.
 */
class FilterDigits {
    operator fun invoke(text: String): String {
        return text.filter { it.isDigit() }
    }
}