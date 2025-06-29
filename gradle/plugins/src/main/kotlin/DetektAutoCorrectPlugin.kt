import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.Plugin
import org.gradle.api.Project

class DetektAutoCorrectPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            createAutoCorrectTasks(project)
        }

        project.gradle.projectsEvaluated {
            createAutoCorrectTasks(project)
        }
    }

    private fun createAutoCorrectTasks(project: Project) {
        val detektTasks = project.tasks.withType(Detekt::class.java).toList()

        detektTasks.forEach { originalTask ->
            if (originalTask.name.endsWith("AutoCorrect")) return@forEach

            val autoCorrectTaskName = "${originalTask.name}AutoCorrect"

            if (project.tasks.findByName(autoCorrectTaskName) != null) {
                return@forEach
            }

            val autoCorrectTask = project.tasks.register(autoCorrectTaskName, Detekt::class.java).get()

            configureAutoCorrectTask(autoCorrectTask, originalTask)
        }
    }

    private fun configureAutoCorrectTask(autoCorrectTask: Detekt, originalTask: Detekt) {
        autoCorrectTask.group = originalTask.group
        autoCorrectTask.description = "Auto-correcting version of ${originalTask.name}"
        autoCorrectTask.buildUponDefaultConfig = originalTask.buildUponDefaultConfig
        autoCorrectTask.config.setFrom(originalTask.config)
        autoCorrectTask.source = originalTask.source
        autoCorrectTask.autoCorrect = true
    }
}
