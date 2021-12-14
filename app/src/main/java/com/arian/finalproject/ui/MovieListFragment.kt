package com.arian.finalproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arian.finalproject.R
import com.arian.finalproject.data.MovieRepository
import com.arian.finalproject.data.model.MovieData
import com.arian.finalproject.databinding.FragmentMovieListBinding
import com.arian.finalproject.viewmodel.ViewModelFactory

class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var rvMovie: RecyclerView
    private lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(layoutInflater, container, false)
        val repository = MovieRepository()
        viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        rvMovie = view.findViewById(R.id.rv_movie)
        setUpRv()

        viewModel.getMovie().observe(viewLifecycleOwner, {
            movieAdapter.setDataMovies(it)
        })

        onMovieSelected()
    }

    private fun setUpRv() {
        with(rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun onMovieSelected() {
        movieAdapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
            override fun onItemClicked(movie: MovieData) {
                val action = movie.let {
                    MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(it)
                }
                action.let {
                    findNavController().navigate(it)
                }
            }
        })
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}
