# ExoPlayerApp

ExoPlayer is an application level media player for Android. It provides an alternative to Android’s MediaPlayer API for playing audio and video both locally and over the Internet. ExoPlayer supports features not currently supported by Android’s MediaPlayer API, including DASH and SmoothStreaming adaptive playbacks. Unlike the MediaPlayer API, ExoPlayer is easy to customize and extend, and can be updated through Play Store application updates.
Documentation

Using ExoPlayer

ExoPlayer modules can be obtained from JCenter. It's also possible to clone the repository and depend on the modules locally.
From JCenter
1. Add repositories

The easiest way to get started using ExoPlayer is to add it as a gradle dependency. You need to make sure you have the Google and JCenter repositories included in the build.gradle file in the root of your project:

repositories {
    google()
    jcenter()
}

2. Add ExoPlayer module dependencies

Next add a dependency in the build.gradle file of your app module. The following will add a dependency to the full library:

implementation 'com.google.android.exoplayer:exoplayer:2.X.X'

where 2.X.X is your preferred version.

