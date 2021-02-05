import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.dagger() {
    compile(Libraries.dagger)
    kapt(Libraries.daggerCompiler)
}

fun DependencyHandler.daggerAndroid() {
    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)
}

fun DependencyHandler.koin() {
    implementation(Libraries.koinCore)
    implementation(Libraries.koinExt)
//    test(Libraries.koinTest)
}

fun DependencyHandler.koinAndroid() {
    implementation(Libraries.koinAndroid)
    implementation(Libraries.koinAndroidXExt)
    implementation(Libraries.koinAndroidXScope)
    implementation(Libraries.koinAndroidXFragment)
    implementation(Libraries.koinAndroidXViewModel)
}

fun DependencyHandler.coroutines() {
    implementation(Libraries.kotlinCoroutines)
    test(Libraries.kotlinCoroutinestest)
}

fun DependencyHandler.gson() {
    implementation(Libraries.gson)
}

fun DependencyHandler.okHttp() {
    implementation(Libraries.okHttp)
    implementation(Libraries.okHttpLogging)
}

fun DependencyHandler.retrofit() {
    implementation(Libraries.retrofit)
    implementation(Libraries.retfofitGson)
}

fun DependencyHandler.ktx() {
    implementation(Libraries.ktxCore)
    implementation(Libraries.ktxCollections)
    implementation(Libraries.ktxViewModel)
    implementation(Libraries.ktxLiveData)
}

fun DependencyHandler.androidXCore() {
    implementation(Libraries.androidXAppCompat)
    implementation(Libraries.androidxConstraintLayout)
}

fun DependencyHandler.androidXCardView() {
    androidXCore()
    implementation(Libraries.androidXCardView)
}

fun DependencyHandler.androidXRecyclerView() {
    androidXCore()
    implementation(Libraries.androidxRecyclerView)
}

fun DependencyHandler.androidXLifecycle() {
    androidXCore()
    implementation(Libraries.androidxLifecycle)
}

fun DependencyHandler.materialDesign() {
    androidXCore()
    implementation(Libraries.materialDesign)
}

fun DependencyHandler.multidex() {
    implementation(Libraries.multidex)
}

fun DependencyHandler.dataBinding() {
    kapt(Libraries.dataBinding)
}
