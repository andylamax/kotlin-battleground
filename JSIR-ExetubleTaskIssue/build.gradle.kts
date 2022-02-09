plugins {
    val vers = "1.5.30-RC"
    kotlin("js") version vers apply false
    kotlin("plugin.serialization") version vers apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}