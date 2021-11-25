package com.edanuryildirim.itunes.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.edanuryildirim.itunes.R
import com.edanuryildirim.itunes.databinding.ActivityDetailBinding
import com.edanuryildirim.itunes.model.Articles
import com.edanuryildirim.itunes.model.NewsModel
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private var newsModels : ArrayList<NewsModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val selectedNews = intent.getSerializableExtra("data") as Articles


        //casting
        binding.artistName.text = selectedNews.artistName
        binding.collectionName.text = selectedNews.collectionName
        binding.kind.text = selectedNews.kind
        binding.releaseDate.text = selectedNews.releaseDate
        binding.collectionPrice.text = selectedNews.collectionPrice.toString()
        binding.trackCount.text = selectedNews.trackCount.toString()
        binding.country.text = selectedNews.country
        binding.currency.text = selectedNews.currency
        //val url2 = "${articleList[position].urlToImage}"
        val url2 = selectedNews.artworkUrl100
        Picasso.get().load(url2).into(binding.tunesImage)

        Share()

    }

    private  fun Share() {
        binding.shareButton.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_TEXT,"Artist Name : ${binding.artistName.text}" +
                    "\n" + "Country : ${binding.country.text}" + "\n" +
                    "\n" + "Release Date : ${binding.releaseDate.text}")
            intent.putExtra(Intent.EXTRA_SUBJECT,"ITUNES MAIL INFORMATION")
            startActivity(Intent.createChooser(intent,"Share text via"))
        })
    }

}