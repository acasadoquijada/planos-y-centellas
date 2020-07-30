package com.example.planosycentellas;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.annotation.ContentView;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)

public class SocialNetworkFragmentTest {

    @Rule
    public IntentsTestRule<MainActivity> testRule =
            new IntentsTestRule<>(MainActivity.class);

    @Before
    public void stubIntent(){
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);
        intending(not(isInternal())).respondWith(result);
        onView(withId(R.id.socialNetworkFragment)).perform(click());
    }

    @Test
    public void checkTwitterImageButton(){
        onView(withId(R.id.twitter)).perform(click());
        checkIntentContent("https://twitter.com/planoscentellas?lang=en");
    }

    @Test
    public void checkIvooxImageButton(){
        onView(withId(R.id.ivoox)).perform(click());
        checkIntentContent("https://www.ivoox.com/podcast-planos-centellas_sq_f1609149_1.html");
    }

    @Test
    public void checkYoutubeImageButton(){
        onView(withId(R.id.youtube)).perform(click());
        checkIntentContent("https://www.youtube.com/channel/UCLacP2BYwAAJISa7-fAj64g");
    }

    @Test
    public void checkInstagramImageButton(){
        onView(withId(R.id.instagram)).perform(click());
        checkIntentContent("https://www.instagram.com/planos_y_centellas/?hl=en");
    }

    @Test
    public void checkItunesImageButton(){
        onView(withId(R.id.itunes)).perform(click());
        checkIntentContent("https://podcasts.apple.com/us/podcast/planos-y-centellas/id1444091704");
    }

    @Test
    public void checkFacebookImageButton(){
        onView(withId(R.id.facebook)).perform(click());
        checkIntentContent("https://www.facebook.com/pages/category/Podcast/Planos-y-Centellas-1950069131742290/");
    }

    @Test
    public void checkSpotifyImageButton(){
        onView(withId(R.id.spotify)).perform(click());
        checkIntentContent("https://open.spotify.com/show/78SRCbyUZei41U33ZkVDme");
    }

    private void checkIntentContent(String url){
        intended(hasAction(Intent.ACTION_VIEW));
        intended(hasData(url));
    }

}
