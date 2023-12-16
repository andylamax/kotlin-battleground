import org.jetbrains.kotlin.gradle.targets.js.ir.KotlinJsIrLink

plugins {
    kotlin("multiplatform") version "1.9.20-RC"
}

repositories {
    mavenCentral()
}

version = "0.0.1"

kotlin {
    js(IR) {
        val main by compilations
        main.outputModuleName = "battleground"
        browser { }
        generateTypeScriptDefinitions()
        binaries.library()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
				implementation("io.ktor:ktor-client-core:2.3.4")
            }
        }
    }
}