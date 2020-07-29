package com.example.planosycentellas.repository;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.planosycentellas.api.Provider;
import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.model.PatreonTier;
import com.example.planosycentellas.model.PodcastInfo;

import java.lang.ref.WeakReference;
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

    public MutableLiveData<List<Episode>> getEpisodeList(){

        if (episodeList.getValue() == null) {
            new FetchData(this).execute();
        }
        return episodeList;
    }

    public MutableLiveData<List<String>> getNews(){
        if(news.getValue() == null){
            new FetchNews(this).execute();
        }
        return news;
    }

    public MutableLiveData<List<PatreonTier>> getPatreonTierList(){
        if(patreonTierList.getValue() == null){
            new FetchPatreonTierInfo(this).execute();
        }
        return patreonTierList;
    }

    public MutableLiveData<PodcastInfo> getPodcastInfo(){
        if(podcastInfo.getValue() == null){
            new FetchPodcastInfo(this).execute();
        }
        return podcastInfo;
    }

    public void searchEpisodes(String query){

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

    public Episode getEpisode(int index){
        if(Objects.requireNonNull(episodeList.getValue()).size() > index){
            return episodeList.getValue().get(index);
        }
        return new Episode();
    }

    private static class FetchData extends AsyncTask<Void, Void, List<Episode>> {

        private WeakReference<Repository> repositoryReference;

        public FetchData(Repository repository){
            repositoryReference = new WeakReference<>(repository);
        }

        @Override
        protected List<Episode> doInBackground(Void... voids) {
            return repositoryReference.get().provider.getEpisodes();
        }

        @Override
        protected void onPostExecute(List<Episode> episodes) {
            super.onPostExecute(episodes);
            repositoryReference.get().episodeList.setValue(episodes);
        }
    }

    private static class FetchNews extends AsyncTask<Void, Void, List<String>> {

        private WeakReference<Repository> repositoryReference;

        public FetchNews(Repository repository){
            repositoryReference = new WeakReference<>(repository);
        }

        @Override
        protected List<String> doInBackground(Void... voids) {
            return repositoryReference.get().provider.getUpcoming();
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            repositoryReference.get().news.setValue(strings);
        }
    }

    private static class FetchPatreonTierInfo extends AsyncTask<Void, Void, List<PatreonTier>> {

        private WeakReference<Repository> repositoryReference;

        public FetchPatreonTierInfo(Repository repository){
            repositoryReference = new WeakReference<>(repository);
        }

        @Override
        protected List<PatreonTier> doInBackground(Void... voids) {
            return repositoryReference.get().provider.getPatreonInfo();
        }

        @Override
        protected void onPostExecute(List<PatreonTier> patreonList) {
            super.onPostExecute(patreonList);
            repositoryReference.get().patreonTierList.setValue(patreonList);
        }
    }

    public static class FetchPodcastInfo extends AsyncTask<Void, Void, PodcastInfo>{

        private WeakReference<Repository> repositoryReference;

        public FetchPodcastInfo(Repository repository){
            repositoryReference = new WeakReference<>(repository);
        }
        @Override
        protected PodcastInfo doInBackground(Void... voids) {
            return repositoryReference.get().provider.getPodcastInfo();
        }

        @Override
        protected void onPostExecute(PodcastInfo podcast) {
            super.onPostExecute(podcast);
            repositoryReference.get().podcastInfo.setValue(podcast);
        }
    }
}


