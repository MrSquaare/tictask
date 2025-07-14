package fr.mrsquaare.tictask.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fr.mrsquaare.tictask.navigation.MainNavigation
import fr.mrsquaare.tictask.navigation.MainTabNavigationBar
import fr.mrsquaare.tictask.navigation.MainTopBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(rootNavController: NavHostController, modifier: Modifier = Modifier) {
    val tabNavController = rememberNavController()
    val navBackStackEntry by tabNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val tabScreens =
        listOf(
            HomeScreen(rootNavController = rootNavController),
            SettingsScreen(),
        )

    Scaffold(
        topBar = { MainTopBar(tabScreens = tabScreens, currentDestination = currentDestination) },
        bottomBar = {
            MainTabNavigationBar(
                tabScreens = tabScreens,
                navController = tabNavController,
                currentDestination = currentDestination,
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        MainNavigation(
            tabScreens = tabScreens,
            navController = tabNavController,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MaterialTheme { MainScreen(rootNavController = rememberNavController()) }
}
