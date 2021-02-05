package precompiled

val version = "1.13.0"
val detekt: Configuration by configurations.creating

dependencies {
    detekt("io.gitlab.arturbosch.detekt:detekt-cli:$version")
}

tasks.register<JavaExec>("detekt") {
    group = "verification"
    description = "Static code analysis for Kotlin."
    classpath = detekt
    main = "io.gitlab.arturbosch.detekt.cli.Main"
    val params = arrayOf(
        "--input", "$projectDir",
        "--config", "$rootDir/detekt.yml",
        "--excludes", "**/build/**,**/resources/**",
        "--report", "html:build/reports/detekt.html"
    )
    args(*params)
}
