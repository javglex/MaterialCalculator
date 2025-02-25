package com.skymonkey.materialcalculator.domain

class ExpressionParser(
    private val calculation: String
) {
    fun parse(): List<ExpressionPart> {
        val result = mutableListOf<ExpressionPart>()
        var i = 0
        while (i < calculation.length) {
            val curChar = calculation[i]
            when {
                curChar in operationSymbols -> {
                    result.add(
                        ExpressionPart.Op(operationFromSymbol(curChar))
                    )
                }
                curChar.isDigit() -> {
                    // add character digits to our result, and at the same time update
                    // our index to where we left off
                    i = parseNumber(i, result)
                    continue
                }
                curChar in "()" -> {
                    parseParenthesis(curChar, result)
                }
            }
            i++
        }
        return result
    }

    private fun parseNumber(startIndex: Int, result: MutableList<ExpressionPart>): Int {
        var i = startIndex
        val numberAsString = buildString {
            while(i < calculation.length && calculation[i] in "0123456789.") {
                append(calculation[i])
                i++
            }
        }

        result.add(ExpressionPart.Number(numberAsString.toDouble()))
        return i
    }

    private fun parseParenthesis(curChar: Char, result: MutableList<ExpressionPart>) {
        result.add(
            ExpressionPart.Parenthesis(
                type = when(curChar) {
                    '(' -> ParenthesisType.OpeningType
                    ')' -> ParenthesisType.ClosingType
                    else -> throw IllegalArgumentException("Invalid parenthesis type")
                }
            )
        )
    }
}