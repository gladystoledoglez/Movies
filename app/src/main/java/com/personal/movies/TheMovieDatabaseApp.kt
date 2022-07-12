package com.personal.movies

import android.app.Application
import com.personal.movies.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TheMovieDatabaseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TheMovieDatabaseApp)
        }
        MainModule.init()
    }
}