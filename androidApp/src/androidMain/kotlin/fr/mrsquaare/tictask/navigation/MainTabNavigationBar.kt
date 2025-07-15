package fr.mrsquaare.tictask.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController

@Composable
fun MainTabNavigationBar(
    tabScreens: List<MainTabScreen>,
    navController: NavHostController,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        tabScreens.forEach { tabScreen ->
            val selected =
                currentDestination?.hierarchy?.any { it.hasRoute(tabScreen.route::class) } == true

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector =
                            if (selected) {
                                tabScreen.selectedIcon
                            } else {
                                tabScreen.unselectedIcon
                            },
                        contentDescription = tabScreen.label,
                    )
                },
                label = { Text(tabScreen.label) },
                selected = selected,
                onClick = {
                    navController.navigate(tabScreen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}
