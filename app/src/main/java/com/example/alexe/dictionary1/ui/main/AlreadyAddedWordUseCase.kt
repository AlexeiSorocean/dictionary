package com.example.alexe.dictionary.ui.main

import com.example.alexe.dictionary1.data.Word
import com.example.alexe.dictionary1.data.WordDadabase
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlreadyAddedWordUseCase @Inject constructor(private val wordDadabase: WordDadabase) {
  fun buildUseCase(word: String): List<Word>? {
    return wordDadabase.wordDao().isIn(word)
  }
}