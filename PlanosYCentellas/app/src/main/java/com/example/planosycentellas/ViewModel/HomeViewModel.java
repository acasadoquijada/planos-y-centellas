package com.example.planosycentellas.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.planosycentellas.repository.Repository;

public class HomeViewModel extends ViewModel {
    private Repository repository;

    public HomeViewModel(){
        repository = new Repository();
    }

    public void getData(){
        repository.getData();
    }
}
