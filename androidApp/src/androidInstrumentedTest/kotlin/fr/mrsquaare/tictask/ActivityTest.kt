package fr.mrsquaare.tictask

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityTest {
    @get:Rule val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigateToSettings_thenBackToHome_thenBackAgain_shouldCloseApp() {
        composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()

        composeTestRule.onNodeWithText("Settings").performClick()
        composeTestRule.onNodeWithText("Settings Screen").assertIsDisplayed()

        composeTestRule.onNodeWithText("Home").performClick()
        composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()

        val activity = composeTestRule.activity

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressBack()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            activity.lifecycle.currentState == Lifecycle.State.DESTROYED
        }
    }
}
