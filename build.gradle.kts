plugins {
    kotlin("jvm") version "2.3.21"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.microsoft.playwright:playwright:1.52.0")
    testImplementation("org.testng:testng:7.7.0")
}

tasks.test {
    useJUnitPlatform()
    useTestNG()
}

tasks.register("playwrightShowTrace", JavaExec::class) {
    mainClass.set("com.microsoft.playwright.CLI")
    classpath = sourceSets["main"].runtimeClasspath
    args = listOf("show-trace", "trace.zip")  // или полный путь, например "build/reports/traces/trace.zip"
}

// Добавите этот блок:
kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}