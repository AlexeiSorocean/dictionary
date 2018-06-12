package com.example.alexe.dictionary1.di

import android.app.Application
import com.example.alexe.dictionary1.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        DatabaseModule::class
        )
)
interface ApplicationComponent {
    fun inject(application: Application)
    fun inject(activity: MainActivity)

}