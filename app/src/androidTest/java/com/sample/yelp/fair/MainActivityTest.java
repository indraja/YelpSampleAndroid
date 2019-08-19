package com.sample.yelp.fair;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.sample.yelp.json_recycler_retrofit", appContext.getPackageName());
    }

    @Test
    public void isGettyCenterVisible() {
        // Context of the app under test.

        onView(withText("The Getty Center")).check(matches(isDisplayed()));
    }

    @Test
    public void isRatingDisplayed() {
        onView(withId(R.id.rating_bar)).check(matches(isDisplayed()));
    }


    @Test
    private void scrollToPosition() {
        ViewInteraction interaction = onView(withId(R.id.scroll_view));
        interaction.perform(scrollTo());
    }

//    @Test
//    private void scrollPhotos(int position) {
//        onView(withId(R.id.pictures_list)).perform(RecyclerViewActions.scrollToPosition(position));
//    }

    @Test
    private void isPhotoRecyclerviewDisplayed() {
        onView(withId(R.id.pictures_list)).check(matches(isDisplayed()));
    }

}
