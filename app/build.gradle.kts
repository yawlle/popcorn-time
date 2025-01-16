import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")

    alias(libs.plugins.composeCompiler)
}

val localProperties = File(rootDir, "local.properties")
val omdbApiKey: String = if (localProperties.exists()) {
    Properties().apply { load(localProperties.inputStream()) }.getProperty("OMDB_API_KEY", "")
} else {
    ""
}

android {
    namespace = "com.example.popcorntime"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.popcorntime"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "OMDB_API_KEY", "\"$omdbApiKey\"")
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "OMDB_API_KEY", "\"$omdbApiKey\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.lottie)
    implementation(libs.splash)
    implementation(libs.retrofit)
    implementation(libs.gson)
//    implementation(libs.dagger)
//    implementation(libs.dagger.compiler)
    implementation(libs.okhttp)
    implementation(libs.loggingInterceptor)
    implementation(libs.coil)
    implementation(libs.coilCompose)
    implementation(libs.lifecycleViewmodelCompose)

//    annotationProcessor(libs.dagger.compiler)
//    annotationProcessor(libs.google.dagger.android.processor)

    implementation(libs.dagger.v216)
    kapt(libs.dagger.compiler.v216)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}