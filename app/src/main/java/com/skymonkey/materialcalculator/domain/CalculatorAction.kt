package com.skymonkey.materialcalculator.domain

sealed interface CalculatorAction {
    data class Number(val number: Int): CalculatorAction
    data class Op(val operation: Operation): CalculatorAction
    data object Clear: CalculatorAction
    data object Delete: CalculatorAction
    data object Parenthesis: CalculatorAction
    data object Calculate: CalculatorAction // evaluate expression
    data object Decimal: CalculatorAction
}