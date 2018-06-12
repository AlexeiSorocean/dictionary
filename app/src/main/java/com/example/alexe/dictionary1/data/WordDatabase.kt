package com.example.alexe.dictionary1.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Word::class), version = 2)
abstract class WordDadabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}