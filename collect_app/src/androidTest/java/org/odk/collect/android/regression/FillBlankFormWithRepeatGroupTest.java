package org.odk.collect.android.regression;

import android.Manifest;

import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.odk.collect.android.espressoutils.FormEntry;
import org.odk.collect.android.espressoutils.MainMenu;
import org.odk.collect.android.espressoutils.Settings;
import org.odk.collect.android.support.CopyFormRule;
import org.odk.collect.android.support.ResetStateRule;

import static androidx.test.espresso.Espresso.pressBack;

//Issue NODK-247
@RunWith(AndroidJUnit4.class)
public class FillBlankFormWithRepeatGroupTest extends BaseRegressionTest {

    @Rule
    public RuleChain copyFormChain = RuleChain
            .outerRule(GrantPermissionRule.grant(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE)
            )
            .around(new ResetStateRule())
            .around(new CopyFormRule("TestRepeat.xml"))
            .around(new CopyFormRule("form1.xml"))
            .around(new CopyFormRule("form2.xml"))
            .around(new CopyFormRule("form3.xml"))
            .around(new CopyFormRule("form4.xml"))
            .around(new CopyFormRule("form5.xml"))
            .around(new CopyFormRule("form6.xml"))
            .around(new CopyFormRule("form7.xml"))
            .around(new CopyFormRule("form8.xml"))
            .around(new CopyFormRule("form9.xml"));


    @Test
    public void repeatGroupsNotAdded_ShouldNotDoubleLastMessage() {

        //TestCase1
        MainMenu.startBlankForm("TestRepeat");
        FormEntry.clickOptionsIcon();
        FormEntry.clickGeneralSettings();
        Settings.clickUserInterface();
        Settings.clickNavigation();
        Settings.clickUseSwipesAndButtons();
        pressBack();
        pressBack();
        FormEntry.clickForwardButton();
        FormEntry.doNotAddRepeatGroup();
        FormEntry.doNotAddRepeatGroup();
        FormEntry.clickForwardButton();
        FormEntry.clickSaveAndExit();
    }

    @Test
    public void nestedGroupsWithFieldListAppearance_ShouldBeAbleToFillTheForm() {

        //TestCase5
        MainMenu.startBlankForm("form1");
        FormEntry.swipeToNextQuestion();
        FormEntry.swipeToNextQuestion();
        FormEntry.clickSaveAndExit();
        MainMenu.startBlankForm("form2");
        FormEntry.swipeToNextQuestion();
        FormEntry.clickSaveAndExit();
        MainMenu.startBlankForm("form3");
        FormEntry.swipeToNextQuestion();
        FormEntry.clickSaveAndExit();
        MainMenu.startBlankForm("form4");
        FormEntry.putText("T1");
        FormEntry.swipeToNextQuestion();
        FormEntry.putText("T2");
        FormEntry.swipeToNextQuestion();
        FormEntry.putText("T3");
        FormEntry.swipeToNextQuestion();
        FormEntry.clickSaveAndExit();
        MainMenu.startBlankForm("form5");
        FormEntry.putText("T1");
        FormEntry.swipeToNextQuestion();
        FormEntry.putText("T2");
        FormEntry.swipeToNextQuestion();
        FormEntry.putText("T3");
        FormEntry.swipeToNextQuestion();
        FormEntry.clickSaveAndExit();
        MainMenu.startBlankForm("form6");
        FormEntry.putText("T1");
        FormEntry.swipeToNextQuestion();
        FormEntry.putText("T2");
        FormEntry.swipeToNextQuestion();
        FormEntry.putText("T3");
        FormEntry.swipeToNextQuestion();
        FormEntry.clickSaveAndExit();
        MainMenu.startBlankForm("form7");
        FormEntry.swipeToNextQuestion();
        FormEntry.clickSaveAndExit();
        MainMenu.startBlankForm("form8");
        FormEntry.swipeToNextQuestion();
        FormEntry.clickSaveAndExit();
        MainMenu.startBlankForm("form9");
        FormEntry.swipeToNextQuestion();
        FormEntry.clickSaveAndExit();
    }


}