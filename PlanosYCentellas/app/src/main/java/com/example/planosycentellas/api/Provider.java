package com.example.planosycentellas.api;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import dagger.Module;
import dagger.Provides;

public class Provider {

    private final String url =
            "https://www.ivoox.com/podcast-planos-centellas_fg_f1609149_filtro_1.xml";


    public void getData(){

        String TAG = "TESTING__";
        try{
            Document doc = Jsoup.connect(url).get().parser(Parser.xmlParser());
/*
            for (Element e : doc.select("item")) {
                Log.d(TAG, e.select("title").text());
                Log.d(TAG, e.select("enclosure").attr("url"));
                Log.d(TAG, e.select("description").text());
            }*/

            Log.d(TAG, doc.select("description").get(0).text());
            Log.d(TAG, doc.select("image").select("url").text());

            //image


        } catch (Exception e){
            Log.w("Exception", e);
        }

    }

}
