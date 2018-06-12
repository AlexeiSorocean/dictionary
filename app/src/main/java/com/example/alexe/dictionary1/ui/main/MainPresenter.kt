package com.example.alexe.dictionary1.ui.main

import android.util.Log
import com.example.alexe.dictionary.ui.main.AddWordUseCase
import com.example.alexe.dictionary.ui.main.AlreadyAddedWordUseCase
import com.example.alexe.dictionary1.ui.base.BasePresenter
import com.example.alexe.dictionary1.ui.base.BaseView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.rxkotlin.toObservable

class MainPresenter @Inject constructor(
  private val addwordUseCase: AddWordUseCase,
  private val alreadyAddedWordUseCase: AlreadyAddedWordUseCase
) : BasePresenter<MainView>() {

  lateinit var mutableSet: MutableSet<String>

  companion object {
    @JvmField public final val TAG = "MainPresenter"
  }

  fun onCreate() {
    val file_name = "three_bilbords.srt"
    //var str = view?.getTextFromFile(file_name)
    var str = "You me he she we"
    str = str?.let { deleteNonLetter(it) }
    var words = str?.let { getWords(it) }
    val s = LinkedHashSet(words)
    mutableSet = s.toMutableSet()
    createListForVisualization();
    addToDatabase("You")
    addToDatabase("me")
  }

  private fun createListForVisualization() {
    mutableSet.toObservable()
        .map { result -> alreadyAddedWordUseCase.buildUseCase(result) }
        ?.subscribeOn(Schedulers.io())
        ?.observeOn(AndroidSchedulers.mainThread())
        ?.subscribe(
            { result ->
              if (result?.size ?: 0 > 0) {
                mutableSet.remove(result?.get(0)?.wordText)
                Log.d(TAG, result.toString())
              }
              Log.d(TAG, result.toString())
            }, { error ->
          Log.d(TAG, "error")
        }, {
          view?.showWords(mutableSet.toList() as ArrayList<String>)
        }
        )
  }

  private fun addToDatabase(string: String): Single<Long>? {
    return addwordUseCase.buildUseCase(string)
        ?.subscribeOn(Schedulers.io())
        ?.observeOn(AndroidSchedulers.mainThread())
  }

  private fun deleteNonLetter(str: String): String {
    var result = "";
    for (char in str) {
      if (char.isLetter() || char == ' ') {
        result += char;
      }
    }
    return result;
  }

  private fun getWords(str: String): List<String> {
    val separate1 = str.split("\\s+".toRegex())
    return separate1
  }

  fun onSwap(position: Int) {
    addToDatabase(mutableSet.elementAt(position))
        ?.subscribe({ result: Long ->
          if (result != 1L) {
            view?.removeWord(position)
          }
        },
            { error: Throwable -> })
  }

}

interface MainView : BaseView {
  fun getTextFromFile(file_name: String): String
  fun showWords(words: ArrayList<String>)
  fun removeWord(position: Int)
}


