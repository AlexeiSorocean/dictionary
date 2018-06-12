package com.example.alexe.dictionary1.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface WordDao {

  @Query("SELECT * FROM word")
  fun getAllPeople(): Flowable<List<Word>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(word: Word) : Long

  @Query("SELECT * FROM word WHERE wordText LIKE :queryString")
  fun isIn(queryString : String) : List<Word>



}