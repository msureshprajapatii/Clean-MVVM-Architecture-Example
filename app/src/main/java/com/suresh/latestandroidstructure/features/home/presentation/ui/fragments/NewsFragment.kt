package com.suresh.latestandroidstructure.features.home.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.suresh.latestandroidstructure.databinding.FragmentNewsBinding
import com.suresh.latestandroidstructure.features.home.domain.model.ArticleDomain
import com.suresh.latestandroidstructure.features.home.presentation.ui.adapter.NewsArticleAdapter
import com.suresh.latestandroidstructure.features.home.presentation.viewmodel.NewsViewModel
import com.suresh.latestandroidstructure.core.util.extension.hide
import com.suresh.latestandroidstructure.core.util.extension.show
import com.suresh.latestandroidstructure.features.home.presentation.ui.model.NewsUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsArticleAdapter: NewsArticleAdapter
    private val newsViewModel: NewsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        subscribeObserver()
    }

    private fun setRecyclerView() {
        newsArticleAdapter = NewsArticleAdapter(arrayListOf())
        binding.newsRecyclerView.adapter = newsArticleAdapter
    }

    private fun subscribeObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            newsViewModel.newsUiState.collectLatest {

                when(it){
                    is NewsUiState.Loading -> {
                        Log.d("subscribeObserv", "loading shimmer")
                        showShimmer()
                    }
                    is NewsUiState.Success -> {
                        hideShimmer()
                       val data: List<ArticleDomain> =  it.data.articles
                        newsArticleAdapter.updateData(data)
                        Log.d("subscribeObserv", "data = $data")
                    }
                    is NewsUiState.Error -> {
                        hideShimmer()
                        Log.d("subscribeObserv", "error = ${it.message}")
                    }
                    is NewsUiState.None -> {}
                }
            }
        }
    }

    private fun showShimmer(){
        binding.apply {
            shimmerFrameLayout.startShimmer()
            shimmerFrameLayout.show()
        }
    }

    private fun hideShimmer(){
        binding.apply {
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.hide()
        }
    }
}