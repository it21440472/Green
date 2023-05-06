package com.example.agro

import com.example.agro.activities.Validator
import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun testValidatorWithValidInput() {
        // Create a new Validator with valid input
        val validator = Validator("crop1", "type1", 10, 20, 30)

        // Check if all input parameters are valid
        assertTrue(validator.isInputValid())
    }

    @Test
    fun testValidatorWithInvalidName() {
        // Create a new Validator with an empty name
        val validator = Validator("", "type1", 10, 20, 30)

        // Check if the name is invalid
        assertFalse(validator.isNameValid())
        assertFalse(validator.isInputValid())
    }

    @Test
    fun testValidatorWithInvalidType() {
        // Create a new Validator with an empty type
        val validator = Validator("crop1", "", 10, 20, 30)

        // Check if the type is invalid
        assertFalse(validator.isTypeValid())
        assertFalse(validator.isInputValid())
    }

    @Test
    fun testValidatorWithInvalidSize() {
        // Create a new Validator with a non-positive size
        val validator = Validator("crop1", "type1", -10, 20, 30)

        // Check if the size is invalid
        assertFalse(validator.isSizeValid())
        assertFalse(validator.isInputValid())
    }

    @Test
    fun testValidatorWithInvalidWork() {
        // Create a new Validator with a non-positive work value
        val validator = Validator("crop1", "type1", 10, -20, 30)

        // Check if the work value is invalid
        assertFalse(validator.isWorkValid())
        assertFalse(validator.isInputValid())
    }

    @Test
    fun testValidatorWithInvalidExp() {
        // Create a new Validator with a non-positive exp value
        val validator = Validator("crop1", "type1", 10, 20, -30)

        // Check if the exp value is invalid
        assertFalse(validator.isExpValid())
        assertFalse(validator.isInputValid())
    }

}
