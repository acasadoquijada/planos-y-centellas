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

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_news,container,false);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        mViewModel.getNewsList().observe(getViewLifecycleOwner(), s -> Picasso.get().load(s).into(mBinding.newsImage));
    }
}
