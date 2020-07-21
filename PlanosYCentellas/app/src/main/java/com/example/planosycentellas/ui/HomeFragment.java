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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    HomeViewModel mViewModel;

    private HomeFragmentBinding mBinding;


    public static HomeFragment newInstance() {
        return new HomeFragment();
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
     //   observeEpisodeList();
        mViewModel.getPatreonTierList();

        mViewModel.getPodcastInfo().observe(getViewLifecycleOwner(), new Observer<PodcastInfo>() {
            @Override
            public void onChanged(PodcastInfo podcastInfo) {
                mBinding.title.setText(podcastInfo.getName());
                Picasso.get().load(podcastInfo.getImage()).into(mBinding.podcastImage);
                mBinding.description.setText(podcastInfo.getDescription());
                mBinding.contactInfo.setText(podcastInfo.getEmail());

                mBinding.contactInfo.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        startActivity(emailIntent);

                    }
                });
            }
        });
    }

    private void getViewModel(){
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }
}
