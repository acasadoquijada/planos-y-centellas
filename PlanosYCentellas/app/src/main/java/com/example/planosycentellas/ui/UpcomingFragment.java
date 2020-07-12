package com.example.planosycentellas.ui;

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
import com.example.planosycentellas.databinding.FragmentUpcomingBinding;

import java.util.List;


public class UpcomingFragment extends Fragment {

    private HomeViewModel mViewModel;
    private FragmentUpcomingBinding mBinding;
    private NewsAdapter adapter;

    public UpcomingFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_upcoming,container,false);

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

        manager.setOrientation(RecyclerView.VERTICAL);

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
