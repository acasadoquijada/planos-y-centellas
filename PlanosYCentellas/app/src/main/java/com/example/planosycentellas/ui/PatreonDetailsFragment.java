package com.example.planosycentellas.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.planosycentellas.R;
import com.example.planosycentellas.databinding.FragmentPatreonDetailsBinding;
import com.example.planosycentellas.viewmodel.HomeViewModel;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class PatreonDetailsFragment extends Fragment {

    private HomeViewModel mViewModel;
    private FragmentPatreonDetailsBinding mBinding;
    private int patreonTierIndex;

    public PatreonDetailsFragment(){}

    @Override
    @NonNull
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        setupDataBinding(inflater, container);
        return getViewRoot();
    }

    private void setupDataBinding(LayoutInflater inflater, ViewGroup container){
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_patreon_details,container,false);
    }

    private View getViewRoot(){
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getViewModel();
        getPatreonTierIndex();
        setupUI();
    }

    private void getViewModel(){
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    private void getPatreonTierIndex(){

        if(getArguments() != null){
            PatreonDetailsFragmentArgs args = PatreonDetailsFragmentArgs.fromBundle(getArguments());
            patreonTierIndex = args.getPos();
        }
    }

    private void setupUI(){
        setupTitle();
        setupLogo();
        setupPrice();
        setupAwards();
        setupJoinButtonListener();
    }


    private void setupTitle(){
        mBinding.title.setText(Objects.requireNonNull(mViewModel.getPatreonTierList().getValue()).get(patreonTierIndex).getTitle());
    }

    private void setupLogo(){
        Picasso.get().load(Objects.requireNonNull(mViewModel.getPatreonTierList().getValue()).get(patreonTierIndex).getImage()).into(mBinding.logo);
    }

    private void setupPrice(){
        mBinding.price.setText(Objects.requireNonNull(mViewModel.getPatreonTierList().getValue()).get(patreonTierIndex).getPrice());
    }

    private void setupAwards(){
        setAwardInitialMessage();
        setAwardDetails();
    }

    private void setAwardInitialMessage(){
        mBinding.awardsInitialMessage.setText(
                Objects.requireNonNull(mViewModel.getPatreonTierList().getValue()).get(patreonTierIndex).getAwards().getInitialMessage());
    }

    private void setAwardDetails(){

        int size = Objects.requireNonNull(mViewModel.getPatreonTierList().getValue()).get(patreonTierIndex).getAwards().getAwardsDetails().size();

        for(int i = 0; i < size; i++){
            addAwardsDetail(
                    mViewModel.getPatreonTierList().getValue().get(patreonTierIndex).getAwards().getAwardsDetails().get(i));
        }
    }

    private void addAwardsDetail(String awardsDetail){
        mBinding.awardsDetails.append(awardsDetail);
        mBinding.awardsDetails.append("\n\n");
    }

    private void setupJoinButtonListener(){
        mBinding.joinButton.setOnClickListener(v -> launchActivity());
    }

    private void launchActivity(){

        PackageManager packageManager = requireActivity().getPackageManager();

        Intent browserIntent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(Objects.requireNonNull(mViewModel.getPatreonTierList().getValue()).get(patreonTierIndex).getLink()));
        if (browserIntent.resolveActivity(packageManager) != null) {
            startActivity(browserIntent);
        } else{
            Toast.makeText(requireContext(),"There is an error with the patreon link.\nPlease go to https://www.patreon.com/planosycentellas", Toast.LENGTH_LONG).show();
        }
    }
}
