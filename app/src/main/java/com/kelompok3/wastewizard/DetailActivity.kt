package com.kelompok3.wastewizard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kelompok3.wastewizard.Api.News

class DetailActivity : AppCompatActivity() {

    private lateinit var news: News
    private lateinit var titleTextView: TextView
    private lateinit var authorTextView: TextView
    private lateinit var contentTextView: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Inisialisasi view
        titleTextView = findViewById(R.id.text_TitleNews)
        authorTextView = findViewById(R.id.tv_reporter)
        contentTextView = findViewById(R.id.tv_kontenNews)
        imageView = findViewById(R.id.img_toolbar)

        // Mengambil data dari intent
        news = intent.getParcelableExtra("news")!!

        // Menampilkan data ke view
        titleTextView.text = news.title
        authorTextView.text = news.author
        contentTextView.text = news.content
        Glide.with(this)
            .load(news.imageUrl)
            .into(imageView)

    }
}