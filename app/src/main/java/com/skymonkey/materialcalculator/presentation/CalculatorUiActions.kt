package com.skymonkey.materialcalculator.presentation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import com.skymonkey.materialcalculator.domain.CalculatorAction
import com.skymonkey.materialcalculator.domain.Operation

val calculatorActions = listOf(
    CalculatorUiAction(
        text = "AC",
        highlightLevel = HighlightLevel.Highlight,
        action = CalculatorAction.Clear
    ),
    CalculatorUiAction(
        text = "()",
        highlightLevel = HighlightLevel.SemiHighlight,
        action = CalculatorAction.Parenthesis
    ),
    CalculatorUiAction(
        text = "%",
        highlightLevel = HighlightLevel.SemiHighlight,
        action = CalculatorAction.Op(Operation.PERCENT)
    ),
    CalculatorUiAction(
        text = "÷",
        highlightLevel = HighlightLevel.SemiHighlight,
        action = CalculatorAction.Op(Operation.DIVIDE)
    ),
    CalculatorUiAction(
        text = "7",
        highlightLevel = HighlightLevel.NeutralHighlight,
        action = CalculatorAction.Number(7)
    ),
    CalculatorUiAction(
        text = "8",
        highlightLevel = HighlightLevel.NeutralHighlight,
        action = CalculatorAction.Number(8)
    ),
    CalculatorUiAction(
        text = "9",
        highlightLevel = HighlightLevel.NeutralHighlight,
        action = CalculatorAction.Number(9)
    ),
    CalculatorUiAction(
        text = "x",
        highlightLevel = HighlightLevel.StrongHighlight,
        action = CalculatorAction.Op(Operation.MULTIPLY)
    ),
    CalculatorUiAction(
        text = "4",
        highlightLevel = HighlightLevel.NeutralHighlight,
        action = CalculatorAction.Number(4)
    ),
    CalculatorUiAction(
        text = "5",
        highlightLevel = HighlightLevel.NeutralHighlight,
        action = CalculatorAction.Number(5)
    ),
    CalculatorUiAction(
        text = "6",
        highlightLevel = HighlightLevel.NeutralHighlight,
        action = CalculatorAction.Number(6)
    ),
    CalculatorUiAction(
        text = "-",
        highlightLevel = HighlightLevel.SemiHighlight,
        action = CalculatorAction.Op(Operation.SUBTRACT)
    ),
    CalculatorUiAction(
        text = "1",
        highlightLevel = HighlightLevel.NeutralHighlight,
        action = CalculatorAction.Number(1)
    ),
    CalculatorUiAction(
        text = "2",
        highlightLevel = HighlightLevel.NeutralHighlight,
        action = CalculatorAction.Number(2)
    ),
    CalculatorUiAction(
        text = "3",
        highlightLevel = HighlightLevel.NeutralHighlight,
        action = CalculatorAction.Number(3)
    ),
    CalculatorUiAction(
        text = "+",
        highlightLevel = HighlightLevel.SemiHighlight,
        action = CalculatorAction.Op(Operation.ADD)
    ),
    CalculatorUiAction(
        text = "0",
        highlightLevel = HighlightLevel.NeutralHighlight,
        action = CalculatorAction.Number(0)
    ),
    CalculatorUiAction(
        text = ".",
        highlightLevel = HighlightLevel.NeutralHighlight,
        action = CalculatorAction.Decimal
    ),
    CalculatorUiAction(
        text = "",
        content = {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        highlightLevel = HighlightLevel.NeutralHighlight,
        action = CalculatorAction.Delete
    ),
    CalculatorUiAction(
        text = "=",
        highlightLevel = HighlightLevel.StrongHighlight,
        action = CalculatorAction.Calculate
    ),
)