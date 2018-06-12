package com.example.alexe.dictionary.ui.main

import com.example.alexe.dictionary1.data.Word
import com.example.alexe.dictionary1.data.WordDadabase
import io.reactivex.Single
import javax.inject.Inject

class AddWordUseCase @Inject constructor(private val wordDadabase: WordDadabase) {
  fun buildUseCase(word: String): Single<Long>? {
    return Single.fromCallable{ wordDadabase.wordDao().insert(Word(word, false, "") )}
  }
}