package com.vsple.salestax_android

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SalesTaxApplication : Application(), Application.ActivityLifecycleCallbacks {

    private val Context.dataStore by preferencesDataStore(name = "app_preferences")

    lateinit var dataStore: DataStore<Preferences>

    //   private set
    private var foregroundActivities = 0
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        dataStore = applicationContext.dataStore

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Prevent system font size changes from affecting the app
        newConfig.fontScale = .5f
    }

    private var isInBackground = false
    override fun onActivityCreated(p0: Activity, p1: Bundle?) {

    }

    override fun onActivityStarted(p0: Activity) {

    }

    override fun onActivityResumed(p0: Activity) {
        isInBackground = false
        foregroundActivities++
    }

    override fun onActivityPaused(p0: Activity) {
        isInBackground = true
        foregroundActivities--
    }

    override fun onActivityStopped(p0: Activity) {

    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

    }

    override fun onActivityDestroyed(p0: Activity) {

    }


}