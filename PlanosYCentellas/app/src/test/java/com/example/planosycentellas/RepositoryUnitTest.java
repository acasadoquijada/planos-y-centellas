package com.example.planosycentellas;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.platform.app.InstrumentationRegistry;

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
import java.util.Objects;
import java.util.concurrent.Executor;

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

        repository.getEpisodeList().setValue(episodes);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(repository.getEpisodeList()),episodes);
    }

    @Test
    public void newsIsNotifiedCorrectly() throws InterruptedException {

        String newsList = "new ArrayList<>()";

        repository.getNews().setValue(newsList);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(repository.getNews()), newsList);

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

    @Test
    public void searchEpisode(){

        repository.getEpisodeList().setValue(createEpisodes());

        String query = "John Wick";

        repository.searchEpisodes(query);

        assertEquals(2, Objects.requireNonNull(repository.getSearchedEpisodes().getValue()).size());

    }

    private List<Episode> createEpisodes(){
        List<Episode> episodeList = new ArrayList<>();

        Episode e1 = new Episode(); e1.setTitle("John Wick 1"); episodeList.add(e1);
        Episode e2 = new Episode(); e2.setTitle("John Wick 3"); episodeList.add(e2);
        Episode e3 = new Episode(); e3.setTitle("Venom"); episodeList.add(e3);
        Episode e4 = new Episode(); e4.setTitle("Cementerio de animales"); episodeList.add(e4);

        return episodeList;
    }

    @Test
    public void getEpisodeReturnActualData(){

        repository.getEpisodeList().setValue(createEpisodes());

        Episode episode = repository.getEpisode(0);

        assertEquals(episode.getTitle(),"John Wick 1");

    }

    @Test
    public void getEpisodeReturnEmptyObject(){

        repository.getEpisodeList().setValue(createEpisodes());

        Episode episode = repository.getEpisode(10);

        assertEquals(episode.getTitle(),"");
    }

}
