package fr.mrsquaare.tictask

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
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

        composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()
    }

    @Test
    fun clickingSettingsTab_shouldShowSettingsScreen() {
        composeTestRule.setContent { App() }

        composeTestRule.onNodeWithText("Settings").performClick()

        composeTestRule.onNodeWithText("Settings Screen").assertIsDisplayed()
    }

    @Test
    fun clickingItemOnHome_shouldShowDetailsScreen() {
        composeTestRule.setContent { App() }

        composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()

        composeTestRule.onNodeWithText("Item 1").performClick()

        composeTestRule.onNodeWithText("Details: Item 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Details Screen: Item 1").assertIsDisplayed()
    }

    @Test
    fun navigateToSettings_thenBackToHome_thenBackAgain_shouldCloseApp() {
        composeTestRule.setContent { App() }

        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()

        composeTestRule.onNodeWithText("Settings").performClick()
        composeTestRule.onNodeWithText("Settings Screen").assertIsDisplayed()

        composeTestRule.onNodeWithText("Home").performClick()
        composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressBack()

        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.activity.isFinishing || composeTestRule.activity.isDestroyed
        }

        assert(composeTestRule.activity.isFinishing || composeTestRule.activity.isDestroyed)
    }

    @Test
    fun navigateToDetailsScreen_thenBack_shouldReturnToHome() {
        composeTestRule.setContent { App() }

        composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()

        composeTestRule.onNodeWithText("Item 1").performClick()

        composeTestRule.onNodeWithText("Details: Item 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Details Screen: Item 1").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()
    }

    @Test
    fun homeScreen_shouldDisplayMultipleItems() {
        composeTestRule.setContent { App() }

        composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 3").assertIsDisplayed()
    }

    @Test
    fun navigateToItem1_thenBackToHome_thenToItem2() {
        composeTestRule.setContent { App() }

        composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()

        composeTestRule.onNodeWithText("Item 1").performClick()

        composeTestRule.onNodeWithText("Details: Item 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Details Screen: Item 1").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 2").assertIsDisplayed()

        composeTestRule.onNodeWithText("Item 2").performClick()

        composeTestRule.onNodeWithText("Details: Item 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Details Screen: Item 2").assertIsDisplayed()
    }
}
