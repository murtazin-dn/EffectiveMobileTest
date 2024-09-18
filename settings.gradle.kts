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

rootProject.name = "EffectiveMobileTest"
include(":app")
include(":features")
include(":features:search")
include(":core")
include(":features:favorites")
include(":features:responses")
include(":features:messages")
include(":features:profile")
include(":core:designsystem")
include(":core:network")
include(":core:common")
include(":core:database")
