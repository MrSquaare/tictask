package fr.mrsquaare.tictask

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeepLinkTest {
    @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun deepLink_homeScreen_navigatesToHomeScreen() {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("http://tictask.mrsquaare.fr/home")).apply {
                setPackage("fr.mrsquaare.tictask")
            }

        ActivityScenario.launch<MainActivity>(intent).use {
            composeTestRule.waitUntil(timeoutMillis = 5000) {
                composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
                true
            }
        }
    }

    @Test
    fun deepLink_settingsScreen_navigatesToSettingsScreen() {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("http://tictask.mrsquaare.fr/settings")).apply {
                setPackage("fr.mrsquaare.tictask")
            }

        ActivityScenario.launch<MainActivity>(intent).use {
            composeTestRule.waitUntil(timeoutMillis = 5000) {
                composeTestRule.onNodeWithText("Settings Screen").assertIsDisplayed()
                true
            }
        }
    }

    @Test
    fun deepLink_detailsScreen_navigatesToDetailsScreenWithCorrectItem() {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("http://tictask.mrsquaare.fr/details/0")).apply {
                setPackage("fr.mrsquaare.tictask")
            }

        ActivityScenario.launch<MainActivity>(intent).use {
            composeTestRule.waitUntil(timeoutMillis = 5000) {
                composeTestRule.onNodeWithText("Details: Item 1").assertIsDisplayed()
                composeTestRule.onNodeWithText("Details Screen: Item 1").assertIsDisplayed()
                true
            }
        }
    }

    @Test
    fun deepLink_unknownRoute_fallsBackToHomeScreen() {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("http://tictask.mrsquaare.fr/unknown")).apply {
                setPackage("fr.mrsquaare.tictask")
            }

        ActivityScenario.launch<MainActivity>(intent).use {
            composeTestRule.waitUntil(timeoutMillis = 5000) {
                composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
                composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()
                true
            }
        }
    }
}
