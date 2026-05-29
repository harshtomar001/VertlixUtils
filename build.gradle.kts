plugins {
    id("com.android.library") version "8.2.2"
    id("maven-publish")
}

repositories {
    google()
    mavenCentral()
}

android {
    namespace = "com.vertlix.utils"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.harshtomar001"
                artifactId = "VertlixUtils"
                version = project.version.toString()

                from(components["release"])
            }
        }
    }
}
}
