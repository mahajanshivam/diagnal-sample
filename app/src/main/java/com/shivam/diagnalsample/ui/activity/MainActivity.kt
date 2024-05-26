package com.shivam.diagnalsample.ui.activity

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.shivam.diagnalsample.R
import com.shivam.diagnalsample.adapter.DiagnalListingAdapter
import com.shivam.diagnalsample.databinding.ActivityMainBinding
import com.shivam.diagnalsample.model.ContentModel
import com.shivam.diagnalsample.paging.PaginationScrollListener
import com.shivam.diagnalsample.util.GridSpacingItemDecoration
import com.shivam.diagnalsample.viewmodel.DiagnalListingViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var diagnalListingAdapter: DiagnalListingAdapter

    //    private lateinit var viewModel: DiagnalListingViewModel
    private val viewModel: DiagnalListingViewModel by viewModels()
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var gridSpacingItemDecoration: GridSpacingItemDecoration
    private var mainDataList: ArrayList<ContentModel> = arrayListOf()
    private var listForRecycler: ArrayList<ContentModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        viewModel = ViewModelProvider(this)[DiagnalListingViewModel::class.java]
        diagnalListingAdapter = DiagnalListingAdapter(listForRecycler, ::onGridItemClicked)

        initUI()
        subscribeUI()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                return true

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun initUI() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        setSupportActionBar(binding.toolbar)

        gridLayoutManager =
            GridLayoutManager(this@MainActivity, resources.getInteger(R.integer.grid_column_count))

        binding.listingRecyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = diagnalListingAdapter
            gridSpacingItemDecoration = GridSpacingItemDecoration(resources)
            addItemDecoration(gridSpacingItemDecoration)
        }

        viewModel.fetchData(resources)

        binding.listingRecyclerView.setOnScrollListener(object :
            PaginationScrollListener(gridLayoutManager) {

            override val isLastPage: Boolean
                get() = viewModel.mIsLastPage
            override val isLoading: Boolean
                get() = viewModel.mIsLoading

            override fun loadMoreItems() {
                if (viewModel.searchQuery.isEmpty()) {
                    viewModel.mIsLoading = true
                    viewModel.currentPage++
                    viewModel.fetchData(resources)
                }
            }
        })
    }

    private fun subscribeUI() {
        viewModel.diagnalGridLiveData.observe(this@MainActivity) { page ->
            binding.toolbar.title = page?.title

            page?.contentItem?.content?.forEach {
                mainDataList.add(it ?: ContentModel("", ""))
                listForRecycler.add(it ?: ContentModel("", ""))
            }
            diagnalListingAdapter.notifyItemRangeInserted(
                (viewModel.currentPage - 1) * (viewModel.LIST_ITEM_PER_PAGE),
                (viewModel.currentPage * viewModel.LIST_ITEM_PER_PAGE) - 1
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        val component = ComponentName(this, MainActivity::class.java)
        val searchableInfo = searchManager.getSearchableInfo(component)
        searchView.setSearchableInfo(searchableInfo)

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search),
            object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    listForRecycler.clear()
                    mainDataList.clear()
                    diagnalListingAdapter.notifyDataSetChanged()
                    viewModel.resetPagination()
                    viewModel.fetchData(resources)
                    viewModel.searchQuery = ""
                    return true
                }

            })

        return true
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            query ?: return
            viewModel.searchQuery = query
            if (query.length >= 3) {
                // do filtering logic here
                viewModel.resetPagination()
                listForRecycler.clear()
                diagnalListingAdapter.notifyDataSetChanged()
                listForRecycler.addAll(getFilteredItemsFromMainList(query))
                diagnalListingAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    resources.getString(R.string.enter_more_characters_search),
                    Toast.LENGTH_SHORT
                ).show()
            }
            Log.d("shivam", "Search query was: $query")
        }
    }

    private fun getFilteredItemsFromMainList(query: String): ArrayList<ContentModel> {
        val filteredList = arrayListOf<ContentModel>()
        mainDataList.forEach {
            if (it.name.contains(query, ignoreCase = true)) {
                filteredList.add(it)
            }
        }
        return filteredList
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        gridLayoutManager.spanCount = resources.getInteger(R.integer.grid_column_count)
        binding.listingRecyclerView.removeItemDecoration(gridSpacingItemDecoration)
        gridSpacingItemDecoration = GridSpacingItemDecoration(resources)
        binding.listingRecyclerView.addItemDecoration(gridSpacingItemDecoration)
    }

    private fun onGridItemClicked() {
        // grid item click action here
    }
}