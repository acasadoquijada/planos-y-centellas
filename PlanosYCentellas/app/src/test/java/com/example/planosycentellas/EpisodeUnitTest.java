package com.example.planosycentellas;

import com.example.planosycentellas.model.Episode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class EpisodeUnitTest {

    @Test
    public void isEmptyWithEmptyData(){
        Episode episode = new Episode();
        assertTrue(episode.isEmpty());
    }

    @Test
    public void isEmptyWithActualData(){
        Episode episode = new Episode();

        episode.setUrl("gsdegvsdegesg");
        episode.setTitle("dgsgsgdsd");
        episode.setDescription("dsfadafda");
        episode.setImage("sfssfdfs");

        assertFalse(episode.isEmpty());
    }


}
