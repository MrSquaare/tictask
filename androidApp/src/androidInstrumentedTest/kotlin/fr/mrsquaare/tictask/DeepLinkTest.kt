package fr.mrsquaare.tictask

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeepLinkTest {
    @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun deepLink_homeScreen_navigatesToHomeScreen() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://tictask.mrsquaare.fr/home"))
            .apply {
                setClassName(
                    InstrumentationRegistry.getInstrumentation().targetContext,
                    "fr.mrsquaare.tictask.MainActivity"
                )
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        context.startActivity(intent)

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            try {
                composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
                true
            } catch (e: Exception) {
                false
            }
        }

        composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()
    }

    @Test
    fun deepLink_settingsScreen_navigatesToSettingsScreen() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://tictask.mrsquaare.fr/settings"))
            .apply {
                setClassName(
                    InstrumentationRegistry.getInstrumentation().targetContext,
                    "fr.mrsquaare.tictask.MainActivity"
                )
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        context.startActivity(intent)

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            try {
                composeTestRule.onNodeWithText("Settings Screen").assertIsDisplayed()
                true
            } catch (e: Exception) {
                false
            }
        }

        composeTestRule.onNodeWithText("Settings Screen").assertIsDisplayed()
    }

    @Test
    fun deepLink_detailsScreen_navigatesToDetailsScreenWithCorrectItem() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://tictask.mrsquaare.fr/details/0"))
            .apply {
                setClassName(
                    InstrumentationRegistry.getInstrumentation().targetContext,
                    "fr.mrsquaare.tictask.MainActivity"
                )
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        context.startActivity(intent)
        
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            try {
                composeTestRule.onNodeWithText("Details: Item 1").assertIsDisplayed()
                true
            } catch (e: Exception) {
                false
            }
        }
        
        composeTestRule.onNodeWithText("Details: Item 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Details Screen: Item 1").assertIsDisplayed()
    }

    @Test
    fun deepLink_unknownRoute_fallsBackToHomeScreen() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://tictask.mrsquaare.fr/unknown"))
            .apply {
                setClassName(
                    InstrumentationRegistry.getInstrumentation().targetContext,
                    "fr.mrsquaare.tictask.MainActivity"
                )
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        context.startActivity(intent)

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            try {
                composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
                true
            } catch (e: Exception) {
                false
            }
        }

        // Should fallback to home screen
        composeTestRule.onAllNodes(hasText("Home"))[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()
    }
}
