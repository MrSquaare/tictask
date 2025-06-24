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

tasks.register<Exec>("lintSwift") {
    group = "formatting"
    description = "Lint Swift code"
    commandLine("bash", "-c", """
        swift package plugin --allow-writing-to-package-directory swiftlint iosApp/ && \
        swift run swiftformat --lint iosApp/
    """.trimIndent())
}

tasks.register<Exec>("lintSwiftFix") {
    group = "formatting"
    description = "Lint and auto-fix Swift code"
    commandLine("bash", "-c", """
        swift package plugin --allow-writing-to-package-directory swiftlint --fix iosApp/ && \
        swift package plugin --allow-writing-to-package-directory swiftlint iosApp/ && \
        swift run swiftformat iosApp/
    """.trimIndent())
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
