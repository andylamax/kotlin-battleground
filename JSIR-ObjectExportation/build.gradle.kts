plugins {
    kotlin("js") version "1.6.10"
}

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        browser()
        nodejs()
    }

    sourceSets {
        val test by getting {
            dependencies {
                implementation("tz.co.asoft:expect-coroutines:1.4.62")
            }
        }
    }
}
