package com.example.planosycentellas.api;

import android.util.Log;

import androidx.annotation.VisibleForTesting;

import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.model.PatreonTier;
import com.example.planosycentellas.model.PodcastInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Provider {

    @VisibleForTesting()
    public String ivoox_url =
            "https://www.ivoox.com/podcast-planos-centellas_fg_f1609149_filtro_1.xml";

    @VisibleForTesting()
    public String ivoox_news_url = "https://www.ivoox.com/planos-centellas_pr_posts_609149_1.html";

    @VisibleForTesting()
    public String patreon_url = "https://www.patreon.com/planosycentellas";

    public PodcastInfo getPodcastInfo() {

        PodcastInfo podcastInfo = new PodcastInfo();

        try {

            Document doc = Jsoup.connect(ivoox_url).get().parser(Parser.xmlParser());

            podcastInfo.setDescription(doc.select("description").get(0).text());
            podcastInfo.setName(doc.select("title").get(0).text());
            podcastInfo.setImage(doc.select("itunes|image").attr("href"));
            podcastInfo.setEmail(doc.select("itunes|email").text());

        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return podcastInfo;
    }

    public List<Episode> getEpisodes(){

        List<Episode> episodeList = new ArrayList<>();

        try{

            Document doc = Jsoup.connect(ivoox_url).get().parser(Parser.xmlParser());

            Elements elements = doc.select("item");

            for(int i = 0; i < 10; i++){
                Episode episode = new Episode();

                Element e = elements.get(i);
                episode.setTitle(e.select("title").text());
                episode.setDescription(e.select("description").text());
                episode.setUrl(e.select("enclosure").attr("url"));
                episode.setImage(e.select("itunes|image").attr("href"));
                episodeList.add(episode);

            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        return episodeList;
    }

    public List<String> getUpcoming(){

        List<String> newsList = new ArrayList<>();
        try{

            Document doc = Jsoup.connect(
                    ivoox_news_url).get();

            Elements images = doc.select("div.container.container-xl");

            for(int i = 0; i < images.size(); i++){
                newsList.add(images.get(i).select("div.m-bottom-10").select("a").attr("href"));
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        return newsList;
    }

    public List<PatreonTier> getPatreonInfo(){
        List<PatreonTier> patreonTierList = new ArrayList<>();
        try{

            Document doc = Jsoup.connect(patreon_url).get();

            Elements elements = doc.select("div.sc-fzoLsD.cCFuMf");

            for(int i = 0; i < elements.size(); i++){

                PatreonTier patreonTier = new PatreonTier();

                patreonTier.setTitle(elements.get(i).select("div.sc-AxjAm.kGRoiw").text());

                patreonTier.setPrice(elements.get(i).select(
                        "div.sc-AxjAm.bdDRMi").text() + " "
                        + elements.get(i).select("div.sc-AxjAm.ufKCT").text() + " "
                        + elements.get(i).select("div.sc-AxjAm.hpINne").text());

                patreonTier.setImage(elements.get(i).select(
                        "div.sc-fzoLsD.cBkBik").select("img").attr("src"));

                patreonTier.setLink("https://www.patreon.com" + elements.get(i).select(
                        "a.sc-fzoiQi.hrhoNA.ibazdf-0.kYJzfB").attr("href"));

                patreonTier.setAwards(elements.get(i).select("div.sc-1rlfkev-0.yMRiI").text());

                patreonTierList.add(patreonTier);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return patreonTierList;
    }

    public List<Episode> searchEpisodes(String s){
        return getEpisodes();
    }

}
