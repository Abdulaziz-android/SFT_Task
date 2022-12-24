class Dependencies {

    object Dagger{
        private const val version = "2.44.2"
        const val dagger = "com.google.dagger:dagger:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val kapt = "com.google.dagger:dagger-compiler:$version"
    }

    object Navigation{
        private const val nav_version = "2.5.3"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
        const val ui = "androidx.navigation:navigation-ui-ktx:2.5.3"
    }

    object Room {
        private const val version = "2.4.3"
        const val room = "androidx.room:room-ktx:$version"
        const val kapt = "androidx.room:room-compiler:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Other {
        const val logging_interceptor ="com.squareup.okhttp3:logging-interceptor:4.9.3"
    }

}