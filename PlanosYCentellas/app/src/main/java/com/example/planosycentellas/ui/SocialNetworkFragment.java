package com.example.planosycentellas.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
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
import com.squareup.picasso.Picasso;

import java.util.List;

public class SocialNetworkFragment extends Fragment {

    private FragmentSocialNetworkBinding mBinding;

    private final String ivoox = "ivoox";
    private final String instagram = "instagram";
    private final String youtube = "youtube";
    private final String facebook = "facebook";
    private final String itunes = "itunes";
    private final String twitter = "twitter";

    public SocialNetworkFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setupDataBinding(inflater, container);

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
        setupOnClickListenerIvoox();
        setupOnClickListenerYoutube();
        setupOnClickListenerTwitter();
        setupOnClickListenerInstagram();
        setupOnClickListenerItunes();
        setupOnClickListenerFacebook();
    }

    private void setupOnClickListener(String socialNetwork) {

        ImageView socialNetworkImageView;
        String url;

        switch (socialNetwork) {
            case ivoox:
                socialNetworkImageView = mBinding.ivoox;
                url = "https://www.ivoox.com/podcast-planos-centellas_sq_f1609149_1.html";
                break;
            case youtube:
                socialNetworkImageView = mBinding.youtube;
                url = "https://www.youtube.com/channel/UCLacP2BYwAAJISa7-fAj64g";
                break;
            case twitter:
                socialNetworkImageView = mBinding.twitter;
                url = "https://twitter.com/planoscentellas?lang=en";
                break;
            case instagram:
                socialNetworkImageView = mBinding.instagram;
                url = "https://www.instagram.com/planos_y_centellas/?hl=en";
                break;
            case itunes:
                socialNetworkImageView = mBinding.itunes;
                url = "https://podcasts.apple.com/us/podcast/planos-y-centellas/id1444091704";
                break;
            case facebook:
                socialNetworkImageView = mBinding.facebook;
                url = "https://www.facebook.com/pages/category/Podcast/Planos-y-Centellas-1950069131742290/";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + socialNetwork);
        }

        socialNetworkImageView.setOnClickListener(v -> launchActivity(url));

    }


    private void setupOnClickListenerIvoox(){
        setupOnClickListener(ivoox);
    }

    private void setupOnClickListenerYoutube(){
        setupOnClickListener(youtube);
    }

    private void setupOnClickListenerInstagram(){
        setupOnClickListener(instagram);
    }

    private void setupOnClickListenerItunes(){
        setupOnClickListener(itunes);
    }

    private void setupOnClickListenerTwitter(){
        setupOnClickListener(twitter);

    }

    private void setupOnClickListenerFacebook(){
        setupOnClickListener(facebook);
    }

    private void launchActivity(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
