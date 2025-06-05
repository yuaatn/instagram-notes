buildscript {
    extra.apply {
        set(
            "room_version",
            "2.7.1"
        )
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    id("com.google.devtools.ksp") version "2.1.10-1.0.31" apply false
    id("com.google.dagger.hilt.android") version "2.55" apply false
}