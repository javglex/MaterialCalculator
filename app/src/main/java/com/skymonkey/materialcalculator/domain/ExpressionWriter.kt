package com.skymonkey.materialcalculator.domain

import kotlin.math.exp

class ExpressionWriter {

    var expression = ""

    fun processAction(action: CalculatorAction) {
        when(action) {
            CalculatorAction.Calculate -> {
                val parser = ExpressionParser(prepareForCalculation())
                val evaluator = ExpressionEvaluator(parser.parse())
                expression = evaluator.evaluate().toString()
            }
            CalculatorAction.Clear -> {
                expression = ""
            }
            CalculatorAction.Decimal -> {
                if (canEnterDecimal()) {
                    expression += "."
                }
            }
            CalculatorAction.Delete -> {
                expression = expression.dropLast(1)
            }
            is CalculatorAction.Number -> {
                expression += action.number
            }
            is CalculatorAction.Op -> {
                if (canEnterOperation(action.operation)) {
                    expression += action.operation.symbol
                }
            }
            CalculatorAction.Parenthesis -> {
                processParenthesis()
            }
        }
    }

    private fun prepareForCalculation(): String {
        val newExpression = expression.dropLastWhile {
            it in "$operationSymbols(."
        }
        if (newExpression.isEmpty()) {
            return "0"
        }
        return newExpression
    }

    /**
     * Checks if the user can enter an operation
     * e.g +--3-5 should be valid
     * e.g *3-5 should be invalid
     */
    private fun canEnterOperation(operation: Operation): Boolean {
        // can we enter add or subtract?
        if (operation in listOf(Operation.ADD, Operation.SUBTRACT)) {
            return expression.isEmpty() || expression.last() in "$operationSymbols()0123456789"
        }
        // can we enter multiplication, division, or percent?
        return expression.isNotEmpty() || expression.last() in "0123456789)"
    }

    private fun canEnterDecimal(): Boolean {
        // we can't set decimals after operations, only numbers.
        if (expression.isEmpty() || expression.last() in "$operationSymbols.()") {
            return false
        }
        /*
        5 + 5.45 is valid
        5 + 5.45.34 is invalid and we prevent it
        IOW, if there is a period in our number already, don't allow another period.
         */
        return !expression.takeLastWhile {
            it in "0123456789."
        }.contains(".")
    }

    private fun processParenthesis() {
        val openingParenthesisCount = expression.count { it == '(' }
        val closingParenthesisCount = expression.count { it == ')' }
        expression += when {
            expression.isEmpty() ||
                    expression.last() in "$operationSymbols(" -> "("
            expression.last() in "0123456789)" &&
                    openingParenthesisCount == closingParenthesisCount -> return
            else -> ")"
        }
    }
}