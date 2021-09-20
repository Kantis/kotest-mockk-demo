plugins {
    id(Plugins.Spring.boot)
    id(Plugins.Spring.dependencyManagement)
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.allopen")
}

dependencies {
    implementation(project(":shared"))
    implementation(Libs.Spring.Boot.web)
    implementation(Libs.Spring.Boot.dataJpa)
    implementation(Libs.h2)

    testImplementation(Libs.Spring.Boot.test)

    testImplementation(Testing.kotest.runner.junit5)
    testImplementation("io.kotest:kotest-framework-datatest:_")
    testImplementation(Testing.kotest.property)
    testImplementation(Testing.kotestExtensions.spring)
    testImplementation(Testing.mockK)
}

allOpen {
    annotation("org.springframework.context.annotation.Configuration")
    annotation("org.springframework.context.annotation.Bean")
}
