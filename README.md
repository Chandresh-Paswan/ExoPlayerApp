# ExoPlayerApp

Playing videos and music is a popular activity on Android devices. The Android framework provides MediaPlayer as a quick solution for playing media with minimal code. It also provides low level media APIs such as MediaCodec, AudioTrack and MediaDrm, which can be used to build custom media player solutions.

ExoPlayer is an open source, application level media player built on top of Android’s low level media APIs. This guide describes the ExoPlayer library and its use. It refers to code in ExoPlayer’s main demo app in order to provide concrete examples. The guide touches on the pros and cons of using ExoPlayer. It shows how to use ExoPlayer to play DASH, SmoothStreaming and HLS adaptive streams, as well as formats such as MP4, M4A, FMP4, WebM, MKV, MP3, Ogg, WAV, MPEG-TS, MPEG-PS, FLV and ADTS (AAC). It also discusses ExoPlayer events, messages, customization and DRM support.

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

