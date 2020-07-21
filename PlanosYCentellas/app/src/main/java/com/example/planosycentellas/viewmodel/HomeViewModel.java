package com.example.planosycentellas.viewmodel;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.model.PatreonTier;
import com.example.planosycentellas.model.PodcastInfo;
import com.example.planosycentellas.repository.Repository;

import java.util.List;


public class HomeViewModel extends ViewModel {

    @VisibleForTesting()
    public Repository repository;

    private MutableLiveData<String> searchQuery;
    public HomeViewModel(){
        repository = new Repository();
        searchQuery = new MutableLiveData<>();
    }

    public MutableLiveData<List<Episode>> getEpisodeList(){
        return repository.getEpisodes();
    }

    public MutableLiveData<List<String>> getNewsList(){
        return repository.getNews();
    }

    public MutableLiveData<List<PatreonTier>> getPatreonTierList(){
        return repository.getPatreonTierList();
    }

    public MutableLiveData<PodcastInfo> getPodcastInfo() {
        return repository.getPodcastInfo();
    }

    public MutableLiveData<String> getSearchQuery() {
        return searchQuery;
    }

    public MutableLiveData<List<Episode>> getSearchedEpisodes(){
        return repository.getSearchedEpisodes();
    }

    public void searchEpisodes(){
        repository.searchEpisodes(searchQuery.getValue());
    }
}
