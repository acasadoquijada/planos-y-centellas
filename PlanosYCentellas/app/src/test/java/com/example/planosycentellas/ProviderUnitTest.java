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

    @Before
    public void setup(){
        provider = new Provider();
    }

    @Test
    public void getPodcastInfoReturnActualData(){
        PodcastInfo podcastInfo = provider.getPodcastInfo();
        checkPodcastInfoNotNull(podcastInfo);
    }

    private void checkPodcastInfoNotNull(PodcastInfo podcastInfo){
        assertNotNull(podcastInfo.getDescription());
        assertNotNull(podcastInfo.getEmail());
        assertNotNull(podcastInfo.getImage());
        assertNotNull(podcastInfo.getName());
    }

    @Test
    public void getEpisodeListReturnActualData(){
        List<Episode> episodeList = provider.getData();

        // This could be improved
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
        List<String> uppcoming = provider.getUpcoming();
        assertFalse(uppcoming.contains(Collections.EMPTY_LIST));
    }

}
