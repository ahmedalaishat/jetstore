package com.alaishat.ahmed.jetstore.initializer

import android.content.Context
import androidx.startup.Initializer
import com.alaishat.ahmed.jetstore.BuildConfig
import timber.log.Timber

/**
 * Created by Ahmed Al-Aishat on Sep/29/2022.
 * JetStore Project.
 */
class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("TimberInitializer is initialized.")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
