package com.example.planosycentellas.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planosycentellas.R;
import com.example.planosycentellas.databinding.FragmentSocialNetworkBinding;
import com.squareup.picasso.Picasso;

public class SocialNetworkFragment extends Fragment {

    private FragmentSocialNetworkBinding mBinding;
    public SocialNetworkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_social_network,container,false);

        mBinding.twitterInList.elementImage.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_twitter));
        mBinding.twitterInList.elementText.setText("@planoscentellas");

        mBinding.youtubeInList.elementImage.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_youtube));
        mBinding.youtubeInList.elementText.setText("Planos y Centellas");

        mBinding.instagramInList.elementImage.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_instagram));
        mBinding.instagramInList.elementText.setText("https://www.instagram.com/planos_y_centellas");

        mBinding.itunesInList.elementImage.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_itunes));
        mBinding.itunesInList.elementText.setText("https://podcasts.apple.com/us/podcast/planos-y-centellas/id1444091704");

        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }
}
