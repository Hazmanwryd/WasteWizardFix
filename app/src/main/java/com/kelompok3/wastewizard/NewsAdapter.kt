package com.kelompok3.wastewizard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kelompok3.wastewizard.Api.News

class NewsAdapter(private val newsList: List<News>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
        val authorTextView: TextView = itemView.findViewById(R.id.tv_author)
        val descriptionTextView: TextView = itemView.findViewById(R.id.tv_desc)
        val imageView: ImageView = itemView.findViewById(R.id.iv_news)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val news = newsList[position]
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("news", news)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        // Inflate layout item berita
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        // Set data berita ke view di dalam ViewHolder
        val news = newsList[position]
        holder.titleTextView.text = news.title
        holder.authorTextView.text = news.author
        holder.descriptionTextView.text = news.description
        Glide.with(holder.itemView.context)
            .load(news.imageUrl)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}
