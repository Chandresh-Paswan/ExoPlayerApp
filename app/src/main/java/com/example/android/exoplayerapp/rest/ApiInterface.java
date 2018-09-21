package com.example.android.exoplayerapp.rest;

import com.example.android.exoplayerapp.adapter_models.VideoDetailsModel;
import com.example.android.exoplayerapp.adapter_models.VideoModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by chandresh on 24/11/17.
 */

public interface ApiInterface {
    @GET("media.json")
    Call<List<VideoModel>> getAllVideos();

    @GET("media.json")
    Call<List<VideoDetailsModel>> getFullVideo();

}
