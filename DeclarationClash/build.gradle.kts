plugins {
    kotlin("multiplatform") version "2.0.0-Beta2"
}

repositories {
    mavenCentral()
}

kotlin {
    jvm { jvmToolchain(18) }
    js(IR) {
        nodejs()
        binaries.library()
//        useEsModules()
        useCommonJs()
//        generateTypeScriptDefinitions()
    }

    sourceSets {
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
