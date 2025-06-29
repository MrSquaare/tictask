plugins {
    `kotlin-dsl`
}

group = "fr.mrsquaare.plugins"
version = "1.0"

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.8")
}

gradlePlugin {
    plugins {
        create("detektFix") {
            id = "fr.mrsquaare.plugins.detekt-autocorrect"
            implementationClass = "DetektAutoCorrectPlugin"
        }
        create("swiftLintPlugin") {
            id = "fr.mrsquaare.plugins.swiftlint"
            implementationClass = "SwiftLintPlugin"
        }
        create("swiftFormatPlugin") {
            id = "fr.mrsquaare.plugins.swiftformat"
            implementationClass = "SwiftFormatPlugin"
        }
    }
}
