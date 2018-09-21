package com.example.android.exoplayerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.exoplayerapp.adapter_models.VideoAdapter;
import com.example.android.exoplayerapp.adapter_models.VideoModel;
import com.example.android.exoplayerapp.rest.ApiClient;
import com.example.android.exoplayerapp.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoList extends AppCompatActivity {

    private static final String TAG = VideoList.class.getSimpleName();

    private List<VideoModel> videoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private VideoAdapter mAdapter;
    static Integer[] drawableArray = {R.drawable.ic_launcher_background};

    List<VideoModel> userList =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);



        loadJSON();
    }

    private void loadJSON(){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<VideoModel>> call = apiService.getAllVideos();
        call.enqueue(new Callback<List<VideoModel>>() {
            @Override
            public void onResponse(Call<List<VideoModel>> call, Response<List<VideoModel>> response) {
                int statusCode = response.code();
                userList = response.body();
                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                VideoAdapter recyclerViewAdapter =new VideoAdapter(getApplicationContext(), userList);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<VideoModel>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

}
