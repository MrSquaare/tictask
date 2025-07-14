package fr.mrsquaare.tictask.constants

import fr.mrsquaare.tictask.models.Item

val items = List(20) { index ->
    Item(
        id = index,
        title = "Item ${index + 1}"
    )
}
