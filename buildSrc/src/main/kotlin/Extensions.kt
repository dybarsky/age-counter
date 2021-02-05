import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import java.io.File

internal fun DependencyHandler.compile(depName: String) = add("compileOnly", depName)
internal fun DependencyHandler.implementation(depName: String) = add("implementation", depName)
internal fun DependencyHandler.test(depName: String) = add("testImplementation", depName)
internal fun DependencyHandler.api(depName: String) = add("api", depName)
internal fun DependencyHandler.kapt(depName: String) = add("kapt", depName)

fun BaseExtension.kotlinSourceSet() = sourceSets {
    map { it.java.srcDir("src/${it.name}/kotlin") }
}

fun BaseExtension.testOptions(reportsDir: String) = testOptions {
    reportDir = "$reportsDir/tests"
}

fun BaseExtension.compileOptions() = compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

fun BaseExtension.appVersions() = defaultConfig {
    defaultConfig {
        versionCode = Versions.androidVersionCode
        versionName = Versions.androidVersionName
    }
}

fun BaseExtension.sdkVersions() = defaultConfig {
    minSdkVersion(Versions.androidSdkMin)
    targetSdkVersion(Versions.androidSdkTarget)
    compileSdkVersion(Versions.androidSdkCompile)
    buildToolsVersion(Versions.androidBuildTools)
}

fun BaseExtension.lintOptions(reportsDir: String) = lintOptions {
    isCheckDependencies = true
    isAbortOnError = true
    xmlOutput = File("$reportsDir/lint/lint-results.xml")
    htmlOutput = File("$reportsDir/reports/lint/lint-results.html")
}

fun Project.reportsDir() = "${buildDir.absolutePath}/reports"
