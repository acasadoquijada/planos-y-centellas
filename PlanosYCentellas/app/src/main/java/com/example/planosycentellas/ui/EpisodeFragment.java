package com.example.planosycentellas.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.planosycentellas.R;
import com.example.planosycentellas.adapter.EpisodeListAdapter;
import com.example.planosycentellas.databinding.FragmentEpisodeBinding;

import com.example.planosycentellas.model.Episode;
import com.example.planosycentellas.repository.Repository;
import com.example.planosycentellas.viewmodel.HomeViewModel;
import com.example.planosycentellas.viewmodel.PlayerViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EpisodeFragment extends Fragment implements EpisodeListAdapter.ItemClickListener{

    private HomeViewModel mViewModel;
    private PlayerViewModel playerViewModel;
    private FragmentEpisodeBinding mBinding;
    private EpisodeListAdapter adapter;

    public static EpisodeFragment newInstance(){
            return new EpisodeFragment();
        }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState){

        setupDataBinding(inflater,container);

        setupRecyclerView();

        return getViewRoot();
    }

    private void setupDataBinding(LayoutInflater inflater,ViewGroup container){
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_episode,container,false);
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
        GridLayoutManager manager = new GridLayoutManager(getContext(),1);

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
        observeEpisodeList();
    }

    private void getViewModel(){
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        playerViewModel =  new ViewModelProvider(requireActivity()).get(PlayerViewModel.class);
    }

    private void observeEpisodeList(){
        mViewModel.getEpisodeList().observe(getViewLifecycleOwner(),this::setEpisodeListAdapter);
    }

    private void setEpisodeListAdapter(List<Episode> episodeList){
        adapter.setEpisodes(episodeList);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();

        if (itemThatWasClickedId == R.id.action_search){
            navigateToSearchFragment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void navigateToSearchFragment(){
        NavDirections action =
                EpisodeFragmentDirections.actionEpisodeFragmentToSearchFragment();
        NavHostFragment.findNavController(this).navigate(action);
    }


    @Override
    public void onItemClick(int clickedItem){
        playerViewModel.setEpisode(mViewModel.getEpisode(clickedItem));
    }
}

