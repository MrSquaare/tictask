package fr.mrsquaare.tictask

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import fr.mrsquaare.tictask.navigation.RootNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    MaterialTheme { RootNavigation(navController = navController, modifier = modifier) }
}

@Preview
@Composable
private fun AppPreview() {
    App()
}
