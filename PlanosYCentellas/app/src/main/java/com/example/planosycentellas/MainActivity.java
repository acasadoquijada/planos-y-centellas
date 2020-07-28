package com.example.planosycentellas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.planosycentellas.databinding.ActivityMainBinding;
import com.example.planosycentellas.viewmodel.PlayerViewModel;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.squareup.picasso.Picasso;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private PlayerViewModel playerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setupDataBinding();
        setUpBottonNavigation();
        setupReproducerViewModel();

        setupSlidingUpPanel();

    }

    private void setupSlidingUpPanel(){
        setupPanelSlideListener();
        setupSlidingPanelSwipeLayout();
    }

    private void setupSlidingPanelSwipeLayout(){
        mBinding.reproducer.reproducerSlidingPanel.swipeLayout.setOnSwipeListener(new SwipeLayout.OnSwipeListener() {
            @Override
            public void onBeginSwipe(SwipeLayout swipeLayout, boolean moveToRight) {
                //nothing to do
            }

            @Override
            public void onSwipeClampReached(SwipeLayout swipeLayout, boolean moveToRight) {
                if(!moveToRight){
                    hideSlidingPanel();
                }
            }

            @Override
            public void onLeftStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {
                //nothing to do
            }

            @Override
            public void onRightStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {
                //nothing to do
            }
        });
    }


    private void hideSlidingPanel(){
        setSlidingPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
    }



    private void setupPanelSlideListener(){

        mBinding.slidingLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                changeAlphaSlidingElements(slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState,
                                            SlidingUpPanelLayout.PanelState newState) {
                // We don't want to change this behavior. But it needs to be override
            }
        });
    }

    /**
     * To calculate the new alpha value to guarantees a smooth transition when the panel is slided
     * @param slideOffset needed for the calculation
     * @return the new alpha value
     */
    private float calculateNewAlphaValue(float slideOffset){
        return 1-(6*slideOffset);
    }

    /**
     * To change the alpha value of the slingindEpisodeImage
     */

    private void changeAlphaValueSlingindEpisodeImage(float newAlphaValue){
        mBinding.reproducer.reproducerSlidingPanel.slingindEpisodeImage.setAlpha(newAlphaValue);
    }

    /**
     * To change the alpha value of the slingindEpisodeImage
     */

    private void changeAlphaValueSlidingName(float newAlphaValue){
        mBinding.reproducer.reproducerSlidingPanel.slidingName.setAlpha(newAlphaValue);
    }

    /**
     * To change the alpha value of the slingindEpisodeImage
     */

    private void changeAlphaValueSlidingMediaReproducer(float newAlphaValue){
        mBinding.reproducer.reproducerSlidingPanel.slidingMediaReproducer.setAlpha(newAlphaValue);
    }

    private void changeAlphaSlidingElements(float slideOffset){

        final float newAlphaValue = calculateNewAlphaValue(slideOffset);

        changeAlphaValueSlingindEpisodeImage(newAlphaValue);
        changeAlphaValueSlidingName(newAlphaValue);
        changeAlphaValueSlidingMediaReproducer(newAlphaValue);
    }

    private void setUpBottonNavigation(){
        NavigationUI.setupWithNavController(mBinding.bottomNavigation,
                Navigation.findNavController(this, R.id.nav_host_fragment));
    }

    private void setupDataBinding(){
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
    }

    private void showSlidingPanel(){
        resetSwipeLayout();
        setSlidingPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

    private void resetSwipeLayout(){
        mBinding.reproducer.reproducerSlidingPanel.swipeLayout.reset();
    }

    private void setSlidingPanelState(SlidingUpPanelLayout.PanelState state){
        mBinding.slidingLayout.setPanelState(state);
    }

    private void setupReproducerViewModel(){
        getViewModel();
        observeEpisode();
    }

    private void getViewModel(){
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
    }

    private void observeEpisode(){
        playerViewModel.getEpisode().observe(this, episode ->{
            showSlidingPanel();
            setImages(episode.getImage());
            setDescription(episode.getDescription());
            setName(episode.getTitle());
        });
    }

    private void setImages(String image){
        Picasso.get().load(image).resize(75,75).into(mBinding.reproducer.reproducerSlidingPanel.slingindEpisodeImage);
        Picasso.get().load(image).into(mBinding.reproducer.mainEpisodeImage);
    }

    private void setDescription(String description){
        mBinding.reproducer.mainEpsiodeDescription.setText(description);
    }

    private void setName(String name){
        mBinding.reproducer.reproducerSlidingPanel.slidingName.setText(name);

    }

}
