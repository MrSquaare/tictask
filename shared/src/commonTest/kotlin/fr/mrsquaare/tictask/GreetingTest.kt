package fr.mrsquaare.tictask

import kotlin.test.Test
import kotlin.test.assertTrue

class GreetingTest {

    @Test
    fun greet() {
        val greeting = Greeting()
        val result = greeting.greet()
        assertTrue(result.contains("Hello"))
    }
}
