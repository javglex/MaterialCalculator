package com.skymonkey.materialcalculator.domain

/**
 * Represents different operations that our calculator supports
 */
enum class Operation(val symbol: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('x'),
    DIVIDE('/'),
    PERCENT('%')
}
val operationSymbols = Operation.entries.map {
    it.symbol
}.joinToString("")

fun operationFromSymbol(symbol: Char): Operation {
    return Operation.entries.find { it.symbol == symbol } ?: throw IllegalArgumentException("Invalid Symbol")
}