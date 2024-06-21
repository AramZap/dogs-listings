plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("org.jetbrains.kotlin.android.extensions")
    id("org.jetbrains.kotlin.kapt")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.dogs"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dogs"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
//        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        dataBinding = true
        compose = true
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

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation (libs.gson)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.preference.ktx)
    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.rxjava)
    implementation(libs.converter.gson)
    implementation(libs.rxjava3.retrofit.adapter)
    implementation(libs.rxandroid)
    implementation(libs.androidx.palette)

//    implementation("com.google.firebase:firebase-core:16.0.8")

//    implementation(libs.androidx.room.compiler)

//    implementation(libs.multidex)

    val room_version = "2.6.1"
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")

    annotationProcessor(libs.compiler)
    annotationProcessor(libs.androidx.room.compiler)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}