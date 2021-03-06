import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.skanderjabouzi.nbateamviewer"
        minSdkVersion(15)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments = mapOf(
            "disableAnalytics" to "true"
        )

    }

    buildTypes {
        named("release"){
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    flavorDimensions("NBATeamViewer")
    productFlavors {
        create("dev") {
            buildConfigField( "String", "TYPE", "\"dev\"")
            buildConfigField( "String", "BASE_URL",  "\"http://skanderjabouzi.com/nbateamviewer/\"")
        }
        create("mock") {
            buildConfigField("String", "TYPE", "\"mock\"")
            buildConfigField("String", "BASE_URL",  "\"http:/mock.api/\"")
        }
    }

    variantFilter {
        if (buildType.name == "release" && flavors[0].name == "development") {
            ignore = true
        }
        if (buildType.name == "debug" && flavors[0].name == "production") {
            ignore = true
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib", KotlinCompilerVersion.VERSION))
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")
    implementation("androidx.room:room-runtime:2.2.6")
    implementation("androidx.room:room-ktx:2.2.6")
    kapt("androidx.room:room-compiler:2.2.6")
    api("com.google.dagger:dagger:2.28.3")
    kapt("com.google.dagger:dagger-compiler:2.28.3")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("androidx.navigation:navigation-runtime-ktx:2.3.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.4")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")

    testImplementation("org.mockito:mockito-core:3.3.3")
    testImplementation("org.mockito:mockito-inline:3.3.3")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test:core:1.3.0")
    testImplementation("androidx.test.ext:junit:1.1.2")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("androidx.room:room-testing:2.2.6")
    androidTestImplementation("androidx.test:core:1.3.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.room:room-testing:2.2.6")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")
    testImplementation("org.robolectric:robolectric:4.4")
    testImplementation("org.hamcrest:hamcrest:2.2")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.3.0")
}
