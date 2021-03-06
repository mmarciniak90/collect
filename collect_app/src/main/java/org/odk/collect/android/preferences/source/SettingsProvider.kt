package org.odk.collect.android.preferences.source

import android.content.Context
import androidx.preference.PreferenceManager
import org.odk.collect.android.preferences.keys.AdminKeys
import org.odk.collect.android.preferences.keys.GeneralKeys
import org.odk.collect.android.preferences.keys.MetaKeys.CURRENT_PROJECT_ID
import org.odk.collect.android.projects.ProjectImporter.Companion.DEMO_PROJECT_ID
import org.odk.collect.projects.NOT_SPECIFIED_UUID
import org.odk.collect.shared.Settings
import javax.inject.Singleton

@Singleton
class SettingsProvider(private val context: Context) {

    private val settings = mutableMapOf<String, Settings>()

    fun getMetaSettings() = settings.getOrPut(META_SETTINGS_NAME) {
        SharedPreferencesSettings(context.getSharedPreferences(META_SETTINGS_NAME, Context.MODE_PRIVATE))
    }

    @JvmOverloads
    fun getGeneralSettings(projectId: String = NOT_SPECIFIED_UUID): Settings {
        val settingsId = getSettingsId(GENERAL_SETTINGS_NAME, projectId)

        return settings.getOrPut(settingsId) {
            if (settingsId == GENERAL_SETTINGS_NAME) {
                SharedPreferencesSettings(PreferenceManager.getDefaultSharedPreferences(context), GeneralKeys.DEFAULTS)
            } else {
                SharedPreferencesSettings(context.getSharedPreferences(settingsId, Context.MODE_PRIVATE), GeneralKeys.DEFAULTS)
            }
        }
    }

    @JvmOverloads
    fun getAdminSettings(projectId: String = NOT_SPECIFIED_UUID): Settings {
        val settingsId = getSettingsId(ADMIN_SETTINGS_NAME, projectId)

        return settings.getOrPut(settingsId) {
            SharedPreferencesSettings(context.getSharedPreferences(settingsId, Context.MODE_PRIVATE), AdminKeys.getDefaults())
        }
    }

    private fun getSettingsId(settingName: String, projectId: String) = if (projectId == NOT_SPECIFIED_UUID) {
        settingName + (getMetaSettings().getString(CURRENT_PROJECT_ID) ?: DEMO_PROJECT_ID)
    } else {
        settingName + projectId
    }

    companion object {
        private const val META_SETTINGS_NAME = "meta"
        private const val GENERAL_SETTINGS_NAME = "general_prefs"
        private const val ADMIN_SETTINGS_NAME = "admin_prefs"
    }
}
