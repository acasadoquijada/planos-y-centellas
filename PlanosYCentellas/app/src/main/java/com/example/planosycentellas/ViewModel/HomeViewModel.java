package com.example.planosycentellas.ViewModel;

import android.net.DnsResolver;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.repository.Repository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<List<Episode>> episodeList;
    private MutableLiveData<List<String>> news;


    public HomeViewModel(){
        repository = new Repository();
    }

    public MutableLiveData<List<Episode>> getEpisodes(){
        if(episodeList == null){
            episodeList = repository.getEpisodes();
        }
        return episodeList;
    }

    public MutableLiveData<List<String>> getNews(){

        if(news == null){
            news = repository.getNews();
        }
        return news;
    }
}
