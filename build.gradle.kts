// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.ksp) apply false

    // Navigation
    alias(libs.plugins.navigation.safeargs.kotlin) apply false

    // DI
    alias(libs.plugins.dagger.hilt.android) apply false
}