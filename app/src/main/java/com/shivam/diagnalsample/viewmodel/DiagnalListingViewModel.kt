package com.shivam.diagnalsample.viewmodel

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.shivam.diagnalsample.R
import com.shivam.diagnalsample.model.PageContentModel
import com.shivam.diagnalsample.model.PageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class DiagnalListingViewModel() : ViewModel() {

//    @Inject
//    lateinit var gson:Gson

    private val _diagnalGridLiveData: MutableLiveData<PageContentModel?> = MutableLiveData()
    val diagnalGridLiveData: LiveData<PageContentModel?>
        get() = _diagnalGridLiveData

    fun fetchData(resources: Resources) {
        viewModelScope.launch {
            val jsonTextFromFile = resources.openRawResource(R.raw.content_listing_page_page1)
                .bufferedReader().use { it.readText() }

            val pageDataFromJson = Gson().fromJson(jsonTextFromFile, PageModel::class.java)
            Log.d("shivam", "fetchData: text = ${pageDataFromJson.page?.title}")
            _diagnalGridLiveData.postValue(pageDataFromJson.page)
        }
    }

}