package fr.mrsquaare.tictask.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mrsquaare.tictask.models.Item
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCard(item: Item, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(onClick = onClick, modifier = modifier) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
private fun ItemCardPreview() {
    MaterialTheme {
        ItemCard(
            item = Item(id = 0, title = "Item 1"),
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        )
    }
}
