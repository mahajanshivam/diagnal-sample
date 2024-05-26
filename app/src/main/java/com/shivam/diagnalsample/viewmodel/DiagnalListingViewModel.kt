package com.shivam.diagnalsample.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivam.diagnalsample.R
import com.shivam.diagnalsample.model.PageContentModel
import com.shivam.diagnalsample.repo.DiagnalListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class DiagnalListingViewModel() : ViewModel() {

    var searchQuery: String = ""
    private var repository = DiagnalListingRepository()

    var mIsLoading = false
    var mIsLastPage = false
    val PAGE_START = 1
    var currentPage = PAGE_START
    var LIST_ITEM_PER_PAGE = 20

    private val _diagnalGridLiveData: MutableLiveData<PageContentModel?> = MutableLiveData()
    val diagnalGridLiveData: LiveData<PageContentModel?>
        get() = _diagnalGridLiveData

    fun fetchData(resources: Resources) {
        viewModelScope.launch {
            mIsLoading = false
            val pageDataFromJson = repository.fetchData(resources, getCurrentPageId())
            _diagnalGridLiveData.postValue(pageDataFromJson.page)
            val content = pageDataFromJson.page?.contentItem?.content ?: return@launch
            if (content.size < LIST_ITEM_PER_PAGE) {
                mIsLastPage = true
            }
        }
    }

    private fun getCurrentPageId(): Int {
        return when (currentPage) {
            1 -> R.raw.content_listing_page_page1
            2 -> R.raw.content_listing_page_page2
            else -> R.raw.content_listing_page_page3
        }
    }

    fun resetPagination() {
        mIsLoading = false
        mIsLastPage = false
        currentPage = PAGE_START
    }
}