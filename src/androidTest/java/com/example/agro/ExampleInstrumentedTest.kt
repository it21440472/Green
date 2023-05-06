package com.example.agro

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.agro.activities.Validator

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ValidatorInstrumentedTest {

    @Test
    fun validateInput_correctInputs_returnsTrue() {
        val name = "Test Crop"
        val type = "Fruit"
        val size = 5
        val work = 10
        val exp = 2

        val validator = Validator(name, type, size, work, exp)

        val isValid = validator.validateInput()

        assertTrue(isValid)
    }

    @Test
    fun validateInput_emptyName_returnsFalse() {
        val name = ""
        val type = "Fruit"
        val size = 5
        val work = 10
        val exp = 2

        val validator = Validator(name, type, size, work, exp)

        val isValid = validator.validateInput()

        assertFalse(isValid)
    }

    @Test
    fun validateInput_emptyType_returnsFalse() {
        val name = "Test Crop"
        val type = ""
        val size = 5
        val work = 10
        val exp = 2

        val validator = Validator(name, type, size, work, exp)

        val isValid = validator.validateInput()

        assertFalse(isValid)
    }

    @Test
    fun validateInput_negativeSize_returnsFalse() {
        val name = "Test Crop"
        val type = "Fruit"
        val size = -5
        val work = 10
        val exp = 2

        val validator = Validator(name, type, size, work, exp)

        val isValid = validator.validateInput()

        assertFalse(isValid)
    }

    @Test
    fun validateInput_zeroWork_returnsFalse() {
        val name = "Test Crop"
        val type = "Fruit"
        val size = 5
        val work = 0
        val exp = 2

        val validator = Validator(name, type, size, work, exp)

        val isValid = validator.validateInput()

        assertFalse(isValid)
    }

    @Test
    fun validateInput_negativeExp_returnsFalse() {
        val name = "Test Crop"
        val type = "Fruit"
        val size = 5
        val work = 10
        val exp = -2

        val validator = Validator(name, type, size, work, exp)

        val isValid = validator.validateInput()

        assertFalse(isValid)
    }
}

