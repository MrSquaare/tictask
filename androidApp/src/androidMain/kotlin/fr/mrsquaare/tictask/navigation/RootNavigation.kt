package fr.mrsquaare.tictask.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import fr.mrsquaare.tictask.constants.DEEP_LINK_URI
import fr.mrsquaare.tictask.screens.DetailsScreen
import fr.mrsquaare.tictask.screens.MainScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

const val ANIMATION_SPEED_MS = 300

@Composable
fun RootNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = RootRoute.Main, modifier = modifier) {
        composable<RootRoute.Main>(
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth / 3 },
                    animationSpec = tween(ANIMATION_SPEED_MS),
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth / 3 },
                    animationSpec = tween(ANIMATION_SPEED_MS),
                )
            },
        ) {
            MainScreen(rootNavController = navController)
        }
        composable<RootRoute.Details>(
            deepLinks = listOf(navDeepLink<RootRoute.Details>("${DEEP_LINK_URI}/details")),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(ANIMATION_SPEED_MS),
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(ANIMATION_SPEED_MS),
                )
            },
        ) {
            val args = it.toRoute<RootRoute.Details>()

            DetailsScreen(id = args.id, onNavigateBack = { navController.navigateUp() })
        }
    }
}

@Preview
@Composable
private fun RootNavigationPreview() {
    MaterialTheme { RootNavigation(navController = rememberNavController()) }
}
