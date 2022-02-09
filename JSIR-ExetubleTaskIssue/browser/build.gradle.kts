plugins {
    kotlin("js")
}

kotlin {
    js(IR) {
        browser {
            testTask {
                useMocha {
                    
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        val main by getting {
            dependencies {
                api(project(":shared"))
            }
        }
        val test by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}