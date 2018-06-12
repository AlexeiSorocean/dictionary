package com.example.alexe.dictionary1.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.alexe.dictionary1.data.WordDadabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule() {

  @Provides @Singleton internal fun provide(context: Context): WordDadabase {
   return Room.databaseBuilder(context, WordDadabase::class.java, "we-need-db")
        .build()
  }

}