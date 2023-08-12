package com.example.newsapp.presentation.newsDetails

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityArticleDetailsBinding
import com.example.newsapp.domain.model.Article

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class ArticleDetails : AppCompatActivity(), ArticleDetailsHandler {
    lateinit var binding: ActivityArticleDetailsBinding

    object ArticleDetails {
        val ARTICLE_KEY = "article"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.article = getArticle()
        binding.handler=this
    }

    private fun getArticle() =
        intent.getSerializableExtra(ArticleDetails.ARTICLE_KEY, Article::class.java)

    override fun onBrowserClick(articleLink: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articleLink))
        startActivity(browserIntent)
    }

}