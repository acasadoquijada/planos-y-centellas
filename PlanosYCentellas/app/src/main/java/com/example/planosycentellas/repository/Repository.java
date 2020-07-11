package com.example.planosycentellas.repository;


import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.planosycentellas.api.Provider;
import com.example.planosycentellas.model.Episode;

import java.util.List;

import javax.inject.Inject;

public class Repository {

    private Provider provider;
    private MutableLiveData<List<Episode>> episodeList;
    private MutableLiveData<List<String>> news;

    public Repository(){
        provider = new Provider();
        episodeList = new MutableLiveData<>();
        news = new MutableLiveData<>();
    }

    public MutableLiveData<List<Episode>> getEpisodes(){

        if(episodeList.getValue() == null){
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


    class FetchData extends AsyncTask<Void, Void, List<Episode>> {
        @Override
        protected List<Episode> doInBackground(Void... voids) {
            return provider.getData();
        }

        @Override
        protected void onPostExecute(List<Episode> episodes) {
            super.onPostExecute(episodes);
            episodeList.setValue(episodes);
        }
    }
    class FetchNews extends AsyncTask<Void, Void, List<String>> {
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
}


