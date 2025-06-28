import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktfmt)
    id("fr.mrsquaare.plugins.detekt-autocorrect")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "fr.mrsquaare.tictask.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

detekt { buildUponDefaultConfig = true }

ktfmt { kotlinLangStyle() }

tasks.register("lintKotlin") {
    group = "formatting"
    description = "Lint Kotlin code"

    dependsOn(
        "detektMetadataCommonMain",
        "detektAndroidDebug",
        "detektMetadataIosMain",
        "ktfmtCheck",
    )
}

gradle.projectsEvaluated {
    val t1 = tasks.findByName("detektMetadataCommonMainAutoCorrect")
    val t2 = tasks.findByName("detektAndroidDebugAutoCorrect")
    val t3 = tasks.findByName("detektMetadataIosMainAutoCorrect")
    val t4 = tasks.findByName("ktfmtFormat")

    if (t1 != null && t2 != null) {
        t2.mustRunAfter(t1)
    }
    if (t2 != null && t3 != null) {
        t3.mustRunAfter(t2)
    }
    if (t3 != null && t4 != null) {
        t4.mustRunAfter(t3)
    }
}

tasks.register("lintKotlinFix") {
    group = "formatting"
    description = "Lint with auto-fix Kotlin"

    dependsOn(
        "detektMetadataCommonMainAutoCorrect",
        "detektAndroidDebugAutoCorrect",
        "detektMetadataIosMainAutoCorrect",
        "ktfmtFormat",
    )
}
