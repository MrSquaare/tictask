package fr.mrsquaare.tictask.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.mrsquaare.tictask.screens.HomeScreen
import fr.mrsquaare.tictask.screens.SettingsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainNavigation(
    tabScreens: List<MainTabScreen>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = tabScreens.first().route,
        modifier = modifier,
    ) {
        tabScreens.forEach { tabScreen ->
            when (tabScreen.route) {
                is MainRoute.Home -> {
                    composable<MainRoute.Home> { tabScreen.content(Modifier) }
                }
                is MainRoute.Settings -> {
                    composable<MainRoute.Settings> { tabScreen.content(Modifier) }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainNavigationPreview() {
    MaterialTheme {
        MainNavigation(
            tabScreens = listOf(HomeScreen(onNavigateToCreate = {}), SettingsScreen()),
            rootNavController = rememberNavController(),
            navController = rememberNavController(),
        )
    }
}
