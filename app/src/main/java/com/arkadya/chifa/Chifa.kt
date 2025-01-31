package com.arkadya.chifa

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.arkadya.chifa.utils.LanguageUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChifaApplication : Application() {
    override fun attachBaseContext(base: Context) {
        val currentLanguage = LanguageUtils.getCurrentLanguage(base)
        super.attachBaseContext(LanguageUtils.createContextWithNewLocale(base, currentLanguage))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val currentLanguage = LanguageUtils.getCurrentLanguage(this)
        LanguageUtils.setLocale(this, currentLanguage)
    }
}