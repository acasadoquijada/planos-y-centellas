package com.example.planosycentellas;

import android.view.View;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.planosycentellas.ui.HomeFragment;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @Rule
    public final ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void navigateToSeachFragment(){
        onView(withId(R.id.searchFragment)).perform(click());
        onView(withId(R.id.search_label)).check(matches(isDisplayed()));
    }

    @Test
    public void navigateToMoreFragment(){
        onView(withId(R.id.moreFragment)).perform(click());
        onView(withId(R.id.more_label)).check(matches(isDisplayed()));
    }

    @Test
    public void navigateToHomeFragment(){
        onView(withId(R.id.homeFragment)).perform(click());
        onView(withId(R.id.home_label)).check(matches(isDisplayed()));
    }
}
