package com.example.android.exoplayerapp;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.android.exoplayerapp.adapter_models.VideoAdapter;
import com.example.android.exoplayerapp.adapter_models.VideoDetailsAdapter;
import com.example.android.exoplayerapp.adapter_models.VideoDetailsModel;
import com.example.android.exoplayerapp.adapter_models.VideoModel;
import com.example.android.exoplayerapp.rest.ApiClient;
import com.example.android.exoplayerapp.rest.ApiInterface;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoDetails extends AppCompatActivity {

    private static final String TAG = VideoDetails.class.getSimpleName();

    PlayerView playerView;
    private SimpleExoPlayer player;
    long playbackPosition;
    int currentWindow;
    boolean playWhenReady;
    String playvideo_link="";

    private List<VideoDetailsModel> videoDetailList = new ArrayList<>();
    private RecyclerView recyclerView;
    private VideoDetailsAdapter mAdapter;
    static Integer[] drawableArray = {R.drawable.ic_launcher_background};

    List<VideoDetailsModel> userLists =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_details);

        Bundle bundle = getIntent().getExtras();
        playvideo_link = bundle.getString("url");

         ArrayList video=new ArrayList();

        playerView = findViewById(R.id.video_view);

        loadJSON();
    }

    private void initializePlayer() {

        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

        Uri uri = Uri.parse(playvideo_link);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {

        // these are reused for both media sources we create below
        DefaultExtractorsFactory extractorsFactory =
                new DefaultExtractorsFactory();
        DefaultHttpDataSourceFactory dataSourceFactory =
                new DefaultHttpDataSourceFactory( "user-agent");

        /*Uri videouri = Uri.parse(playvideo_link);*/
        ExtractorMediaSource videoSource =
                new ExtractorMediaSource.Factory(
                        new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                        createMediaSource(uri);

        /*Uri audioUri = Uri.parse(playvideo_link);*/
        ExtractorMediaSource audioSource =
                new ExtractorMediaSource.Factory(
                        new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                        createMediaSource(uri);

        return new ConcatenatingMediaSource(audioSource, videoSource);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    private void loadJSON(){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<VideoDetailsModel>> call = apiService.getFullVideo();
        call.enqueue(new Callback<List<VideoDetailsModel>>() {
            @Override
            public void onResponse(Call<List<VideoDetailsModel>> call, Response<List<VideoDetailsModel>> response) {
                int statusCode = response.code();
                userLists = response.body();
                recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                VideoDetailsAdapter recyclerViewVideoDetailAdapter =new VideoDetailsAdapter(getApplicationContext(), userLists,playvideo_link);
                recyclerView.setAdapter(recyclerViewVideoDetailAdapter);


            }

            @Override
            public void onFailure(Call<List<VideoDetailsModel>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
