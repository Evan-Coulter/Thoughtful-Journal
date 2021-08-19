package com.coulter.thoughtfuljournal;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppBarUITest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    //Requires an empty recycler view for this to work.
    @Test
    public void appBarUITest() {
        onView(withId(R.id.sortButton)).check(matches(isDisplayed()));
        onView(withId(R.id.searchButton)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.saveButton)).check(matches(isDisplayed()));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withId(R.id.dialogSaveButton)).perform(click());
        pressBack();
        onView(withId(R.id.sortButton)).check(matches(isDisplayed()));
        onView(withId(R.id.searchButton)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerViewCard)).perform(click());
        onView(withId(R.id.dynamicSizeButton)).check(matches(isDisplayed()));
    }
}
