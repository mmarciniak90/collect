package org.odk.collect.android.espressoutils.pages;

import androidx.test.rule.ActivityTestRule;

import org.odk.collect.android.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class UserAndDeviceIdentitySettingsPage extends Page<UserAndDeviceIdentitySettingsPage> {

    public UserAndDeviceIdentitySettingsPage(ActivityTestRule rule) {
        super(rule);
    }

    @Override
    public UserAndDeviceIdentitySettingsPage assertOnPage() {
        checkIsStringDisplayed(R.string.user_and_device_identity_title);
        return this;
    }

    public UserAndDeviceIdentitySettingsPage clickFormMetadata() {
        onView(withText(getInstrumentation().getTargetContext().getString(R.string.form_metadata_summary))).perform(click());
        return this;
    }

    public UserAndDeviceIdentitySettingsPage clickMetadataEmail() {
        onView(withText(getInstrumentation().getTargetContext().getString(R.string.email))).perform(click());
        return this;
    }

    public UserAndDeviceIdentitySettingsPage clickMetadataUsername() {
        onView(withText(getInstrumentation().getTargetContext().getString(R.string.username))).perform(click());
        return this;
    }
}
