package com.example.alexe.dictionary1.ui.base

import android.support.v7.app.AppCompatActivity
import com.example.alexe.dictionary1.DictionaryApplication
import com.example.alexe.dictionary1.di.ApplicationComponent

open class BaseActivity : AppCompatActivity() {

  val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
    (this?.application as DictionaryApplication).appComponent
  }

}