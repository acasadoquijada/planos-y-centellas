package com.example.planosycentellas.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planosycentellas.R;
import com.example.planosycentellas.databinding.FragmentPatreonBinding;
import com.example.planosycentellas.databinding.PatreonBinding;
import com.example.planosycentellas.model.PatreonTier;
import com.example.planosycentellas.viewmodel.HomeViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PatreonFragment extends Fragment {

    private FragmentPatreonBinding mBinding;
    private HomeViewModel mViewModel;

    public PatreonFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setupDataBinding(inflater, container);

        return getViewRoot();

    }

    private void setupDataBinding(LayoutInflater inflater, ViewGroup container){
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_patreon,container,false);
    }

    private View getViewRoot(){
        return mBinding.getRoot();
    }

    private void setupUI(List<PatreonTier> patreonTiers){
        for(int i = 0; i < patreonTiers.size(); i++){
            setupPatreonTier(patreonTiers.get(i),i);
        }
    }

    private void setupPatreonTier(PatreonTier patreonTier, int pos){

        PatreonBinding patreonBinding = getPatreonBinding(pos);

        setupOnClickListener(patreonBinding, pos);

        setTitle(patreonBinding, patreonTier.getTitle());

        setImage(patreonBinding, patreonTier.getImage());

        setPrice(patreonBinding, patreonTier.getPrice());
    }

    private PatreonBinding getPatreonBinding(int pos){
        if (pos == 0) {
            return mBinding.patreonTier1;
        } else if(pos == 1){
            return mBinding.patreonTier2;
        } else {
            return mBinding.patreonTier3;
        }
    }

    private void setTitle(PatreonBinding patreonBinding, String name){
        patreonBinding.title.setText(name);
    }

    private void setImage(PatreonBinding patreonBinding, String image){
        Picasso.get().load(image).into(patreonBinding.image);
    }

    private void setPrice(PatreonBinding patreonBinding, String price){
        patreonBinding.price.setText(price);
    }

    private void setupOnClickListener(PatreonBinding patreonBinding, int pos){
        patreonBinding.getRoot().setOnClickListener(v -> {
            NavDirections action =
                    PatreonFragmentDirections.actionPatreonFragmentToPatreonDetailsFragment(pos);
            NavHostFragment.findNavController(this).navigate(action);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewModel();
    }

    private void setupViewModel(){
        getViewModel();
        observeEpisodeList();
    }

    private void getViewModel(){
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    private void observeEpisodeList(){
        mViewModel.getPatreonTierList().observe(getViewLifecycleOwner(), this::setupUI);
    }
}
