plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt.android)
    // alias(libs.plugins.compose.compiler)
    //  id("org.jetbrains.compose") version "1.5.0" // Add this plugin for Compose Compiler
    //id("com.google.devtools.ksp") version "1.8.21-1.0.11" apply false
    kotlin("kapt")
}
android {
    namespace = "com.vsple.salestax_android"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.vsple.salestax_android"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }







        configurations {
            all {
                // exclude group: 'org.jetbrains.kotlin.konan'
                // exclude module: 'konan.target'
            }
        }
        buildFeatures {
            compose =true
        }
        composeOptions {
            kotlinCompilerExtensionVersion= "1.5.3"
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
            compose = true
        }
        composeOptions {
            // kotlinCompilerExtensionVersion = "1.5.7"
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
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
        implementation (libs.ui)
        implementation (libs.androidx.foundation)

        implementation (libs.androidx.runtime.livedata)
        //gson
        implementation(libs.gson)

        //gson converter
        implementation(libs.converter.gson)

        //data store
        implementation (libs.androidx.datastore.preferences)
// System Bar Color Controller
        implementation(libs.accompanist.systemuicontroller)

        // Import the BoM for the Firebase platform
        platform(libs.firebase.bom)
        implementation (libs.firebase.auth)
        implementation (libs.firebase.firestore)

        //hilt
        implementation(libs.hilt.android)
        kapt(libs.hilt.android.compiler)
        implementation(libs.androidx.hilt.navigation.compose)
        //coil
        implementation(libs.coil.compose)

        //retrofit
        implementation(libs.retrofit)
        implementation(libs.logging.interceptor)
        implementation(libs.converter.scalars)

        //gson
        implementation(libs.gson)

        //gson converter
        implementation(libs.converter.gson)

        //data store
        implementation (libs.androidx.datastore.preferences.v100)

        //dynamic pixel skell
        implementation (libs.ssp.android)
        implementation (libs.sdp.android)

        // System Bar Color Controller
        implementation(libs.accompanist.systemuicontroller.v0340)

        // Indicator
        implementation(libs.accompanist.pager.indicators)

        // Google places API
        implementation (libs.places)

        //implementation("com.google.android.gms:play-services-maps:18.2.0")
        implementation(libs.maps.compose)

        implementation(libs.accompanist.permissions)

        implementation(libs.androidx.foundation.v170alpha07)
        //   implementation ("de.charlex.compose:revealswipe:1.0.0")
        implementation(libs.androidx.activity.ktx)


        //  implementation ("implementation 'co.yml:ycharts:2.1.0'")
        implementation(libs.mpandroidchart)
        //json
        implementation(libs.kotlinx.serialization.json)

        implementation(libs.reorderable)


        implementation(libs.androidx.lifecycle.runtime.compose)


        // implementation ("com.patrykandpatrick.vico:compose:2.0.0-alpha.19") // Replace with the latest version

        //date picker
        implementation(libs.core)
        implementation(libs.calendar)
        implementation (libs.androidx.constraintlayout)

        //for wheel picker
        implementation (libs.numberpicker)

        implementation(libs.sh.reorderable)
        
        //window size dependency to get size of screen
        implementation(libs.androidx.material3.windowsizeclass)

    }
// Allow references to generated code
    kapt {
        correctErrorTypes = true
    }
