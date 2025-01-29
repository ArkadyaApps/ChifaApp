plugins {
    //id("com.google.devtools.ksp") version "2.0.21-1.0.27"
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
      //classpath(libs.gradle) // Replace YOUR_AGP_VERSION
    //classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.20") // 2.0.21-1.0.27 Replace YOUR_KOTLIN_VERSION
     //   classpath("com.google.dagger:hilt-android-gradle-plugin:2.55")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
