plugins {
    id(Plugins.Spring.boot)
    id(Plugins.Spring.dependencyManagement)
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.allopen")
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(KotlinX.coroutines.core)
    implementation(KotlinX.coroutines.reactor)
    implementation(Libs.Spring.Boot.webflux)
    implementation(Libs.Spring.Boot.dataJpa)
    implementation(Libs.h2)

    implementation(Libs.caffeine)

    testImplementation(Libs.Spring.Boot.test)

    testImplementation("com.ninja-squad:springmockk:_")
    testImplementation(Testing.kotest.runner.junit5)
    testImplementation(Testing.kotest.property)
    testImplementation(Testing.kotestExtensions.spring)
    testImplementation(Testing.mockK)
}

allOpen {
    annotation("org.springframework.context.annotation.Configuration")
    annotation("org.springframework.context.annotation.Bean")
}
