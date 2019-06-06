package org.odk.collect.android;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


/* Issue number NODK-244

List of required forms:
- form-with-invalid-submission-url
- All widgets
- 1560_DateData
- 1560_IntegerData
- 1560_IntegerData_instanceID
- Household
- predicate-warning.xml
- metadata2.xml
- g6Error.xml
- g6Error2.xml
- emptyGroupFieldList.xml
- emptyGroupFieldList2.xml
- selectTest.xml
*/

public class FillBlankFormsTest extends BaseFormTest {

    @Test
    public void invalidUrl() {

        //TestCase15
        clickFillBlankForm();
        onView(withId(R.id.snackbar_text)).check(matches(isDisplayed()));
        checkIsDisplayed("XForm parse error for form-with-invalid-submission-url.xml: submission url is missing or invalid.");

    }

    @Test
    public void subtextAddedOn() {

        //TestCase2
        clickFillBlankForm();
        onView(withIndex(withId(R.id.form_subtitle2), 0)).check(matches(isDisplayed()));

    }

    @Test
    public void exitDialog() {

        //TestCase6 , TestCase9
        clickFillBlankForm();
        clickOnText("All widgets");
        pressBack();
        checkIsDisplayed("Exit All widgets");
        checkIsDisplayed("Save Changes");
        checkIsDisplayed("Ignore Changes");
        clickOnText("Ignore Changes");
        checkIsDisplayed("Fill Blank Form");
        checkIsDisplayed("Get Blank Form");

    }

    @Test
    public void searchBar() {

        //TestCase12
        clickFillBlankForm();
        clickMenuFilter();
        searchInBar("Aaa");
        pressBack();
        pressBack();

    }

    @Test
    public void navigationButtons() {

        //TestCase16
        clickFillBlankForm();
        clickOnText("All widgets");
        onView(withText("Welcome to ODK Collect! This form showcases the different available question types (widgets).")).perform(swipeLeft());
        clickOptionsIcon();
        clickGeneralSettings();
        clickUserInterface();
        clickOnText("Navigation");
        clickOnText("Use swipes and buttons");
        pressBack();
        pressBack();
        onView(withId(R.id.form_forward_button)).check(matches(isDisplayed()));
        onView(withId(R.id.form_back_button)).check(matches(isDisplayed()));

    }

    @Test
    public void formsWithDate() {

        //TestCase17
        clickFillBlankForm();
        clickOnText("1560_DateData");
        onView(withText("Jan 01, 1900")).perform(swipeLeft());
        checkIsDisplayed("01/01/00");
        clickSaveAndExit();

        clickFillBlankForm();
        clickOnText("1560_IntegerData");
        onView(withText("5")).perform(swipeLeft());
        checkIsDisplayed("5");
        clickSaveAndExit();

        clickFillBlankForm();
        clickOnText("1560_IntegerData_instanceID");
        onView(withText("5")).perform(swipeLeft());
        checkIsDisplayed("1560_IntegerData_instanceID");
        clickSaveAndExit();

    }

    @Test
    public void household() {

        //TestCase20
        clickFillBlankForm();
        clickOnText("Household");

        onView(allOf(childAtPosition(childAtPosition(withClassName(is("android.widget.LinearLayout")), 0), 2), isDisplayed())).perform(replaceText("K050003"), closeSoftKeyboard());
        onView(allOf(childAtPosition(childAtPosition(withClassName(is("android.widget.LinearLayout")), 2), 2), isDisplayed())).perform(replaceText("06"), swipeLeft());
        checkIsDisplayed("Sorry, no record found for the following.\n" + "\n" + "Household ID: K050003\n" + "\n" + "Household member no: 06");
        onView(withText("Sorry, no record found for the following.\n" + "\n" + "Household ID: K050003\n" + "\n" + "Household member no: 06")).perform(swipeRight());
        onView(allOf(childAtPosition(childAtPosition(withClassName(is("android.widget.LinearLayout")), 2), 2), isDisplayed())).perform(replaceText("05"), swipeLeft());
        checkIsDisplayed("Household ID: K050003\n" + "\n" + "Household member no: 05\n" + "\n" + "Study site: California");
        onView(withText("Household ID: K050003\n" + "\n" + "Household member no: 05\n" + "\n" + "Study site: California")).perform(swipeLeft());
        clickSaveAndExit();

    }

    @Test
    public void predicateWarning() {

        //TestCase24
        clickFillBlankForm();
        clickOnText("predicate-warning");
        clickOnText("Apple");
        onView(withText("Apple")).perform(swipeLeft());
        clickOnText("Gala");
        onView(withText("Gala")).perform(swipeLeft());
        onView(withText("Variety (relative reference)")).perform(swipeLeft());
        clickOnText("Granny Smith");
        clickOnText("Gala");
        onView(withText("Gala")).perform(swipeLeft());
        onView(withText("Varieties (relative reference)")).perform(swipeLeft());
        clickSaveAndExit();

    }

    @Test
    public void metadataForm() {

        //TestCase27
        clickFillBlankForm();
        clickOnText("metadata2");
        clickSaveAndExit();
        clickFillBlankForm();
    }

    @Test
    public void emptyGroupFieldList() {

        //TestCase32
        clickFillBlankForm();
        clickOnText("g6Error");
        clickOnText("OK");
        onView(withText("text3")).perform(swipeLeft());
        clickSaveAndExit();
        clickFillBlankForm();
        clickOnText("g6Error2");
        onView(withText("text0")).perform(swipeLeft());
        clickOnText("OK");
        onView(withText("text0")).perform(swipeLeft());
        onView(withText("text3")).perform(swipeLeft());
        clickSaveAndExit();
        clickFillBlankForm();
        clickOnText("emptyGroupFieldList");
        clickSaveAndExit();
        clickFillBlankForm();
        clickOnText("emptyGroupFieldList2");
        onView(withText("text0")).perform(swipeLeft());
        clickSaveAndExit();

    }

    @Test
    public void SelectOneWidgetExternal() {

        //TestCase36
        clickFillBlankForm();
        clickOnText("selectTest");
        clickOnText("Poland");
        onView(withText("SelectOneWidget")).perform(swipeLeft());
        clickOnText("Rank items");
        clickOnText("OK");
        onView(withText("RankWidget")).perform(swipeLeft());
        clickOnText("Hungary");
        onView(withText("ItemsetWIdget")).perform(swipeLeft());
        clickOnText("Czech republic");
        onView(withText("SelectOneWidget")).perform(swipeLeft());
        onView(withText("SelectOneWidget: Poland\n" +
                "\n" +
                "RankWidget (first position): Poland\n" +
                "\n" +
                "ItemsetWIdget:\n" +
                "\n" +
                "SelectOneWidget external: Czech republic")).perform(swipeLeft());
        clickSaveAndExit();

    }
}
