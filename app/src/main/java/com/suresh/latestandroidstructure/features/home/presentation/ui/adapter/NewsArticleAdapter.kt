package com.suresh.latestandroidstructure.features.home.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suresh.latestandroidstructure.R
import com.suresh.latestandroidstructure.databinding.ItemNewsBinding
import com.suresh.latestandroidstructure.features.home.domain.model.ArticleDomain

class NewsArticleAdapter(
    private val articles: MutableList<ArticleDomain>
) : RecyclerView.Adapter<NewsArticleAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        with(holder.binding) {
            titleTextView.text = article.title
            sourceTextView.text = article.source.name
            dateTextView.text = article.publishedAt

            Glide.with(imageView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.placeholder)
                .into(imageView)
        }
    }

    fun updateData(data: List<ArticleDomain> ){
        articles.clear()
        articles.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = articles.size
}