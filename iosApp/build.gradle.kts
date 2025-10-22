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

afterEvaluate {
    tasks.named("swiftFormat").configure {
        mustRunAfter("swiftLintFix")
    }
}

tasks.register("lintSwiftFix") {
    group = "formatting"
    description = "Lint with auto-fix Swift code"
    dependsOn("swiftLintFix", "swiftFormat")
}

tasks.register<Exec>("build") {
    group = "build"
    description = "Build iOS app"
    workingDir = projectDir

    commandLine(
        "xcodebuild",
        "build",
        "-scheme", "Tictask",
        "-destination", "platform=iOS Simulator,name=iPhone 17,OS=26.0",
        "CODE_SIGN_IDENTITY=",
        "CODE_SIGNING_REQUIRED=NO",
        "CODE_SIGNING_ALLOWED=NO"
    )
}

tasks.register<Exec>("uiTest") {
    group = "verification"
    description = "Run iOS UI tests using test plan"
    workingDir = projectDir

    commandLine(
        "xcodebuild",
        "test",
        "-scheme", "Tictask",
        "-destination", "platform=iOS Simulator,name=iPhone 17,OS=26.0",
        "CODE_SIGN_IDENTITY=",
        "CODE_SIGNING_REQUIRED=NO",
        "CODE_SIGNING_ALLOWED=NO"
    )
}
