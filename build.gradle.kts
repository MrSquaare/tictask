plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.ktfmt) apply false
    id("fr.mrsquaare.plugins.detekt-autocorrect") apply false
}
