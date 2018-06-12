package com.example.alexe.dictionary1

import android.app.Application
import com.example.alexe.dictionary1.di.ApplicationComponent
import com.example.alexe.dictionary1.di.ApplicationModule
import com.example.alexe.dictionary1.di.DaggerApplicationComponent
import com.facebook.stetho.Stetho

class DictionaryApplication : Application() {
  val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
    DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))

        .build()
  }

  override fun onCreate() {
    super.onCreate()

    appComponent.inject(this)
//
//    if (BuildConfig.DEBUG) {
//      LeakCanary.install(this)
//    }

    Stetho.initializeWithDefaults(this);
  }
}