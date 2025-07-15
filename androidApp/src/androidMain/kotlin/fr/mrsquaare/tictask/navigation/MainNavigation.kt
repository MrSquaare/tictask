package fr.mrsquaare.tictask.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import fr.mrsquaare.tictask.constants.DEEP_LINK_URI
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
        startDestination = tabScreens.firstOrNull()?.route ?: return,
        modifier = modifier,
    ) {
        tabScreens.forEach { tabScreen ->
            when (tabScreen.route) {
                is MainRoute.Home -> {
                    composable<MainRoute.Home>(
                        deepLinks = listOf(navDeepLink<MainRoute.Home>("${DEEP_LINK_URI}/home"))
                    ) {
                        tabScreen.content(Modifier)
                    }
                }
                is MainRoute.Settings -> {
                    composable<MainRoute.Settings>(
                        deepLinks =
                            listOf(navDeepLink<MainRoute.Settings>("${DEEP_LINK_URI}/settings"))
                    ) {
                        tabScreen.content(Modifier)
                    }
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
            tabScreens =
                listOf(HomeScreen(rootNavController = rememberNavController()), SettingsScreen()),
            navController = rememberNavController(),
        )
    }
}
