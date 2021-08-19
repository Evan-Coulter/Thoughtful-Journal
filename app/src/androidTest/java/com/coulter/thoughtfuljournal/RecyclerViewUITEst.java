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

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecyclerViewUITEst {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void recyclerViewUITEst() {
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

        ViewInteraction textInputEditText = onView(
                allOf(withText("New Journal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textField),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withText("New Journal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textField),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("Greeting"));

        ViewInteraction textInputEditText3 = onView(
                allOf(withText("Greeting"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textField),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(closeSoftKeyboard());

        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.checkBox), withText("Save as draft?"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        materialCheckBox.perform(click());

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

        ViewInteraction textView = onView(
                allOf(withId(R.id.card_title), withText("Greeting"),
                        withParent(withParent(IsInstanceOf.instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView.check(matches(withText("Greeting")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.journal_text), withText("Hello World!\n\n"),
                        withParent(withParent(IsInstanceOf.instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.card_date), withText("DRAFT"),
                        withParent(withParent(IsInstanceOf.instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView3.check(matches(withText("DRAFT")));
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
