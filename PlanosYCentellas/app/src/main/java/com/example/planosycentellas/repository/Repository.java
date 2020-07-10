package com.example.planosycentellas.repository;


import android.os.AsyncTask;

import com.example.planosycentellas.api.Provider;

import javax.inject.Inject;

public class Repository {

    private Provider provider;


    public Repository(){
        provider = new Provider();
    }

    public void getData(){

        new FetchData().execute();
    }


    class FetchData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            provider.getData();
            return null;
        }
    }}
