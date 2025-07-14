package fr.mrsquaare.tictask.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fr.mrsquaare.tictask.constants.items
import fr.mrsquaare.tictask.navigation.MainRoute
import fr.mrsquaare.tictask.navigation.MainTabScreen
import fr.mrsquaare.tictask.navigation.RootRoute
import fr.mrsquaare.tictask.ui.ItemCard
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
class HomeScreen(rootNavController: NavController) : MainTabScreen() {

    override val route = MainRoute.Home
    override val selectedIcon = Icons.Filled.Home
    override val unselectedIcon = Icons.Outlined.Home
    override val label = "Home"

    override val topBar: (@Composable () -> Unit) = { TopAppBar(title = { Text("Home") }) }

    override val content: @Composable (Modifier) -> Unit = { modifier ->
        LazyColumn(modifier = modifier.fillMaxSize().padding(16.dp)) {
            items(items) { item ->
                ItemCard(
                    item = item,
                    onClick = { rootNavController.navigate(RootRoute.Details(id = item.id)) },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        val homeScreen = HomeScreen(rootNavController = rememberNavController())
        Scaffold(topBar = { homeScreen.topBar.invoke() }) { innerPadding ->
            homeScreen.content(Modifier.padding(innerPadding))
        }
    }
}
