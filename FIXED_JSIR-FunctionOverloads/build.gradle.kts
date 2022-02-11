plugins {
    kotlin("js") version "1.6.20-M1"
}

repositories {
    mavenCentral()
}

val tmp = 4

kotlin {
    js(IR) {
        browser()
    }

    sourceSets {
        val test by getting {
            dependencies {
                implementation("tz.co.asoft:expect-coroutines:1.4.61")
            }
        }
    }
}
