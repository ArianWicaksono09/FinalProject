package com.arian.finalproject.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arian.finalproject.BuildConfig
import com.arian.finalproject.data.model.MovieData
import com.arian.finalproject.data.model.MovieResponse
import com.arian.finalproject.data.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    fun getMovie(): LiveData<ArrayList<MovieData>> {
        val listMovie = MutableLiveData<ArrayList<MovieData>>()
        val api = NetworkConfig().api()
        api.getMovie(API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    listMovie.value = response.body()?.results as ArrayList<MovieData>
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        return listMovie
    }

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
        private const val TAG = "MovieRepository"
    }
}