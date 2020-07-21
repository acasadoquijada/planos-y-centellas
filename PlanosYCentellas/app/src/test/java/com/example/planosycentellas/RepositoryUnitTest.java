package com.example.planosycentellas;

import android.os.AsyncTask;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.model.PatreonTier;
import com.example.planosycentellas.model.PodcastInfo;
import com.example.planosycentellas.repository.Repository;
import com.example.planosycentellas.util.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class RepositoryUnitTest {

    private Repository repository;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setup(){
        repository = new Repository();
    }

    @Test
    public void episodeListIsNotifiedCorrectly() throws InterruptedException {

        List<Episode> episodes = new ArrayList<>();

        repository.getEpisodes().setValue(episodes);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(repository.getEpisodes()),episodes);
    }

    @Test
    public void newsIsNotifiedCorrectly() throws InterruptedException {

        List<String> news = new ArrayList<>();

        repository.getNews().setValue(news);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(repository.getNews()),news);
    }


    @Test
    public void patreonTierListIsNotifiedCorrectly() throws InterruptedException {

        List<PatreonTier> patreonTierList = new ArrayList<>();

        repository.getPatreonTierList().setValue(patreonTierList);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(repository.getPatreonTierList()),patreonTierList);
    }

    @Test
    public void podcastInfoIsNotifiedCorrectly() throws InterruptedException {

        PodcastInfo podcastInfo = new PodcastInfo();

        repository.getPodcastInfo().setValue(podcastInfo);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(repository.getPodcastInfo()),podcastInfo);
    }

    @Test
    public void searchEpisodeListIsNotifiedCorrectly() throws InterruptedException {

        List<Episode> episodes = new ArrayList<>();

        repository.getSearchedEpisodes().setValue(episodes);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(repository.getSearchedEpisodes()),episodes);
    }

}
