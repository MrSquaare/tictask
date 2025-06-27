plugins {
    id("fr.mrsquaare.plugins.swiftlint")
    id("fr.mrsquaare.plugins.swiftformat")
}

swiftLint {
    executionPath.set("..")
    targetPath.set(".")
}

swiftFormat {
    executionPath.set("..")
    targetPath.set(".")
}

tasks.register("lintSwift") {
    group = "verification"
    description = "Lint Swift code"
    dependsOn("swiftLint", "swiftFormatLint")
}

tasks.register("lintSwiftFix") {
    group = "formatting"
    description = "Lint with auto-fix Swift code"
    dependsOn("swiftLintFix", "swiftFormat")
}
