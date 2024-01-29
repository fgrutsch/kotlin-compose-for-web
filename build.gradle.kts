plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.9.21"

    id("org.jetbrains.compose")
}

group = "com.fgrutsch"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val patternFlyVersion: String by project

kotlin {
    jvmToolchain(17)

    js(IR) {
        browser {
            runTask {
                devServer = devServer?.copy(port = 3000)
            }

            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
                outputFileName = "app.js"
            }

            webpackTask {
                mainOutputFileName = "app.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation("com.arkivanov.decompose:decompose:2.2.2")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:2.2.2-compose-experimental")

            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation("org.jetbrains.kotlin-wrappers:kotlin-browser-js:1.0.0-pre.690")

                implementation(npm("@patternfly/patternfly", patternFlyVersion))
            }
        }
    }

}
