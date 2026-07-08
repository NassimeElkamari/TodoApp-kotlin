package com.nassime.todo

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginValidationTest {

    @Test
    fun blankEmail_isInvalid() {
        assertFalse(isValidEmail(""))
    }

    @Test
    fun validEmail_isAccepted() {
        assertTrue(isValidEmail("user@example.com"))
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && email.contains("@")
    }
}
