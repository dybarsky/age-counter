plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    appVersions()
    sdkVersions()
    compileOptions()
    kotlinSourceSet()
    testOptions(project.reportsDir())
    lintOptions(project.reportsDir())
    buildFeatures {
        dataBinding = true
    }
    defaultConfig {
        applicationId = "dybarsky.agecounter"
        vectorDrawables.useSupportLibrary = true
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    ktx()
    coroutines()
    dataBinding()
    androidXCore()
    materialDesign()
    androidXRecyclerView()
}
