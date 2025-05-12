package com.suresh.latestandroidstructure.features.newscategories.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suresh.latestandroidstructure.databinding.ItemNewsCategoryBinding
import com.suresh.latestandroidstructure.features.newscategories.domain.model.NewsCategory

class NewsCategoryAdapter(
    private val items: List<NewsCategory>,
    private val onItemClick: (NewsCategory) -> Unit
) : RecyclerView.Adapter<NewsCategoryAdapter.NewsCategoryViewHolder>() {

    inner class NewsCategoryViewHolder(
        private val binding: ItemNewsCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsCategory) {
            binding.txtCategoryName.text = item.name
            binding.imgCategoryIcon.setImageResource(item.iconResId)

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCategoryViewHolder {
        val binding = ItemNewsCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsCategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}