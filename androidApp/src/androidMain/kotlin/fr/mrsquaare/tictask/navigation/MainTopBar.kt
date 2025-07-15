package fr.mrsquaare.tictask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy

@Composable
fun MainTopBar(tabScreens: List<MainTabScreen>, currentDestination: NavDestination?) {
    tabScreens
        .find { tabScreen ->
            currentDestination?.hierarchy?.any { it.hasRoute(tabScreen.route::class) } == true
        }
        ?.topBar
        ?.invoke()
}
