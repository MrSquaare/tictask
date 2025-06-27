import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Exec
import java.io.File

abstract class SwiftLintExtension {
    abstract val executionPath: Property<String>
    abstract val targetPath: Property<String>
}

class SwiftLintPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            val extension = extensions.create("swiftLint", SwiftLintExtension::class.java)

            extension.executionPath.convention(".")
            extension.targetPath.convention(".")

            afterEvaluate {
                val executionPath = extension.executionPath.get()
                val targetPath = extension.targetPath.get()

                val relativePath = calculateRelativePath(executionPath, targetPath, project)

                tasks.register("swiftLint", Exec::class.java) {
                    group = "verification"
                    description = "Run SwiftLint to check Swift code"

                    workingDir = if (executionPath == ".") {
                        project.projectDir
                    } else {
                        File(project.projectDir, executionPath)
                    }

                    commandLine("swift", "package", "plugin", "--allow-writing-to-package-directory", "swiftlint", relativePath)

                    doFirst {
                        println("Running SwiftLint from: $workingDir")
                        println("Targeting path: $relativePath")
                    }
                }

                tasks.register("swiftLintFix", Exec::class.java) {
                    group = "formatting"
                    description = "Run SwiftLint with auto-fix to correct Swift code issues"

                    workingDir = if (executionPath == ".") {
                        project.projectDir
                    } else {
                        File(project.projectDir, executionPath)
                    }

                    commandLine("swift", "package", "plugin", "--allow-writing-to-package-directory", "swiftlint", "--fix", relativePath)

                    doFirst {
                        println("Running SwiftLint fix from: $workingDir")
                        println("Targeting path: $relativePath")
                    }
                }
            }
        }
    }

    private fun calculateRelativePath(executionPath: String, targetPath: String, project: Project): String {
        if (executionPath == "." && targetPath == ".") {
            return "."
        }

        val executionDir = if (executionPath == ".") {
            project.projectDir
        } else {
            File(project.projectDir, executionPath)
        }

        val targetDir = if (targetPath == ".") {
            project.projectDir
        } else {
            File(project.projectDir, targetPath)
        }

        return executionDir.toPath().relativize(targetDir.toPath()).toString()
    }
}
