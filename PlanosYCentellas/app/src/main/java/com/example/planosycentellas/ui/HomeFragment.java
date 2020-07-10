package com.example.planosycentellas.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.planosycentellas.ViewModel.HomeViewModel;
import com.example.planosycentellas.R;
import com.example.planosycentellas.adapter.EpisodeListAdapter;
import com.example.planosycentellas.databinding.HomeFragmentBinding;
import com.example.planosycentellas.model.Episode;
import com.squareup.picasso.Picasso;

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

        mBinding = DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false);


        setupRecyclerView();
        return mBinding.getRoot();
    }

    private void setupRecyclerView(){
        RecyclerView recyclerView = mBinding.episodeListRecyclerView;
        recyclerView.setLayoutManager(createGridLayoutManager());
        adapter = new EpisodeListAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private GridLayoutManager createGridLayoutManager(){
        int spanCount = 1;

        GridLayoutManager manager = new GridLayoutManager(getContext(), spanCount);

        manager.setOrientation(RecyclerView.VERTICAL);

        return manager;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        mViewModel.getEpisodes().observe(getViewLifecycleOwner(), new Observer<List<Episode>>() {
            @Override
            public void onChanged(List<Episode> episodes) {

                adapter.setEpisodes(episodes);
         //       Log.d("TESTING__","IM UPDATED" + episodes.get(0).getImage());

          //      Picasso.get().load(episodes.get(0).getImage()).into(mBinding.include.episodeImage);
            //    mBinding.include.episodeTitle.setText(episodes.get(0).getTitle());
            }
        });

    }

    @Override
    public void onItemClick(int clickedItem, boolean delete) {
        Toast.makeText(requireContext(),"" + clickedItem,Toast.LENGTH_SHORT).show();
    }
}
