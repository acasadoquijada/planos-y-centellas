package com.example.planosycentellas.api;

import android.util.Log;

import com.example.planosycentellas.model.Episode;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.internal.LockFreeLinkedListHead;

public class Provider {

    private final String url =
            "https://www.ivoox.com/podcast-planos-centellas_fg_f1609149_filtro_1.xml";


    public List<Episode> getData(){


        try{

            Document doc = Jsoup.connect(url).get().parser(Parser.xmlParser());
            List<Episode> episodeList = new ArrayList<>();

            Elements elements = doc.select("item");

            for(int i = 0; i < 10; i++){
                Episode episode = new Episode();

                Element e = elements.get(i);
                episode.setTitle(e.select("title").text());
                episode.setDescription(e.select("description").text());
                episode.setUrl(e.select("description").text());
                episode.setImage(e.select("itunes|image").attr("href"));
                episodeList.add(episode);

            }/*
            for (Element e : doc.select("item")) {
                Episode episode = new Episode();

                episode.setTitle(e.select("title").text());
                episode.setDescription(e.select("description").text());
                episode.setUrl(e.select("description").text());
                episode.setImage(e.select("itunes|image").attr("href"));

                /*
                Log.d(TAG, e.select("title").text());
                Log.d(TAG, e.select("enclosure").attr("url"));
                Log.d(TAG, e.select("description").text());

                episodeList.add(episode);
            }*/

            return episodeList;

        //    Log.d(TAG, doc.select("description").get(0).text());
     //       Log.d(TAG, doc.select("image").select("url").text());

            //image

        } catch (IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<String> getUpcoming(){

        //container container-xl
        String TAG = "NEWS__";

        List<String> newsList = new ArrayList<>();
        try{

            Document doc = Jsoup.connect("https://www.ivoox.com/planos-centellas_pr_posts_609149_1.html").get();

            Elements images = doc.select("div.container.container-xl");

            for(int i = 0; i < images.size(); i++){
                newsList.add(images.get(i).select("div.m-bottom-10").select("a").attr("href"));
            }

            return newsList;
        } catch (IOException e) {
            e.printStackTrace();
            return newsList;
        }


    }
}
