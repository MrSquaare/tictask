package fr.mrsquaare.tictask.constants

import fr.mrsquaare.tictask.models.Item

const val ITEMS_NB = 20

val items = List(ITEMS_NB) { index -> Item(id = index, title = "Item ${index + 1}") }
