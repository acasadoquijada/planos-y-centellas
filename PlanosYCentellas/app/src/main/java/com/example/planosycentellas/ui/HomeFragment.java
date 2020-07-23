package com.example.planosycentellas.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.model.PodcastInfo;
import com.example.planosycentellas.viewmodel.HomeViewModel;
import com.example.planosycentellas.R;
import com.example.planosycentellas.adapter.EpisodeListAdapter;
import com.example.planosycentellas.databinding.HomeFragmentBinding;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding mBinding;

    public HomeFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setupDataBinding(inflater, container);
        return getViewRoot();
    }

    private void setupDataBinding(LayoutInflater inflater, ViewGroup container){
        mBinding = DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false);
    }

    private View getViewRoot(){
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewModel();
    }

    private void setupViewModel(){
        getViewModel();
        observePodcastInfo();
    }

    private void observePodcastInfo(){
        mViewModel.getPodcastInfo().observe(getViewLifecycleOwner(), this::populateUI);
    }

    private void populateUI(PodcastInfo podcastInfo){
        setPodcastTitle(podcastInfo.getName());
        setPodcastImage(podcastInfo.getImage());
        setDescription(podcastInfo.getDescription());
        setContactInfo(podcastInfo.getEmail());
    }

    private void setPodcastTitle(String title){
        mBinding.title.setText(title);
    }

    private void setPodcastImage(String image){
        Picasso.get().load(image).into(mBinding.podcastImage);
    }

    private void setDescription(String description){
        mBinding.description.setText(description);
    }

    private void setContactInfo(String email){
        mBinding.contactInfo.setText(email);
    }

    private void getViewModel(){
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }
}
