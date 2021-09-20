import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

group = "org.example"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

subprojects {
    tasks.withType<KotlinCompile>() {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_15.toString()
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    plugins.withType<JavaPlugin> {
        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.VERSION_15
            targetCompatibility = JavaVersion.VERSION_15
        }
    }
}
