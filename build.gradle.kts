import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.0-beta04")
        classpath(kotlin("gradle-plugin", version = "1.4.30"))
    }
}

plugins {
    precompiled.`quality-ktlint`
    precompiled.`quality-detekt`
}

allprojects {
    repositories {
        google()
        jcenter()
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
