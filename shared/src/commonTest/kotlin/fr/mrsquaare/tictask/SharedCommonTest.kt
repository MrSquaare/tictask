package fr.mrsquaare.tictask

import kotlin.test.Test
import kotlin.test.assertTrue

class SharedCommonTest {

    @Test
    fun testGreeting() {
        val greeting = Greeting()
        val result = greeting.greet()
        assertTrue(result.contains("Hello"))
    }
}