package fr.mrsquaare.tictask

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityTest {
    @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun navigateToSettings_thenBackToHome_thenBackAgain_shouldCloseApp() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()

            composeTestRule.onNodeWithText("Settings").performClick()
            composeTestRule.onNodeWithText("Settings Screen").assertIsDisplayed()

            composeTestRule.onNodeWithText("Home").performClick()
            composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()

            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            device.pressBack()

            composeTestRule.waitUntil(timeoutMillis = 5000) { scenario.state.name == "DESTROYED" }
        }
    }
}
