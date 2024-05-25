package com.shivam.diagnalsample.repo

import android.content.res.Resources
import com.google.gson.Gson
import com.shivam.diagnalsample.model.PageModel
import javax.inject.Singleton

@Singleton
class DiagnalListingRepository {

    fun fetchData(resources: Resources, currentPageId: Int): PageModel {
        val jsonTextFromFile = resources.openRawResource(currentPageId)
            .bufferedReader().use { it.readText() }
        val pageDataFromJson = Gson().fromJson(jsonTextFromFile, PageModel::class.java)
        return pageDataFromJson
    }
}