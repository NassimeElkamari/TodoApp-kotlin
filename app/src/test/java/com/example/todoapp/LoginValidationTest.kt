package com.example.todoapp

import org.junit.Assert.assertEquals
import org.junit.Test

class IntentionalFailureTest {

    @Test
    fun ciPipeline_shouldFail_onPurpose() {
        assertEquals("LoginValidationTest for CI/CD validation", 1, 2)
    }
}
