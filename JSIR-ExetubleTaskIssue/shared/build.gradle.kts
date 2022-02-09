import java.time.Duration

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    js(IR) {
        browser {
            testTask {
                timeout.set(Duration.ofMinutes(1))
                useKarma {
                    useChrome()
                    useChromium()
                    useDebuggableChrome()
                    useFirefox()
                    useFirefoxHeadless()
                    useCoverage()
                    useSourceMapSupport()
                }
            }
        }
        binaries.executable()
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}