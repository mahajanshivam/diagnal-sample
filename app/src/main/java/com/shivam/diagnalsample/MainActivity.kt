package com.shivam.diagnalsample

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.shivam.diagnalsample.adapter.DiagnalListingAdapter
import com.shivam.diagnalsample.databinding.ActivityMainBinding
import com.shivam.diagnalsample.model.ContentModel
import com.shivam.diagnalsample.viewmodel.DiagnalListingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var diagnalListingAdapter: DiagnalListingAdapter
//    private lateinit var viewModel: DiagnalListingViewModel
    private val viewModel: DiagnalListingViewModel by viewModels()
    private lateinit var gridLayoutManager: GridLayoutManager
    private var gridDataList: ArrayList<ContentModel?> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        viewModel = ViewModelProvider(this)[DiagnalListingViewModel::class.java]
        diagnalListingAdapter = DiagnalListingAdapter(::onGridItemClicked)

        initUI()
        subscribeUI()
    }

    private fun initUI() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        setSupportActionBar(binding.toolbar)

        gridLayoutManager =
            GridLayoutManager(this@MainActivity, resources.getInteger(R.integer.grid_column_count))
        binding.listingRecyclerView.run {
            layoutManager = gridLayoutManager
            adapter = diagnalListingAdapter
        }

        viewModel.fetchData(resources)
    }

    private fun subscribeUI() {
        viewModel.diagnalGridLiveData.observe(this@MainActivity) { page ->
            binding.toolbar.title = page?.title

            page?.contentItemModel?.content?.forEach {
                gridDataList.add(it)
            }
            diagnalListingAdapter.submitList(gridDataList)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        val component = ComponentName(this, MainActivity::class.java)
        val searchableInfo = searchManager.getSearchableInfo(component)
        searchView.setSearchableInfo(searchableInfo)

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
            if (query.length > 3) {
                // do filtering logic here
            }
            Log.d("shivam", "Search query was: $query")
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        gridLayoutManager.spanCount = resources.getInteger(R.integer.grid_column_count)
    }

    private fun onGridItemClicked() {
        // grid item click action here
    }


}