plugins {
   
    id("com.android.library") version "8.2.2" 

    id("maven-publish")
}

android {
    namespace = "com.vertlix.utils"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

