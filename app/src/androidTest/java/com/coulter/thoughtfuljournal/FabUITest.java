package com.coulter.thoughtfuljournal;


import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FabUITest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void fabUITest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.fab), withText("New Journal"), withContentDescription("New Journal Button"),
                        withParent(withParent(withId(R.id.fab_container_main))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction extendedFloatingActionButton = onView(
                allOf(withId(R.id.fab), withText("New Journal"), withContentDescription("New Journal Button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fab_container_main),
                                        0),
                                0),
                        isDisplayed()));
        extendedFloatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatEditText.perform(scrollTo(), replaceText("Hello World!"), closeSoftKeyboard());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.saveButton), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.app_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withParent(allOf(withId(android.R.id.content),
                        withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction textInputEditText = onView(
                allOf(withText("New Journal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textField),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Greeting"));

        ViewInteraction textInputEditText2 = onView(
                allOf(withText("Greeting"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textField),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText2.perform(closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.dialogSaveButton), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        pressBack();

        ViewInteraction viewGroup = onView(
                allOf(withParent(withParent(withId(R.id.recycler_view))),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.fab), withText("New Journal"), withContentDescription("New Journal Button"),
                        withParent(withParent(withId(R.id.fab_container_main))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
