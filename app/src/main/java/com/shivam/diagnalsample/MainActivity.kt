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
import androidx.recyclerview.widget.RecyclerView
import com.shivam.diagnalsample.adapter.DiagnalListingAdapter
import com.shivam.diagnalsample.databinding.ActivityMainBinding
import com.shivam.diagnalsample.viewmodel.DiagnalListingViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var diagnalListingAdapter: DiagnalListingAdapter
    private lateinit var viewModel: DiagnalListingViewModel
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[DiagnalListingViewModel::class.java]
        diagnalListingAdapter = DiagnalListingAdapter(::onGridItemClicked)
        initUI()

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
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

    private fun initUI() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        setSupportActionBar(binding.toolbar)

        gridLayoutManager =
            GridLayoutManager(this@MainActivity, resources.getInteger(R.integer.grid_column_count))
        binding.listingRecyclerView.run {
            layoutManager = gridLayoutManager
            adapter = diagnalListingAdapter
        }
        diagnalListingAdapter.submitList(viewModel.listOfMovies)
        diagnalListingAdapter.notifyDataSetChanged()
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