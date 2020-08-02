package com.example.planosycentellas.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planosycentellas.R;
import com.example.planosycentellas.viewmodel.HomeViewModel;
import com.example.planosycentellas.databinding.FragmentNewsBinding;
import com.squareup.picasso.Picasso;


public class NewsFragment extends Fragment {

    private HomeViewModel mViewModel;
    private FragmentNewsBinding mBinding;

    public NewsFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setupDataBinding(inflater,container);

        return getViewRoot();
    }

    private void setupDataBinding(LayoutInflater inflater,ViewGroup container){
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_news,container,false);
    }

    private View getViewRoot(){
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewModel();
    }

    private void setupViewModel() {
        getViewModel();
        observeNewsImage();
    }

    private void getViewModel(){
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    private void observeNewsImage(){
        mViewModel.getNewsList().observe(getViewLifecycleOwner(), this::loadImage);
    }

    private void loadImage(String image) {
        Picasso.get().load(image).into(mBinding.newsImage);
    }
}
