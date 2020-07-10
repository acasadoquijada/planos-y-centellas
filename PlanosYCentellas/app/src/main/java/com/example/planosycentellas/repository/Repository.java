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

    public Repository(){
        provider = new Provider();
        episodeList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Episode>> getEpisodes(){

        if(episodeList.getValue() == null){
            new FetchData().execute();
        }

        return episodeList;
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
    }}
