package com.example.planosycentellas.api;

import androidx.annotation.VisibleForTesting;

import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.model.PatreonAwards;
import com.example.planosycentellas.model.PatreonTier;
import com.example.planosycentellas.model.PodcastInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
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

            for(int i = 0; i < elements.size(); i++){

                Episode episode = new Episode();

                Element e = elements.get(i);

                episode.setTitle(getEpisodeTitle(e));

                episode.setDescription(getEpisodeDescription(e));

                episode.setUrl(getEpisodeUrl(e));

                episode.setImage(getEpisodeImage(e));

                episodeList.add(episode);
            }

        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        return episodeList;
    }

    private String getEpisodeTitle(Element element){
        return element.select("title").text();
    }

    private String getEpisodeDescription(Element element){
        return element.select("description").text();
    }

    private String getEpisodeUrl(Element element){
        return element.select("enclosure").attr("url");
    }

    private String getEpisodeImage(Element element){
        return element.select("itunes|image").attr("href");
    }

    public List<String> getUpcoming(){

        List<String> newsList = new ArrayList<>();
        try{

            Document doc = Jsoup.connect(
                    ivoox_news_url).get();

            Elements images = doc.select("div.container.container-xl");

            for(int i = 0; i < images.size(); i++){
                newsList.add(getUpcomingImage(images.get(i)));
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        return newsList;
    }

    private String getUpcomingImage(Element element){
        return element.select("div.m-bottom-10").select("a").attr("href");
    }

    public List<PatreonTier> getPatreonInfo(){

        List<PatreonTier> patreonTierList = new ArrayList<>();

        try{

            Document doc = Jsoup.connect(patreon_url).get();

            Elements elements = doc.select("div.sc-fzoLsD.cCFuMf");

            for(int i = 0; i < elements.size(); i++){

                PatreonTier patreonTier = new PatreonTier();

                patreonTier.setTitle(getPatreonTitle(elements.get(i)));

                patreonTier.setPrice(getPatreonPrice(elements.get(i)));

                patreonTier.setImage(getPatreonImage(elements.get(i)));

                patreonTier.setLink(getPatreonUrl(elements.get(i)));

                patreonTier.setAwards(getPatreonAwards(elements.get(i)));

                patreonTierList.add(patreonTier);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return patreonTierList;
    }

    private PatreonAwards getPatreonAwards(Element element){

        PatreonAwards patreonAwards = new PatreonAwards();

        patreonAwards.setInitialMessage(getAwardInitialMessage(element));

        patreonAwards.setAwardsDetails(getPatreonAwardsDetails(element));

        return patreonAwards;
    }

    private String getAwardInitialMessage(Element element){

        // Initial message
        String initialMessage = element.select("div.sc-1rlfkev-0.yMRiI").select("div").text();

        // Get only the initial message
        initialMessage = (initialMessage.substring(0,initialMessage.indexOf("Recompensas")));

        return initialMessage;
    }

    private List<String> getPatreonAwardsDetails(Element element){

        List<String> awards = new ArrayList<>();
        int size =  element.select("div.sc-1rlfkev-0.yMRiI").select("div").select("li").size();

        for(int j = 0; j < size; j++) {
            awards.add(element.select("div.sc-1rlfkev-0.yMRiI").select("div").select("li").get(j).text());
        }

        return awards;
    }

    private String getPatreonTitle(Element element){
        return element.select("div.sc-AxjAm.kGRoiw").text();
    }

    private String getPatreonPrice(Element element){
        return element.select(
                "div.sc-AxjAm.bdDRMi").text() + " "
                +element.select("div.sc-AxjAm.ufKCT").text() + " "
                + element.select("div.sc-AxjAm.hpINne").text();
    }

    private String getPatreonImage(Element element){
        return element.select(
                "div.sc-fzoLsD.cBkBik").select("img").attr("src");
    }

    private String getPatreonUrl(Element element){
        return "https://www.patreon.com" + element.select(
                "a.sc-fzoiQi.hrhoNA.ibazdf-0.kYJzfB").attr("href");
    }
}
