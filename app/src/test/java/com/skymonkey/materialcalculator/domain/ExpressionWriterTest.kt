package com.skymonkey.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ExpressionWriterTest {
    private lateinit var writer: ExpressionWriter

    @Before
    fun setup() {
        writer = ExpressionWriter()
    }

    @Test
    fun `initial parenthesis parsed`() {
        writer.processAction(CalculatorAction.Parenthesis)
        writer.processAction(CalculatorAction.Number(5))
        writer.processAction(CalculatorAction.Op(Operation.ADD))
        writer.processAction(CalculatorAction.Number(4))
        writer.processAction(CalculatorAction.Parenthesis)
        assertThat(writer.expression).isEqualTo("(5+4)")
    }

    @Test
    fun `closing parenthesis at the start not parsed`() {
        writer.processAction(CalculatorAction.Parenthesis)
        writer.processAction(CalculatorAction.Parenthesis)
        // parenthesis should always surround a number, so it doesn't make sense to close them.
        assertThat(writer.expression).isEqualTo("((")
    }

    @Test
    fun `parenthesis around a number are parsed`() {
        writer.processAction(CalculatorAction.Parenthesis)
        writer.processAction(CalculatorAction.Number(6))
        writer.processAction(CalculatorAction.Parenthesis)
        // parenthesis should always surround a number, so it doesn't make sense to close them.
        assertThat(writer.expression).isEqualTo("(6)")
    }
}