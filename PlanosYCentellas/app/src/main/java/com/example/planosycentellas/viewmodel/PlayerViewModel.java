package com.example.planosycentellas.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.example.planosycentellas.model.Episode;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.Objects;

public class PlayerViewModel extends AndroidViewModel implements LifecycleObserver {


    private MutableLiveData<Episode> episode;

    public PlayerViewModel(@NonNull Application application) {
        super(application);

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

        episode = new MutableLiveData<>();

    }


    public void setupAudio() {
    }

    public LiveData<Episode> getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode.setValue(episode);
    }


    private MediaSource buildMediaSource(String url) {
        Uri uri = Uri.parse(url);
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getApplication().getApplicationContext(), "exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onAppForegrounded(){
        setupPlayer();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onAppBackgrounded(){
        releasePlayer();
    }

    private void setupPlayer(){
        setupAudio();
    }

    private void releasePlayer(){
/*        currentPosition = player.getValue().getCurrentPosition();
        playWhenReady = player.getValue().getPlayWhenReady();
        player.getValue().release();*/
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        releasePlayer();
        ProcessLifecycleOwner.get().getLifecycle().removeObserver(this);
    }
}
