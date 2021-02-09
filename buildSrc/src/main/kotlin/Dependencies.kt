import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.coroutines() {
    implementation(Libraries.kotlinCoroutines)
    test(Libraries.kotlinCoroutinesTest)
}

fun DependencyHandler.ktx() {
    implementation(Libraries.ktxCore)
}

fun DependencyHandler.androidXCore() {
    implementation(Libraries.androidXAppCompat)
    implementation(Libraries.androidxConstraintLayout)
}

fun DependencyHandler.materialDesign() {
    androidXCore()
    implementation(Libraries.materialDesign)
}

fun DependencyHandler.dataBinding() {
    kapt(Libraries.dataBinding)
}
