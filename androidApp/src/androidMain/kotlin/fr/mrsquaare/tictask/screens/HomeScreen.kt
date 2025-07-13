package fr.mrsquaare.tictask.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
class HomeScreen(private val onNavigateToCreate: () -> Unit) : MainTabScreen() {

    override val route = MainRoute.Home
    override val selectedIcon = Icons.Filled.Home
    override val unselectedIcon = Icons.Outlined.Home
    override val label = "Home"

    override val topBar: (@Composable () -> Unit) = {
        TopAppBar(
            title = { Text("Home") },
            actions = {
                IconButton(onClick = onNavigateToCreate) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Create")
                }
            },
        )
    }

    override val content: @Composable (Modifier) -> Unit = { modifier ->
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Home Screen", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        val homeScreen = HomeScreen(onNavigateToCreate = {})
        Scaffold(topBar = { homeScreen.topBar.invoke() }) { innerPadding ->
            homeScreen.content(Modifier.padding(innerPadding))
        }
    }
}
