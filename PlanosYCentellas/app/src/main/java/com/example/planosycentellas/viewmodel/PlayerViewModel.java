package com.example.planosycentellas.viewmodel;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.planosycentellas.model.Episode;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.Objects;

public class PlayerViewModel extends AndroidViewModel {

    private MutableLiveData<Episode> episode;

    private long currentPosition;
    private ExoPlayer player;
    private MediaSource mediaSource;
    private boolean playWhenReady;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        player = new SimpleExoPlayer.Builder(getApplication()).build();
        episode = new MutableLiveData<>();
        currentPosition = 0;
        playWhenReady = true;
    }

    public void setupAudio() {
        if(episode.getValue() != null && !Objects.requireNonNull(episode.getValue()).getUrl().equals("")){
            if (currentPosition != 0) {
                player.prepare(mediaSource);
                player.seekTo(currentPosition);
            } else {
                mediaSource = buildMediaSource(episode.getValue().getUrl());
                player.prepare(mediaSource);
                player.seekTo(currentPosition);
            }
            player.setPlayWhenReady(playWhenReady);
        }
    }

    public LiveData<Episode> getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {

        this.episode.setValue(new Episode(episode));
    }

    private MediaSource buildMediaSource(String url) {
        Uri uri = Uri.parse(url);
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getApplication().getApplicationContext(), "exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

    public void clean(){
        stopPlayer();
        currentPosition = 0;
        player.seekTo(currentPosition);
        playWhenReady = true;
        episode.getValue().clean();
    }

    public ExoPlayer getPlayer(){
        return player;
    }

    public void releasePlayer(){
        currentPosition = player.getCurrentPosition();
        playWhenReady = player.getPlayWhenReady();
        player.stop();
    }

    public void stopPlayer(){
        if(player != null){
            player.stop();
        }
    }

    public void onPause(){
        releasePlayer();
    }

    public void onResume(){
        setupAudio();
    }
}
