package com.example.planosycentellas;

import android.content.pm.ActivityInfo;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {

    @Rule
    public final ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void infoIsShownInPortrait(){
        mainActivityActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        infoIsShown();
    }

    @Test
    public void infoIsShownInLandscape(){
        mainActivityActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        infoIsShown();
    }

    private void infoIsShown(){
        onView(withId(R.id.title)).check(matches(not(withText(""))));
        onView(withId(R.id.podcastImage)).check(matches(isDisplayed()));
        onView(withId(R.id.contactInfo)).check(matches(not(withText(""))));
        onView(withId(R.id.description)).check(matches(not(withText(""))));
    }
}
