package com.suresh.latestandroidstructure.features.newscategories.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.suresh.latestandroidstructure.R
import com.suresh.latestandroidstructure.databinding.FragmentCategoriesBinding
import com.suresh.latestandroidstructure.features.newscategories.domain.model.NewsCategory
import com.suresh.latestandroidstructure.features.newscategories.presentation.ui.adapter.NewsCategoryAdapter

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBinding()
    }

    private fun setupBinding() {
        binding.apply {
            rvNewsCategories.adapter = NewsCategoryAdapter(
                listOf(
                    NewsCategory("Technology", R.drawable.ic_category_technology),
                    NewsCategory("Sports", R.drawable.ic_category_sports),
                    NewsCategory("Health", R.drawable.ic_category_health),
                    NewsCategory("Business", R.drawable.ic_category_business),
                    NewsCategory("Entertainment", R.drawable.ic_category_entertainment)
                )
            ) { category ->
                Toast.makeText(context, "Clicked: ${category.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}