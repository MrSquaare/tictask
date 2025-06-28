package fr.mrsquaare.tictask

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class AppTest {
    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun clickingButton_shouldRevealContent() {
        composeTestRule.setContent { App() }

        composeTestRule.onNodeWithText("Click me!").performClick()
        composeTestRule.onNodeWithText("Compose:", substring = true).assertIsDisplayed()
    }
}
