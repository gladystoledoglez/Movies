package com.personal.movies

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.personal.movies.data.Movie

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Movie) {
        val rating = itemView.findViewById<TextView>(R.id.tvRatings)
        val name = itemView.findViewById<TextView>(R.id.tvName)
        rating.text = item.points.toString()
        name.text = item.title
        itemView.setOnClickListener {
            //TODO make appear details view with information related to the item received
        }
    }
}