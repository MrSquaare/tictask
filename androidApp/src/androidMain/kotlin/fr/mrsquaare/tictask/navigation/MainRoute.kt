package fr.mrsquaare.tictask.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class MainRoute {
    @Serializable data object Home : MainRoute()

    @Serializable data object Settings : MainRoute()
}
