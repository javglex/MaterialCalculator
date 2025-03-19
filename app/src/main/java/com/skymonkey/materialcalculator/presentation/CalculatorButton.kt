package com.skymonkey.materialcalculator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    action: CalculatorUiAction,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(
                when(action.highlightLevel) {
                    HighlightLevel.Highlight -> MaterialTheme.colorScheme.tertiary
                    HighlightLevel.NeutralHighlight -> MaterialTheme.colorScheme.surfaceVariant
                    HighlightLevel.SemiHighlight -> MaterialTheme.colorScheme.inverseSurface
                    HighlightLevel.StrongHighlight -> MaterialTheme.colorScheme.primary
                }
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if(action.text.isNotEmpty()) {
            Text(
                text = action.text,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                color = when(action.highlightLevel) {
                    HighlightLevel.Highlight -> MaterialTheme.colorScheme.onTertiary
                    HighlightLevel.NeutralHighlight -> MaterialTheme.colorScheme.onSurfaceVariant
                    HighlightLevel.SemiHighlight -> MaterialTheme.colorScheme.inverseOnSurface
                    HighlightLevel.StrongHighlight -> MaterialTheme.colorScheme.onPrimary
                }
            )
        } else {
            action.content
        }
    }
}