import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.11"

project {

    buildType(UnitAndIntegrationTests)

    subProject(Official)
    subProject(Release)
    subProject(Developer)
}

object UnitAndIntegrationTests : BuildType({
    name = "Unit and Integration Tests"
})


object Developer : Project({
    name = "Developer"

    buildType(Developer_DeveloperBuild)
})

object Developer_DeveloperBuild : BuildType({
    name = "Developer Build"

    steps {
        script {
            name = "Echo"
            id = "Echoe"
            scriptContent = """echoe "test""""
        }
        script {
            name = "Echo Again"
            id = "Echo_Again"
            scriptContent = """echo "Again""""
        }
    }
})


object Official : Project({
    name = "Official"

    buildType(Official_OfficialBuild)
})

object Official_OfficialBuild : BuildType({
    name = "Official Build"
})


object Release : Project({
    name = "Release"

    buildType(Release_ReleaseBuild)
})

object Release_ReleaseBuild : BuildType({
    name = "Release Build"

    steps {
        script {
            name = "echo"
            id = "echo"
            scriptContent = """echo "test""""
        }
    }
})
