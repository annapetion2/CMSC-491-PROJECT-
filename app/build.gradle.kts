plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.anonymousgradingapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.anonymousgradingapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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

        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.opencsv:opencsv:5.9")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.zxing:core:3.4.1")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation ("com.google.mlkit:text-recognition:16.0.0")
    implementation ("com.google.mlkit:barcode-scanning:16.1.1")
    implementation ("com.google.android.gms:play-services-mlkit-text-recognition:19.0.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.google.firebase:firebase-bom:30.1.0")

    implementation("com.amplifyframework:core:1.6.2")
    implementation("com.amplifyframework:aws-auth-cognito:2.14.11")
    implementation("com.amplifyframework:aws-api:2.14.11")
    implementation("com.amazonaws:aws-android-sdk-apigateway-core:2.8.5")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    implementation("androidx.activity:activity:1.8.0")
    implementation("com.amplifyframework:aws-datastore:2.16.1")

}