import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")

}

// Load API key from `apikey.properties`
val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties = Properties()
if (apikeyPropertiesFile.exists()) {
    apikeyProperties.load(FileInputStream(apikeyPropertiesFile))
} else {
    println("WARNING: apikey.properties file not found!")
}


android {
    namespace = "muhammadfauzi.polbeng.ac.id.githubprofile"
    compileSdk = 35

    defaultConfig {
        applicationId = "muhammadfauzi.polbeng.ac.id.githubprofile"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        if (apikeyProperties.containsKey("ACCESS_TOKEN")) {
            buildConfigField(
                "String",
                "ACCESS_TOKEN",
                "\"${apikeyProperties["ACCESS_TOKEN"]}\""
            )
        } else {
            println("WARNING: ACCESS_TOKEN not found in apikey.properties!")
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Library untuk menampilkan gambar bergerak splashscreen
    implementation("com.airbnb.android:lottie:5.2.0")

    // Library untuk menampilkan gambar profil bulat
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Library untuk memuat gambar dari URL
    implementation("com.github.bumptech.glide:glide:4.13.2")
    kapt("com.github.bumptech.glide:compiler:4.13.2")

    // Library untuk melakukan request API
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Library untuk logging API request
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")
}
