package fr.mrsquaare.tictask.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.mrsquaare.tictask.navigation.MainRoute
import fr.mrsquaare.tictask.navigation.MainTabScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
class SettingsScreen : MainTabScreen() {

    override val route = MainRoute.Settings
    override val selectedIcon = Icons.Filled.Settings
    override val unselectedIcon = Icons.Outlined.Settings
    override val label = "Settings"

    override val topBar: (@Composable () -> Unit) = { TopAppBar(title = { Text("Settings") }) }

    override val content: @Composable (Modifier) -> Unit = { modifier ->
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Settings Screen", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    MaterialTheme {
        val settingsScreen = SettingsScreen()
        Scaffold(topBar = { settingsScreen.topBar?.invoke() }) { innerPadding ->
            settingsScreen.content(Modifier.padding(innerPadding))
        }
    }
}
