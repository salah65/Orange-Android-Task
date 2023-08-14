package com.example.newsapp.presentation.newsList

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.newsapp.databinding.ActivityNewsListBinding
import com.example.newsapp.presentation.core.getQueryTextChangeStateFlow
import com.example.newsapp.presentation.newsDetails.ArticleDetails
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@AndroidEntryPoint
class NewsListActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsListBinding
    private val viewModel: NewsListViewModel by viewModels()
    private val adapter: NewListAdapter by lazy {
        NewListAdapter {
            val intent = Intent(this, ArticleDetails::class.java)
            intent.putExtra(ArticleDetails.ArticleDetails.ARTICLE_KEY, it)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.rvNewsList.adapter = adapter
        lifecycleScope.launch {
            binding.searchBar.getQueryTextChangeStateFlow()
                .debounce(1000)
                .distinctUntilChanged()
                .flowOn(Dispatchers.Default)
                .collect { query ->
                    viewModel.setSearchQuery(query.ifEmpty { "tesla" })
                }
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.getNewsFlow.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                val refreshState = loadStates.refresh
                val appendState = loadStates.append
                if (refreshState is LoadState.Loading) binding.loader.visibility =
                    View.VISIBLE
                else if (refreshState is LoadState.Error || appendState is LoadState.Error) {
                    binding.loader.visibility = View.GONE
                    val errorMessage = (refreshState as? LoadState.Error)?.error?.localizedMessage
                        ?: (appendState as? LoadState.Error)?.error?.localizedMessage
                        ?: "An error occurred"

                    Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Try Again") {
                            initObservers()
                        }.show()
                } else binding.loader.visibility = View.GONE


            }
        }
    }
}