package fr.mrsquaare.tictask

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform