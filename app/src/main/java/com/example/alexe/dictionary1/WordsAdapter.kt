package com.example.alexe.dictionary1

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.alexe.dictionary1.R

class WordsAdapter(val items: List<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

  // Gets the number of animals in the list
  override fun getItemCount(): Int {
    return items.size
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_word, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder?.tvWord?.text = items.get(position)
  }

  fun removeAt(adapterPosition: Int) {
    if (items is ArrayList) {
      items.removeAt(adapterPosition)
    }
    notifyItemRemoved(adapterPosition)
  }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
  // Holds the TextView that will add each animal to
  val tvWord = view.findViewById<TextView>(R.id.tv_word);
}