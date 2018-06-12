package com.example.alexe.dictionary1.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Word(
  @PrimaryKey(autoGenerate = false)
  var wordText: String = "",
  var isKnown : Boolean = false,
  var sourse : String = "",
  var theme : String = "",
  var description: String = ""

)