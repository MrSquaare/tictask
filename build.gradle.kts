plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktfmt)
}

tasks.register("lintKotlin") {
    group = "formatting"
    description = "Lint Kotlin code"
    dependsOn("detekt", "ktfmtCheck")
}

tasks.register("lintKotlinFix") {
    group = "formatting"
    description = "Lint and auto-fix Kotlin code"
    dependsOn("detekt", "ktfmtFormat")
}

tasks.register<Exec>("checkSwiftCLI") {
    group = "verification"
    description = "Check if Swift CLI is available"
    commandLine("swift", "--version")
}

tasks.register<Exec>("swiftLint") {
    group = "formatting"
    description = "Run SwiftLint on Swift code"
    dependsOn("checkSwiftCLI")
    commandLine("swift", "package", "plugin", "--allow-writing-to-package-directory", "swiftlint", "iosApp/")
}

tasks.register<Exec>("swiftLintFix") {
    group = "formatting"
    description = "Run SwiftLint with auto-fix on Swift code"
    dependsOn("checkSwiftCLI")
    commandLine("swift", "package", "plugin", "--allow-writing-to-package-directory", "swiftlint", "--fix", "iosApp/")
}

tasks.register<Exec>("swiftFormat") {
    group = "formatting"
    description = "Run SwiftFormat on Swift code"
    dependsOn("checkSwiftCLI")
    commandLine("swift", "run", "swiftformat", "iosApp/")
}

tasks.register<Exec>("swiftFormatLint") {
    group = "formatting"
    description = "Run SwiftFormat as linter Swift code"
    dependsOn("checkSwiftCLI")
    commandLine("swift", "run", "swiftformat", "--lint", "iosApp/")
}

tasks.register("lintSwift") {
    group = "formatting"
    description = "Lint Swift code"
    dependsOn("swiftLint", "swiftFormatLint")
}

tasks.register("lintSwiftFix") {
    group = "formatting"
    description = "Lint and auto-fix Swift code"
    dependsOn("swiftLintFix", "swiftLint", "swiftFormat")
}

tasks.register("lint") {
    group = "formatting"
    description = "Lint all code"
    dependsOn("lintKotlin", "lintSwift")
}

tasks.register("lintFix") {
    group = "formatting"
    description = "Lint and auto-fix all code"
    dependsOn("lintKotlinFix", "lintSwiftFix")
}
