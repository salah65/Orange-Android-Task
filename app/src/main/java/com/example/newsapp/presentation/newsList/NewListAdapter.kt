package com.example.newsapp.presentation.newsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemNewsListRvBinding
import com.example.newsapp.domain.model.Article

class NewListAdapter(
    private var newsList: List<Article> = emptyList(),
    val onArticleClickListener: (article: Article) -> Unit
) :
    RecyclerView.Adapter<ViewHolder>() {


    fun updateData(newData: List<Article>) {
        this.newsList = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNewsListRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = newsList[position]
        holder.bind(article)
        holder.itemView.setOnClickListener { onArticleClickListener(article) }
    }
}

class ViewHolder(private val binding: ItemNewsListRvBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        binding.article = article
    }
}