package com.example.planosycentellas.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

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

public class PatreonDetailsFragment extends Fragment {

    private HomeViewModel mViewModel;
    private FragmentPatreonDetailsBinding mBinding;
    private int pos;

    public PatreonDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_patreon_details,container,false);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        getPatreonTierPosition();

        setupUI();
    }

    private void getPatreonTierPosition(){

        if(getArguments() != null){
            PatreonDetailsFragmentArgs args = PatreonDetailsFragmentArgs.fromBundle(getArguments());
            pos = args.getPos();
        }
    }

    private void setupUI(){
        mBinding.title.setText(mViewModel.getPatreonTierList().getValue().get(pos).getTitle());

        Picasso.get().load(mViewModel.getPatreonTierList().getValue().get(pos).getImage()).into(mBinding.logo);

        mBinding.price.setText(mViewModel.getPatreonTierList().getValue().get(pos).getPrice());

        mBinding.awards.setText(mViewModel.getPatreonTierList().getValue().get(pos).getRewards());

        mBinding.joinButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mViewModel.getPatreonTierList().getValue().get(pos).getLink()));
                startActivity(browserIntent);
            }
        });/*
        mBinding.joinButton.setOnClickListener(v -> Toast.makeText(requireContext(),
                mViewModel.getPatreonTierList().getValue().get(pos).getLink(),Toast.LENGTH_SHORT).show());*/

    }
}
