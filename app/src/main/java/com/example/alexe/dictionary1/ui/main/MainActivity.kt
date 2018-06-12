package com.example.alexe.dictionary1.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.support.v7.widget.helper.ItemTouchHelper

import com.example.alexe.dictionary1.R
import com.example.alexe.dictionary1.SwipeToDeleteCallback
import com.example.alexe.dictionary1.WordsAdapter
import com.example.alexe.dictionary1.ui.base.BaseActivity

import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

  private lateinit var linearLayoutManager: LinearLayoutManager
  private lateinit var recyclerView: RecyclerView

  companion object {
    const val TAG = "Dictionary"
  }

  @Inject lateinit var presenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)


    appComponent.inject(this)
    setContentView(R.layout.activity_main)
    presenter.attachView(this)
    presenter.onCreate()

  }

  override fun showWords(words: ArrayList<String>) {
    recyclerView = findViewById(R.id.recyclerView);

    recyclerView.layoutManager = LinearLayoutManager(this)

    recyclerView.adapter = WordsAdapter(words, this)

    val swipeHandler = object : SwipeToDeleteCallback(this) {
      override fun onSwiped(
        viewHolder: ViewHolder,
        direction: Int
      ) {
        presenter.onSwap(viewHolder.adapterPosition)

      }
    }
    val itemTouchHelper = ItemTouchHelper(swipeHandler)
    itemTouchHelper.attachToRecyclerView(recyclerView)
  }

  override fun removeWord(position: Int) {
    val adapter = recyclerView.adapter as WordsAdapter
    adapter.removeAt(position)
  }

  override fun getTextFromFile(file_name: String): String {
    return application.assets.open(file_name)
        .bufferedReader()
        .use {
          it.readText()
        }
  }

}


