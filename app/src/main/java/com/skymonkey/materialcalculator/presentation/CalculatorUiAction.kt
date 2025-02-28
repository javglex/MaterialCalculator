package com.skymonkey.materialcalculator.presentation

import androidx.compose.runtime.Composable
import com.skymonkey.materialcalculator.domain.CalculatorAction

data class CalculatorUiAction(
    val text: String,
    val highlightLevel: HighlightLevel,
    val action: CalculatorAction,
    val content: @Composable () -> Unit = {}
)

sealed interface HighlightLevel {
    data object NeutralHighlight: HighlightLevel
    data object SemiHighlight: HighlightLevel
    data object Highlight: HighlightLevel
    data object StrongHighlight: HighlightLevel
}