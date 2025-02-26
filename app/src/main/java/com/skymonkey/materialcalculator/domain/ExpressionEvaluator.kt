package com.skymonkey.materialcalculator.domain

/**
 * User the following grammar.
 * expression: term | term + term | term - term
 * term: factor | factor * factor | factor / factor | factor % factor
 * factor: number | ( expression ) | + factor | - factor
 */
class ExpressionEvaluator(
    private val expression: List<ExpressionPart>
) {

    /**
     * Returns result of the evaluation as a double
     */
    fun evaluate(): Double {
        return evaluateExpression(expression).value
    }

    // a factor is either a number or an expression in parenthesis
    private fun evaluateFactor(expression: List<ExpressionPart>): ExpressionResult {
        return when(val part = expression.firstOrNull()) {
            ExpressionPart.Op(Operation.ADD) -> {
                evaluateFactor(expression.drop(1))
            }
            ExpressionPart.Op(Operation.SUBTRACT) -> {
                evaluateFactor(expression.drop(1)).run {
                    ExpressionResult(remainingExpression, -value)
                }
            }
            ExpressionPart.Parenthesis(ParenthesisType.OpeningType) -> {
                evaluateExpression(expression.drop(1)).run {
                    ExpressionResult(remainingExpression.drop(1), value)
                }
            }
            ExpressionPart.Op(Operation.PERCENT) -> {
                evaluateTerm(expression.drop(1))
            }
            is ExpressionPart.Number -> ExpressionResult(
                expression.drop(1),
                part.number
            )
            else -> throw RuntimeException("Invalid expression part.")
        }
    }

    private fun evaluateExpression(expression: List<ExpressionPart>): ExpressionResult {
        val result = evaluateTerm(expression)
        var remainingExpression = result.remainingExpression
        var sum = result.value
        while(true) {
            when(remainingExpression.firstOrNull()) {
                ExpressionPart.Op(Operation.ADD) -> {
                    val term = evaluateTerm(remainingExpression.drop(1))
                    sum += term.value
                    remainingExpression = term.remainingExpression
                }
                ExpressionPart.Op(Operation.SUBTRACT) -> {
                    val term = evaluateTerm(remainingExpression.drop(1))
                    sum -= term.value
                    remainingExpression = term.remainingExpression
                }
                else -> return ExpressionResult(remainingExpression, sum)
            }
        }
    }

    private fun evaluateTerm(expression: List<ExpressionPart>): ExpressionResult {
        val result = evaluateFactor(expression)
        var remainingExpression = result.remainingExpression
        var sum = result.value
        while(true) {
            when(remainingExpression.firstOrNull()) {
                ExpressionPart.Op(Operation.MULTIPLY) -> {
                    val factor = evaluateFactor(remainingExpression.drop(1))
                    sum *= factor.value
                    remainingExpression = factor.remainingExpression
                }
                ExpressionPart.Op(Operation.DIVIDE) -> {
                    val factor = evaluateFactor(remainingExpression.drop(1))
                    sum /= factor.value
                    remainingExpression = factor.remainingExpression
                }
                ExpressionPart.Op(Operation.PERCENT) -> {
                    val factor = evaluateFactor(remainingExpression.drop(1))
                    sum *= (factor.value / 100.0)
                    remainingExpression = factor.remainingExpression
                }
                else -> return ExpressionResult(remainingExpression, sum)
            }
        }
    }

    data class ExpressionResult(
        val remainingExpression: List<ExpressionPart>,
        val value: Double
    )
}