repositories {
    jcenter()
}

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
}


dependencies {
    implementation("com.android.tools.build:gradle:4.2.0-beta04")
    implementation(kotlin("gradle-plugin", version = "1.4.30"))
}
