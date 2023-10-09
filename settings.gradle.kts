pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        kotlin("multiplatform").version(extra["kotlin.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
    }

}

rootProject.name = "kotlin-compose-for-web"
