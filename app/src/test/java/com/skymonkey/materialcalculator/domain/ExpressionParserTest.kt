package com.skymonkey.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `simple expression is properly parsed`() {
        // GIVEN
        parser = ExpressionParser("3+5-3x4/3")
        // DO
        val actual = parser.parse()
        // ASSERT
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0)
        )

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `expression with parenthesis is properly parsed`() {
        parser = ExpressionParser("4-(4x5)")

        val actual = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parenthesis(ParenthesisType.OpeningType),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parenthesis(ParenthesisType.ClosingType)
        )

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `expression with invalid characters is properly parsed`() {
        parser = ExpressionParser("4k-(4x5g)")

        val actual = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parenthesis(ParenthesisType.OpeningType),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parenthesis(ParenthesisType.ClosingType)
        )

        assertThat(expected).isEqualTo(actual)
    }

    //TODO: write more unit tests e.g test decimal values, invalid characters etc.
}