package com.shivam.diagnalsample.viewmodel

import androidx.lifecycle.ViewModel
import com.shivam.diagnalsample.model.ContentItemModel

class DiagnalListingViewModel : ViewModel() {

    val listOfMovies: ArrayList<ContentItemModel> = arrayListOf()

    init {
        populateList()
    }

    private fun populateList() {
        listOfMovies.add(ContentItemModel(name = "Family Pot", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Family Pot", posterImage = "poster8.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster7.jpg"))
        listOfMovies.add(ContentItemModel(name = "The Birds", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "The birds", posterImage = "poster8.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster7.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Family Pot", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Family Pot", posterImage = "poster8.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster7.jpg"))
        listOfMovies.add(ContentItemModel(name = "The Birds", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "The birds", posterImage = "poster8.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster7.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Family Pot", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Family Pot", posterImage = "poster8.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster7.jpg"))
        listOfMovies.add(ContentItemModel(name = "The Birds", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "The birds", posterImage = "poster8.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster7.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Family Pot", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Family Pot", posterImage = "poster8.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster7.jpg"))
        listOfMovies.add(ContentItemModel(name = "The Birds", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "The birds", posterImage = "poster8.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster7.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Family Pot", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Family Pot", posterImage = "poster8.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster7.jpg"))
        listOfMovies.add(ContentItemModel(name = "The Birds", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster9.jpg"))
        listOfMovies.add(ContentItemModel(name = "The birds", posterImage = "poster8.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster7.jpg"))
        listOfMovies.add(ContentItemModel(name = "Rear window", posterImage = "poster9.jpg"))
    }

}