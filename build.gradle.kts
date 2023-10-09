plugins {
    kotlin("multiplatform")
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
            runTask(Action {
                devServer = devServer?.copy(port = 3000)
            })

            commonWebpackConfig(Action {
                cssSupport {
                    enabled.set(true)
                }
                outputFileName = "app.js"
            })

            webpackTask(Action {
                mainOutputFileName = "app.js"
            })
        }
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(npm("@patternfly/patternfly", patternFlyVersion))
            }
        }
    }

}
