package com.arian.finalproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arian.finalproject.data.MovieRepository
import com.arian.finalproject.data.model.MovieData

class MovieViewModel (private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovie(): LiveData<ArrayList<MovieData>> = movieRepository.getMovie()
}