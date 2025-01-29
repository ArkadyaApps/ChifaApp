package com.arkadya.chifa.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import java.util.Locale

object LanguageUtils {
    private const val PREF_NAME = "app_prefs"
    private const val PREF_LANGUAGE = "app_language"
    private val SUPPORTED_LANGUAGES = listOf("en", "fr", "ar")

    fun createContextWithNewLocale(context: Context, languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        config.setLayoutDirection(locale)

        return context.createConfigurationContext(config)
    }

    fun setLocale(context: Context, languageCode: String) {
        if (languageCode !in SUPPORTED_LANGUAGES) {
            Log.e("LanguageUtils", "Unsupported language code: $languageCode")
            return
        }

        if (getCurrentLanguage(context) == languageCode) {
            Log.d("LanguageUtils", "Language already set to $languageCode")
            return
        }

        try {
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(PREF_LANGUAGE, languageCode)
                .apply()

            if (context is Activity) {
                context.recreate()
            }
        } catch (e: Exception) {
            Log.e("LanguageUtils", "Error setting locale", e)
        }
    }

    fun getCurrentLanguage(context: Context): String {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(PREF_LANGUAGE, "en") ?: "en"
    }

    fun getNextLanguage(currentLanguage: String): String {
        return when (currentLanguage) {
            "en" -> "fr"
            "fr" -> "ar"
            "ar" -> "en"
            else -> "en"
        }
    }

    fun isRTL(language: String): Boolean = language == "ar"
}