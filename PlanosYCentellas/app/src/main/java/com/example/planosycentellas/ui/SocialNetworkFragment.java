package com.example.planosycentellas.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.planosycentellas.R;
import com.example.planosycentellas.databinding.FragmentSocialNetworkBinding;
import com.example.planosycentellas.model.SocialNetwork;

import java.util.ArrayList;
import java.util.List;

public class SocialNetworkFragment extends Fragment {

    private FragmentSocialNetworkBinding mBinding;

    private List<SocialNetwork> socialNetworkList;

    private final String ivoox = "ivoox";
    private final String instagram = "instagram";
    private final String youtube = "youtube";
    private final String facebook = "facebook";
    private final String itunes = "itunes";
    private final String twitter = "twitter";
    private final String spotify = "spotify";

    private final String ivoox_url = "https://www.ivoox.com/podcast-planos-centellas_sq_f1609149_1.html";
    private final String youtube_url = "https://www.youtube.com/channel/UCLacP2BYwAAJISa7-fAj64g";
    private final String twitter_url = "https://twitter.com/planoscentellas?lang=en";
    private final String instagram_url = "https://www.instagram.com/planos_y_centellas/?hl=en";
    private final String itunes_url = "https://podcasts.apple.com/us/podcast/planos-y-centellas/id1444091704";
    private final String facebook_url = "https://www.facebook.com/pages/category/Podcast/Planos-y-Centellas-1950069131742290/";
    private final String spotify_url = "https://open.spotify.com/show/78SRCbyUZei41U33ZkVDme";



    public SocialNetworkFragment() {}

    private void setupSocialLinkList(){

        socialNetworkList = new ArrayList<>();

        socialNetworkList.add(new SocialNetwork(ivoox,ivoox_url));
        socialNetworkList.add(new SocialNetwork(instagram,instagram_url));
        socialNetworkList.add(new SocialNetwork(youtube,youtube_url));
        socialNetworkList.add(new SocialNetwork(facebook,facebook_url));
        socialNetworkList.add(new SocialNetwork(itunes,itunes_url));
        socialNetworkList.add(new SocialNetwork(twitter,twitter_url));
        socialNetworkList.add(new SocialNetwork(spotify,spotify_url));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setupDataBinding(inflater, container);

        setupSocialLinkList();

        setupOnClickListenerSocialNetworks();

        return getRoot();
    }

    private void setupDataBinding(LayoutInflater inflater, ViewGroup container){
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_social_network,container,false);
    }

    private View getRoot(){
        return mBinding.getRoot();
    }

    private void setupOnClickListenerSocialNetworks(){
        for(SocialNetwork socialNetwork: socialNetworkList){
            setupOnClickListener(socialNetwork);
        }
    }

    private ImageView getSocialNetworkImageView(String name){

        switch (name){
            case ivoox:
                return mBinding.ivoox;
            case youtube:
                return mBinding.youtube;
            case twitter:
                return mBinding.twitter;
            case instagram:
                return mBinding.instagram;
            case itunes:
                return mBinding.itunes;
            case facebook:
                return mBinding.facebook;
            case spotify:
                return mBinding.spotify;
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }

    }

    private void setupOnClickListener(SocialNetwork socialNetwork) {

        try {

            ImageView socialNetworkImageView;
            socialNetworkImageView = getSocialNetworkImageView(socialNetwork.getName());
            socialNetworkImageView.setOnClickListener(v -> launchActivity(socialNetwork.getUrl()));

        } catch (IllegalStateException e){
            e.printStackTrace();
        }
    }

    private void launchActivity(String url){

        PackageManager packageManager = requireActivity().getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent);
        } else{
            Toast.makeText(requireContext(),"There is an error\nGo to: " + url,Toast.LENGTH_SHORT).show();
        }
    }
}
