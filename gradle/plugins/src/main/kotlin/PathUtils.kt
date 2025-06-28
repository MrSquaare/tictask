import org.gradle.api.Project
import java.io.File

object PathUtils {
    fun calculateRelativePath(executionPath: String, targetPath: String, project: Project): String {
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
