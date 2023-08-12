package com.example.newsapp.presentation.newsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemNewsListRvBinding
import com.example.newsapp.domain.model.Article

class NewListAdapter(
    val onArticleClickListener: (article: Article) -> Unit
) : PagingDataAdapter<Article, ViewHolder>(ITEM_COMPARATOR) {
    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.headLine == newItem.headLine
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNewsListRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
        article?.let {
            holder.bind(article)
            holder.itemView.setOnClickListener { onArticleClickListener(article) }
        }
    }
}

class ViewHolder(private val binding: ItemNewsListRvBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        binding.article = article
    }
}