package com.example.planosycentellas.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.planosycentellas.R;
import com.example.planosycentellas.adapter.EpisodeListAdapter;
import com.example.planosycentellas.databinding.FragmentSearchBinding;
import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.viewmodel.HomeViewModel;
import com.example.planosycentellas.viewmodel.PlayerViewModel;

import java.util.List;


public class SearchFragment extends Fragment implements EpisodeListAdapter.ItemClickListener{

    private FragmentSearchBinding mBinding;
    private HomeViewModel mViewModel;
    private PlayerViewModel playerViewModel;
    private EpisodeListAdapter adapter;

    public SearchFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setupDataBinding(inflater,container);
        setupSearchView();
        setupRecyclerView();

        return getViewRoot();
    }

    private void setupDataBinding(LayoutInflater inflater, ViewGroup container){
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search,container,false);
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
        GridLayoutManager manager=new GridLayoutManager(getContext(),1);

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setupViewModel();
    }

    private void setupViewModel() {
        getViewModel();
        observeSearchQuery();
        observeSearchedEpisodes();
    }

    private void getViewModel(){
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        playerViewModel =  new ViewModelProvider(requireActivity()).get(PlayerViewModel.class);

    }

    private void observeSearchedEpisodes(){
        mViewModel.getSearchedEpisodes().observe(getViewLifecycleOwner(),
                this::handleEpisodeList);
    }

    private void handleEpisodeList(List<Episode> episodeList){
        if(episodeList.size() > 0){
            setEpisodes(episodeList);
        } else {
            showToast();
        }
    }

    private void showToast(){
        Toast.makeText(requireContext(),"No hay episodios correspondientes a tu busqueda",Toast.LENGTH_SHORT).show();
    }

    private void setEpisodes(List<Episode> episodeList){
        adapter.setEpisodes(episodeList);
    }
    private void observeSearchQuery(){
        mViewModel.getSearchQuery().observe(getViewLifecycleOwner(), s -> mViewModel.searchEpisodes());
    }
    private void setupSearchView(){
        SearchView searchView = mBinding.searchView;

        // Click anywhere will open the keyboard
        searchView.setOnClickListener(v -> {
            if (v.getId() == R.id.searchView) {
                searchView.onActionViewExpanded();
            }
        });

        // We query new data from the ViewModel according to the query result
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.getSearchQuery().setValue(query);
               // searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onItemClick(int clickedItem) {
        playerViewModel.setEpisode(mViewModel.getEpisode(clickedItem));
    }
}