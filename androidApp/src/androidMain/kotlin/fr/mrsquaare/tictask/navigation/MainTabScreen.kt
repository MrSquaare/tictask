package fr.mrsquaare.tictask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

abstract class MainTabScreen {
    abstract val route: MainRoute
    abstract val selectedIcon: ImageVector
    abstract val unselectedIcon: ImageVector
    abstract val label: String

    open val topBar: (@Composable () -> Unit)? = null
    abstract val content: @Composable (Modifier) -> Unit
}
