package com.example.planosycentellas;

import com.example.planosycentellas.api.Provider;
import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.model.PatreonTier;
import com.example.planosycentellas.model.PodcastInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ProviderUnitTest {

    private Provider provider;

    private String actual_ivoox_url;
    private String actual_ivoox_news_url;
    private String actual_patreon_url;


    @Before
    public void setup(){
        provider = new Provider();
        actual_ivoox_url = provider.ivoox_url;
        actual_ivoox_news_url = provider.ivoox_news_url;
        actual_patreon_url = provider.patreon_url;
    }

    @Test
    public void getPodcastInfoReturnActualData(){
        getPodcastInfo(actual_ivoox_url);
    }

    /**
     * Both possible exception, IOException | IllegalArgumentException are covered by the same catch
     * section, so we trigger the IllegalArgumentException to test it
     */
    @Test
    public void getPodcastInfoReturnEmptyObjectWrongUrl(){
        getPodcastInfo("wrong_url");
    }

    private void getPodcastInfo(String url){
        provider.ivoox_url = url;
        PodcastInfo podcastInfo = provider.getPodcastInfo();
        checkPodcastInfoNotNull(podcastInfo);
    }

    private void checkPodcastInfoNotNull(PodcastInfo podcastInfo){
        assertNotNull(podcastInfo.getDescription());
        assertNotNull(podcastInfo.getEmail());
        assertNotNull(podcastInfo.getImage());
        assertNotNull(podcastInfo.getName());
    }

    /**
     * Both possible exception, IOException | IllegalArgumentException are covered by the same catch
     * section, so we trigger the IllegalArgumentException to test it
     */
    @Test
    public void getEpisodeListReturnReturnEmptyObjectWrongUrl(){
        getEpisodeList("wrong_url");
    }

    @Test
    public void getEpisodeListReturnActualData(){
        getEpisodeList(actual_ivoox_url);
    }

    private void getEpisodeList(String url){
        provider.ivoox_url = url;

        List<Episode> episodeList = provider.getEpisodes();

        for (Episode episode: episodeList) {
            checkEpisodeInfoNotNull(episode);
        }
    }

    private void checkEpisodeInfoNotNull(Episode episode){
        assertNotNull(episode.getImage());
        assertNotNull(episode.getDescription());
        assertNotNull(episode.getTitle());
        assertNotNull(episode.getUrl());
    }

    @Test
    public void getPatreonInfoReturnActualData(){
        getPatreonInfo(actual_patreon_url);
    }

    /**
     * Both possible exception, IOException | IllegalArgumentException are covered by the same catch
     * section, so we trigger the IllegalArgumentException to test it
     */
    @Test
    public void getPatreonInfoReturnReturnEmptyObjectWrongUrl(){
        getPatreonInfo("wrong_url");
    }

    private void getPatreonInfo(String url){

        provider.patreon_url = url;

        List<PatreonTier> patreonTierList = provider.getPatreonInfo();

        for (PatreonTier patreonTier: patreonTierList) {
            checkPatreonTierNotNull(patreonTier);
        }
    }

    private void checkPatreonTierNotNull(PatreonTier patreonTier){
        assertNotNull(patreonTier.getTitle());
        assertNotNull(patreonTier.getLink());
        assertNotNull(patreonTier.getImage());
        assertNotNull(patreonTier.getPrice());
        assertNotNull(patreonTier.getAwards());
    }

    @Test
    public void getUpcomingReturnActualData(){
        getUpconming(actual_ivoox_news_url);
    }

    @Test
    public void getUpcomingEmptyObjectWrongUrl(){
        getUpconming("wrong_url");
    }

    private void getUpconming(String url){
        provider.ivoox_news_url = url;
        List<String> uppcoming = provider.getUpcoming();
        assertFalse(uppcoming.contains(Collections.EMPTY_LIST));
    }



}
