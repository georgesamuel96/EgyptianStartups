package com.example.android.egyptionstartups;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private ArrayList<Video> videos;
    Context mContext;

    public VideoAdapter(Context context, ArrayList<Video> videos)
    {
        this.mContext = context;
        this.videos = videos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        VideoAdapter.MyViewHolder phv = new VideoAdapter.MyViewHolder(v);
        return phv;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.contentText.setText(videos.get(position).getContent());
        final String idVideo = videos.get(position).getId();


        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.youTubePlayerView.initialize(PlayerConfig.API_KEY, holder.onInitializedListener);
            }
        });

        holder.onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(idVideo);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView contentText;
        private Button play;
        YouTubePlayerView youTubePlayerView;
        YouTubePlayer.OnInitializedListener onInitializedListener;

        public MyViewHolder(View view)
        {
            super(view);

            contentText = (TextView)view.findViewById(R.id.content);
            play = (Button) view.findViewById(R.id.play);
            youTubePlayerView = (YouTubePlayerView) view.findViewById(R.id.youtube_player_view);


        }
    }
}
