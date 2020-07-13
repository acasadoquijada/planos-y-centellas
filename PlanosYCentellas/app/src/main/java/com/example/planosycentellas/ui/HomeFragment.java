package com.example.planosycentellas.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

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
import com.example.planosycentellas.viewmodel.HomeViewModel;
import com.example.planosycentellas.R;
import com.example.planosycentellas.adapter.EpisodeListAdapter;
import com.example.planosycentellas.databinding.HomeFragmentBinding;

import java.util.List;

public class HomeFragment extends Fragment implements EpisodeListAdapter.ItemClickListener {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding mBinding;
    private EpisodeListAdapter adapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setupDataBinding(inflater, container);

        setupRecyclerView();

        return getViewRoot();
    }

    private void setupDataBinding(LayoutInflater inflater, ViewGroup container){
        mBinding = DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false);
    }

    private View getViewRoot(){
        return mBinding.getRoot();
    }

    private void setupRecyclerView(){
        setupLayoutManager();
        setupAdapter();
    }


    private void setupLayoutManager(){
        getRecyclerView().setLayoutManager(createGridLayoutManager());
    }

    private GridLayoutManager createGridLayoutManager(){
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);

        manager.setOrientation(RecyclerView.VERTICAL);

        return manager;
    }


    private void setupAdapter(){
        createAdapter();
        setAdapter();
    }

    private void createAdapter(){
        adapter = new EpisodeListAdapter(this);
    }

    private void setAdapter(){
        getRecyclerView().setAdapter(adapter);
    }

    private RecyclerView getRecyclerView(){
        return mBinding.episodeListRecyclerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewModel();
    }

    private void setupViewModel(){
        getViewModel();
        observeEpisodeList();
        mViewModel.getPatreonTierList();
    }

    private void getViewModel(){
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    private void observeEpisodeList(){
        mViewModel.getEpisodeList().observe(getViewLifecycleOwner(), this::setEpisodeListAdapter);
    }

    private void setEpisodeListAdapter(List<Episode> episodeList){
        adapter.setEpisodes(episodeList);
    }

    @Override
    public void onItemClick(int clickedItem, boolean delete) {
        Toast.makeText(requireContext(),"" + clickedItem,Toast.LENGTH_SHORT).show();
    }
}
