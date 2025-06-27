import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Exec
import java.io.File

abstract class SwiftFormatExtension {
    abstract val executionPath: Property<String>
    abstract val targetPath: Property<String>
}

class SwiftFormatPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            val extension = extensions.create("swiftFormat", SwiftFormatExtension::class.java)

            extension.executionPath.convention(".")
            extension.targetPath.convention(".")

            afterEvaluate {
                val executionPath = extension.executionPath.get()
                val targetPath = extension.targetPath.get()

                val relativePath = calculateRelativePath(executionPath, targetPath, project)

                tasks.register("swiftFormat", Exec::class.java) {
                    group = "formatting"
                    description = "Run SwiftFormat to format Swift code"

                    workingDir = if (executionPath == ".") {
                        project.projectDir
                    } else {
                        File(project.projectDir, executionPath)
                    }

                    commandLine("swift", "run", "swiftformat", relativePath)

                    doFirst {
                        println("Running SwiftFormat from: $workingDir")
                        println("Targeting path: $relativePath")
                    }
                }

                tasks.register("swiftFormatLint", Exec::class.java) {
                    group = "verification" 
                    description = "Run SwiftFormat as linter to check Swift code formatting"

                    workingDir = if (executionPath == ".") {
                        project.projectDir
                    } else {
                        File(project.projectDir, executionPath)
                    }

                    commandLine("swift", "run", "swiftformat", "--lint", relativePath)

                    doFirst {
                        println("Running SwiftFormat lint from: $workingDir")
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
