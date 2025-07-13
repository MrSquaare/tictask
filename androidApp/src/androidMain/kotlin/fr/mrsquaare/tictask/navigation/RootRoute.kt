package fr.mrsquaare.tictask.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class RootRoute {
    @Serializable data object Main : RootRoute()

    @Serializable data object Create : RootRoute()
}
