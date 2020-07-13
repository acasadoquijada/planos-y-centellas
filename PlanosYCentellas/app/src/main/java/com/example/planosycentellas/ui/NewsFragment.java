package com.example.planosycentellas.ui;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planosycentellas.R;
import com.example.planosycentellas.viewmodel.HomeViewModel;
import com.example.planosycentellas.adapter.NewsAdapter;
import com.example.planosycentellas.databinding.FragmentNewsBinding;

import java.util.List;


public class NewsFragment extends Fragment {

    private HomeViewModel mViewModel;
    private FragmentNewsBinding mBinding;
    private NewsAdapter adapter;

    public NewsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_news,container,false);

        setupRecyclerView();

        return mBinding.getRoot();
    }

    private void setupRecyclerView(){
        RecyclerView recyclerView = mBinding.newListRecyclerView;
        recyclerView.setLayoutManager(createGridLayoutManager());
        adapter = new NewsAdapter();
        recyclerView.setAdapter(adapter);
    }

    private GridLayoutManager createGridLayoutManager(){
        int spanCount = 1;

        GridLayoutManager manager = new GridLayoutManager(getContext(), spanCount);

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            manager.setOrientation(RecyclerView.VERTICAL);
        } else {
            manager.setOrientation(RecyclerView.HORIZONTAL);
        }

        return manager;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        mViewModel.getNewsList().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.setNews(strings);
            }
        });
    }
}
