pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Netflix Clone"
include(":app")
include(":features")

// Core
include(":core:data")
include(":core:ui")
include(":core:domain")

// Home
include(":features:home:data")
include(":features:home:domain")
include(":features:home:ui")
include(":features:detail")
