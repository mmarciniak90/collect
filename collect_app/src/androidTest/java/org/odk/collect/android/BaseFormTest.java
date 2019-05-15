package org.odk.collect.android;

import android.Manifest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.odk.collect.android.activities.MainMenuActivity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

public class BaseFormTest {

    @Rule
    public ActivityTestRule<MainMenuActivity> main = new ActivityTestRule<>(MainMenuActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    DataInteraction scrollToShowGuidanceForQuestion = onData(anything())
            .inAdapterView(allOf(withId(android.R.id.list),
                    childAtPosition(
                            withId(android.R.id.list_container),
                            0)))
            .atPosition(2);

    DataInteraction showGuidanceDialog = onData(anything())
            .inAdapterView(allOf(withId(android.R.id.list),
                    childAtPosition(
                            withId(android.R.id.list_container),
                            0)))
            .atPosition(12);

    protected static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                description.appendText(" , ");
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
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

    protected void clickGoToIcon() {
        onView(withId(R.id.menu_goto)).perform(click());
    }

    protected void clickFillBlankForm() {
        onView(withId(R.id.enter_data)).perform(click());
    }

    protected void clickOptionsIcon() {
        onView(withContentDescription("More options")).perform(click());
    }

    protected void clickGeneralSettings() {
        onView(withText("General Settings")).perform(click());
    }

    protected void clickFormManagement() {
        onView(withText("Form management")).perform(click());
    }

    protected void clickShowGuidanceAlways() {
        onView(withText("Yes - always shown")).perform(click());
    }

    protected void clickSaveAndExit() {
        onView(withId(R.id.save_exit_button)).perform(click());
    }

    protected void clickShowGuidanceCollapsed() {
        onView(withText("Yes - collapsed")).perform(click());
    }

    protected void clickJumpEndButton() {
        onView(withId(R.id.jumpEndButton)).perform(click());
    }

    protected void checkIsDisplayed(String message) {
        onView(withText(message)).check(matches(isDisplayed()));
    }

    protected void checkIsToastWithMessageDisplayes(String message) {
        onView(withText(message)).inRoot(withDecorView(not(is(main.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    protected void clickOnText(String message) {
        onView(withText(message)).perform(click());
    }
}
