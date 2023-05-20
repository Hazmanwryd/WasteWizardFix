package com.kelompok3.wastewizard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kelompok3.wastewizard.Api.Article
import com.kelompok3.wastewizard.Api.News
import com.kelompok3.wastewizard.Api.NewsApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsFragment : Fragment() {
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsApiService: NewsApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            val view = inflater.inflate(R.layout.fragment_news, container, false)

            recyclerView = view.findViewById(R.id.daftarBerita)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            // Inisialisasi Retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            newsApiService = retrofit.create(NewsApiService::class.java)

            // Panggil API untuk mendapatkan data berita
            getNews()

            return view
        }



        private fun getNews() {
            val apiKey = "36c1ef8196504257863efce3b0621692" // Ganti dengan API key Anda
            val country = "us" // Ganti dengan kode negara yang sesuai

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = newsApiService.getTopHeadlines(country, apiKey)
                    if (response.isSuccessful) {
                        val newsList = response.body()?.articles?.map { article : Article ->
                            News(article.title, article.author, article.description, article.content, article.imageUrl)
                        }
                        withContext(Dispatchers.Main) {
                            // Inisialisasi adapter dan set ke RecyclerView
                            newsAdapter = newsList?.let { NewsAdapter(newsList) } ?: NewsAdapter(emptyList())
                            recyclerView.adapter = newsAdapter
                        }
                    } else {
                        Log.e("NewsActivity", "API request failed: ${response.errorBody()}")
                    }
                } catch (e: Exception) {
                    Log.e("NewsActivity", "API request error: ${e.message}")
                }
            }
            // Inflate the layout for this fragment
        }
    }
