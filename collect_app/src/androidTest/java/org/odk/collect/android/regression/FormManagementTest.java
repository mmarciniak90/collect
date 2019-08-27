package org.odk.collect.android.regression;

import android.Manifest;

import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.odk.collect.android.R;
import org.odk.collect.android.espressoutils.FormEntry;
import org.odk.collect.android.espressoutils.MainMenu;
import org.odk.collect.android.espressoutils.Settings;
import org.odk.collect.android.support.CopyFormRule;
import org.odk.collect.android.support.ResetStateRule;

import static androidx.test.espresso.Espresso.pressBack;

//Issue NODK-237
@RunWith(AndroidJUnit4.class)
public class FormManagementTest extends  BaseRegressionTest {

    @Rule
    public RuleChain copyFormChain = RuleChain
            .outerRule(GrantPermissionRule.grant(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE)
            )
            .around(new ResetStateRule())
            .around(new CopyFormRule("OnePageFormValid2.xml"))
            .around(new CopyFormRule("hints_textq.xml"));

    @SuppressWarnings("PMD.AvoidCallingFinalize")
    @Test
    public void validationUponSwipe_ShouldDisplay() {
        //TestCase7,8
        MainMenu.startBlankForm("OnePageFormValid");
        FormEntry.putText("Bla");
        FormEntry.swipeToNextQuestion();
        FormEntry.checkIsToastWithMessageDisplayed("Response length must be between 5 and 15", main);
        MainMenu.clickOnMenu();
        MainMenu.clickGeneralSettings();
        Settings.openFormManagement();
        Settings.openConstraintProcessing();
        FormEntry.clickOnString(R.string.constraint_behavior_on_finalize);
        pressBack();
        pressBack();
        FormEntry.swipeToNextQuestion();
        FormEntry.clickSaveAndExit();
        FormEntry.checkIsToastWithMessageDisplayed("Response length must be between 5 and 15", main);
    }

    @Test
    public void guidanceForQuestion_ShouldDisplayAlways() {
        //TestCase10
        MainMenu.clickOnMenu();
        MainMenu.clickGeneralSettings();
        Settings.openFormManagement();
        Settings.openShowGuidanceForQuestions();
        Settings.clickOnString(R.string.guidance_yes);
        pressBack();
        pressBack();
        MainMenu.startBlankForm("hints textq");
        FormEntry.checkIsTextDisplayed("1 very very very very very very very very very very long text");
        FormEntry.swipeToNextQuestion();
        FormEntry.clickSaveAndExit();
    }

    @Test
    public void guidanceForQuestion_ShouldBeCollapsed() {
        //TestCase11
        MainMenu.clickOnMenu();
        MainMenu.clickGeneralSettings();
        Settings.openFormManagement();
        Settings.openShowGuidanceForQuestions();
        Settings.clickOnString(R.string.guidance_yes_collapsed);
        pressBack();
        pressBack();
        MainMenu.startBlankForm("hints textq");
        FormEntry.checkIsIdDisplayed(R.id.help_icon);
        FormEntry.clickOnText("Hint 1");
        FormEntry.checkIsTextDisplayed("1 very very very very very very very very very very long text");
        FormEntry.swipeToNextQuestion();
        FormEntry.clickSaveAndExit();
    }

}
