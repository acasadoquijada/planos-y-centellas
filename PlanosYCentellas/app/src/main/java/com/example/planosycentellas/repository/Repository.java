package com.example.planosycentellas.repository;


import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;

import com.example.planosycentellas.api.Provider;
import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.model.PatreonTier;
import com.example.planosycentellas.model.PodcastInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Repository {

    private Provider provider;
    private MutableLiveData<List<Episode>> episodeList;
    private MutableLiveData<List<String>> news;
    private MutableLiveData<List<PatreonTier>> patreonTierList;
    private MutableLiveData<PodcastInfo> podcastInfo;
    private MutableLiveData<List<Episode>> searchedEpisodes;

    public Repository(){
        provider = new Provider();
        episodeList = new MutableLiveData<>();
        news = new MutableLiveData<>();
        patreonTierList = new MutableLiveData<>();
        podcastInfo = new MutableLiveData<>();
        searchedEpisodes = new MutableLiveData<>();
    }

    public MutableLiveData<List<Episode>> getEpisodes(){

        if (episodeList.getValue() == null) {
            new FetchData().execute();
        }
        return episodeList;
    }

    public MutableLiveData<List<String>> getNews(){
        if(news.getValue() == null){
            new FetchNews().execute();
        }
        return news;
    }

    public MutableLiveData<List<PatreonTier>> getPatreonTierList(){
        if(patreonTierList.getValue() == null){
            new FetchPatreonTierInfo().execute();
        }
        return patreonTierList;
    }

    public MutableLiveData<PodcastInfo> getPodcastInfo(){
        if(podcastInfo.getValue() == null){
            new FetchPodcastInfo().execute();
        }
        return podcastInfo;
    }

    public void searchEpisodes(String query){

        if(searchedEpisodes.getValue() != null){
            searchedEpisodes.getValue().clear();
        }

        List<Episode> searchEpisodesList = new ArrayList<>();

        for(Episode episode : Objects.requireNonNull(episodeList.getValue())){
            if(episode.getTitle().toLowerCase().contains(query.toLowerCase())){
                searchEpisodesList.add(episode);
            }
        }

        searchedEpisodes.setValue(searchEpisodesList);
    }

    public MutableLiveData<List<Episode>> getSearchedEpisodes(){
        return searchedEpisodes;
    }

    class FetchData extends AsyncTask<Void, Void, List<Episode>> {
        @Override
        protected List<Episode> doInBackground(Void... voids) {
            return provider.getEpisodes();
        }

        @Override
        protected void onPostExecute(List<Episode> episodes) {
            super.onPostExecute(episodes);
            episodeList.setValue(episodes);
        }
    }

    @VisibleForTesting()
    public class FetchNews extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            return provider.getUpcoming();
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            news.setValue(strings);
        }
    }
    class FetchPatreonTierInfo extends AsyncTask<Void, Void, List<PatreonTier>> {
        @Override
        protected List<PatreonTier> doInBackground(Void... voids) {
            return provider.getPatreonInfo();
        }

        @Override
        protected void onPostExecute(List<PatreonTier> patreonList) {
            super.onPostExecute(patreonList);
            patreonTierList.setValue(patreonList);
        }
    }
    class FetchPodcastInfo extends AsyncTask<Void, Void, PodcastInfo>{
        @Override
        protected PodcastInfo doInBackground(Void... voids) {
            return provider.getPodcastInfo();
        }

        @Override
        protected void onPostExecute(PodcastInfo podcast) {
            super.onPostExecute(podcast);
            podcastInfo.setValue(podcast);
        }
    }
}


