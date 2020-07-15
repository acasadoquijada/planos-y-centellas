package com.example.planosycentellas.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.model.PatreonTier;
import com.example.planosycentellas.model.PodcastInfo;
import com.example.planosycentellas.repository.Repository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<List<Episode>> episodeList;
    private MutableLiveData<List<String>> newsList;
    private MutableLiveData<List<PatreonTier>> patreonTierList;
    private MutableLiveData<PodcastInfo> podcastInfo;
    private MutableLiveData<String> searchQuery;
    private MutableLiveData<List<Episode>> searchedEpisodes;


    public HomeViewModel(){
        repository = new Repository();
        searchQuery = new MutableLiveData<>();
        searchedEpisodes = new MutableLiveData<>();
    }

    public MutableLiveData<List<Episode>> getEpisodeList(){
        if(episodeList == null){
            getEpisodeListRepository();
        }
        return episodeList;
    }

    private void getEpisodeListRepository(){
        episodeList = repository.getEpisodes();
    }

    public MutableLiveData<List<String>> getNewsList(){
        if(newsList == null){
            getNewsListRepository();
        }
        return newsList;
    }

    private void getNewsListRepository(){
        newsList = repository.getNews();
    }

    public MutableLiveData<List<PatreonTier>> getPatreonTierList(){
        if(patreonTierList == null){
            getPatreonTierListRepository();
        }
        return patreonTierList;
    }

    private void getPatreonTierListRepository(){
        patreonTierList = repository.getPatreonTierList();
    }

    public MutableLiveData<PodcastInfo> getPodcastInfo() {
        if(podcastInfo == null){
            getPodcastInfoRepository();
        }
        return podcastInfo;
    }

    private void getPodcastInfoRepository(){
        podcastInfo = repository.getPodcastInfo();
    }

    public MutableLiveData<String> getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery.setValue(searchQuery);
    }

    public MutableLiveData<List<Episode>> getSearchedEpisodes(){

        searchedEpisodes = repository.getSearchedEpisodes();

        return searchedEpisodes;

    }

    public void searchEpisodes(){
        repository.searchEpisodes(searchQuery.getValue());
    }
}
