package org.odk.collect.android.espressoutils.pages;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.odk.collect.android.R;
import org.odk.collect.android.support.ActivityHelpers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.StringEndsWith.endsWith;

public class FormEntryPage extends Page<FormEntryPage> {

    private final String formName;

    public FormEntryPage(String formName, ActivityTestRule rule) {
        super(rule);
        this.formName = formName;
    }

    @Override
    public FormEntryPage assertOnPage() {
        onView(allOf(withText(formName), isDescendantOfA(withId(R.id.toolbar)))).check(matches(isDisplayed()));
        return this;
    }

    public FormEntryPage clickOnGoToIconInForm() {
        onView(withId(R.id.menu_goto)).perform(click());
        return this;
    }

    public FormEntryPage clickJumpEndButton() {
        onView(withId(R.id.jumpEndButton)).perform(click());
        return this;
    }

    public MainMenuPage clickSaveAndExit() {
        onView(withId(R.id.save_exit_button)).perform(click());
        return new MainMenuPage(rule).assertOnPage();
    }

    public FormEntryPage clickSaveAndExitWithError() {
        onView(withId(R.id.save_exit_button)).perform(click());
        return this;
    }

    public FormEntryPage swipeToNextQuestion() {
        onView(withId(R.id.questionholder)).perform(swipeLeft());
        return this;
    }

    public FormEntryPage swipeToNextQuestion(int repetitions) {
        for (int i = 0; i < repetitions; i++) {
            swipeToNextQuestion();
        }
        return this;
    }

    public ErrorDialog swipeToNextQuestionWithError() {
        onView(withId(R.id.questionholder)).perform(swipeLeft());
        return new ErrorDialog(rule);
    }

    public FormEntryPage clickOptionsIcon() {
        Espresso.openActionBarOverflowOrOptionsMenu(ActivityHelpers.getActivity());
        return this;
    }

    public FormEntryPage clickOnLaunchButton() {
        onView(withText(getTranslatedString(R.string.launch_app))).perform(click());
        return this;
    }

    public GeneralSettingsPage clickGeneralSettings() {
        onView(withText(getTranslatedString(R.string.general_preferences))).perform(click());
        return new GeneralSettingsPage(rule).assertOnPage();
    }

    public FormEntryPage checkAreNavigationButtonsDisplayed() {
        onView(withId(R.id.form_forward_button)).check(matches(isDisplayed()));
        onView(withId(R.id.form_back_button)).check(matches(isDisplayed()));
        return this;
    }

    public FormEntryPage swipeToPreviousQuestion() {
        onView(withId(R.id.questionholder)).perform(swipeRight());
        return this;
    }

    public FormEntryPage clickGoToIconInForm() {
        onView(withId(R.id.menu_goto)).perform(click());
        return this;
    }

    public FormEntryPage swipeOnText(String text) {
        onView(withClassName(endsWith("EditText"))).check(matches(withText(text))).perform(swipeLeft());
        return this;
    }

    public FormEntryPage clickForwardButton() {
        onView(withText(getTranslatedString(R.string.form_forward))).perform(click());
        return this;
    }

    public FormEntryPage clickOnDoNotAddGroup() {
        clickOnString(R.string.add_repeat_no);
        return this;
    }
}
