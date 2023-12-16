plugins {
    kotlin("multiplatform") version "2.0.0-Beta2"
}

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        browser()
    }

    sourceSets {
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
