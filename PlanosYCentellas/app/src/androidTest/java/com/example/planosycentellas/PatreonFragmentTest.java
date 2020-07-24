package com.example.planosycentellas;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.planosycentellas.ui.HomeFragment;
import com.example.planosycentellas.ui.PatreonFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)

public class PatreonFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> testRule =
            new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setup(){
        onView(withId(R.id.patreonFragment)).perform(click());
    }


    @Test
    public void tier1IsShown(){
        tierIsShown(R.id.patreonTier1);
    }

    @Test
    public void tier2IsShown(){
        tierIsShown(R.id.patreonTier2);
    }

    @Test
    public void tier3IsShown(){
        tierIsShown(R.id.patreonTier3);
    }


    private void tierIsShown(int id) {
        onView(allOf(withId(R.id.title), isDescendantOfA(withId(id)))).check(matches(not(withText(""))));
        onView(allOf(withId(R.id.price), isDescendantOfA(withId(id)))).check(matches(not(withText(""))));
    }

}
