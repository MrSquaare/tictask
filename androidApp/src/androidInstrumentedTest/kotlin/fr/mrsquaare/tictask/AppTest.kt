package fr.mrsquaare.tictask

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppNavigationTest {
    @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun app_shouldShowHomeScreenByDefault() {
        composeTestRule.setContent { App() }

        composeTestRule.onNodeWithText("Home Screen").assertIsDisplayed()
    }

    @Test
    fun clickingSettingsTab_shouldShowSettingsScreen() {
        composeTestRule.setContent { App() }

        composeTestRule.onNodeWithText("Settings").performClick()

        composeTestRule.onNodeWithText("Settings Screen").assertIsDisplayed()
    }

    @Test
    fun clickingPlusButtonOnHome_shouldShowCreateScreen() {
        composeTestRule.setContent { App() }

        composeTestRule.onNodeWithText("Home Screen").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Create").performClick()

        composeTestRule.onNodeWithText("Create Screen").assertIsDisplayed()
    }

    @Test
    fun navigateToSettings_thenBackToHome_thenBackAgain_shouldCloseApp() {
        composeTestRule.setContent { App() }

        composeTestRule.onNodeWithText("Home Screen").assertIsDisplayed()

        composeTestRule.onNodeWithText("Settings").performClick()
        composeTestRule.onNodeWithText("Settings Screen").assertIsDisplayed()

        composeTestRule.onNodeWithText("Home").performClick()

        composeTestRule.onNodeWithText("Home Screen").assertIsDisplayed()

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressBack()

        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.activity.isFinishing || composeTestRule.activity.isDestroyed
        }

        assert(composeTestRule.activity.isFinishing || composeTestRule.activity.isDestroyed)
    }
}
