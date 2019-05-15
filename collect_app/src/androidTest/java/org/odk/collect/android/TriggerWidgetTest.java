package org.odk.collect.android;

import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

// Issue number NODK-415
@RunWith(AndroidJUnit4.class)
public class TriggerWidgetTest extends BaseFormTest {

    @Test
    public void testActivityOpen() {

        //TestCase1
        clickOptionsIcon();
        clickGeneralSettings();
        clickFormManagement();
        scrollToShowGuidanceForQuestion.perform(click());
        showGuidanceDialog.perform(click());
        clickShowGuidanceAlways();
        pressBack();
        pressBack();
        clickFillBlankForm();
        clickOnText("Guidance Form Sample");
        clickGoToIcon();
        clickOnText("Trigger");
        onView(withText("Trigger")).perform(swipeLeft());
        clickSaveAndExit();

        //TestCase2
        clickOptionsIcon();
        clickGeneralSettings();
        clickFormManagement();
        scrollToShowGuidanceForQuestion.perform(click());
        showGuidanceDialog.perform(click());
        clickShowGuidanceCollapsed();
        pressBack();
        pressBack();
        clickFillBlankForm();
        clickOnText("Guidance Form Sample");
        clickGoToIcon();
        clickOnText("Trigger");
        onView(withText("Trigger")).perform(swipeLeft());
        clickSaveAndExit();

    }
}




