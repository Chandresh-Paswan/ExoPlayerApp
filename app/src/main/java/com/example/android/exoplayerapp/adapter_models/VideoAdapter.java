package com.example.android.exoplayerapp.adapter_models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.android.exoplayerapp.R;
import com.example.android.exoplayerapp.VideoDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private List<VideoModel> item;
    Context context ;
    private MediaController mediacontroller;
    private Uri uri;

    public VideoAdapter(Context context, List<VideoModel> item ) {
        this.item = item;
        this.context = context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView desc, title,id;
        public ImageView thumb;
        public TextView url;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.id);
            desc = (TextView) view.findViewById(R.id.description);
            title = (TextView) view.findViewById(R.id.title);
            url = (TextView) view.findViewById(R.id.url);
            thumb = (ImageView) view.findViewById(R.id.thumb);
        }
    }


    @NonNull
    @Override
    public VideoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoAdapter.MyViewHolder holder, final int position) {

        holder.id.setText(item.get(position).getId());
        holder.desc.setText(item.get(position).getDescription());
        holder.title.setText(item.get(position).getTitle());
        holder.url.setText(item.get(position).getUrl());

        String url_image =(item.get(position).getThumb());
        Picasso.get()
                .load(url_image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.thumb);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "#" + position + " - " + item.get(position).getUrl() + " (Long click)", Toast.LENGTH_SHORT).show();
                System.out.println("o"+item.get(position).getUrl());
                Intent intent = new Intent(view.getContext(), VideoDetails.class);
                intent.putExtra("position",position);
                intent.putExtra("url",item.get(position).getUrl());
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}
