package com.example.planosycentellas;


import android.view.View;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.model.PatreonTier;
import com.example.planosycentellas.model.PodcastInfo;
import com.example.planosycentellas.repository.Repository;
import com.example.planosycentellas.util.LiveDataTestUtil;
import com.example.planosycentellas.viewmodel.HomeViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class ViewModelUnitTest {

    private HomeViewModel viewModel;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setup(){
        viewModel = new HomeViewModel();
    }

    @Test
    public void episodeListIsNotifiedCorrectly() throws InterruptedException {

        List<Episode> episodes = new ArrayList<>();

        viewModel.getEpisodeList().setValue(episodes);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(viewModel.getEpisodeList()),episodes);
    }


    @Test
    public void newsListIsNotifiedCorrectly() throws InterruptedException {

        List<String> newsList = new ArrayList<>();

        viewModel.getNewsList().setValue(newsList);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(viewModel.getNewsList()),newsList);
    }

    @Test
    public void patreonTierListIsNotifiedCorrectly() throws InterruptedException {

        List<PatreonTier> patreonTierList = new ArrayList<>();

        viewModel.getPatreonTierList().setValue(patreonTierList);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(viewModel.getPatreonTierList()),patreonTierList);
    }

    @Test
    public void podcastInfoIsNotifiedCorrectly() throws InterruptedException {

        PodcastInfo podcastInfo = new PodcastInfo();

        viewModel.getPodcastInfo().setValue(podcastInfo);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(viewModel.getPodcastInfo()), podcastInfo);
    }


    @Test
    public void searchedEpisodeListIsNotifiedCorrectly() throws InterruptedException {

        List<Episode> episodes = new ArrayList<>();

        viewModel.getSearchedEpisodes().setValue(episodes);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(viewModel.getSearchedEpisodes()), episodes);
    }

}
