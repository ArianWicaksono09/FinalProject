package com.arian.finalproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.arian.finalproject.databinding.FragmentMovieDetailBinding
import com.arian.finalproject.ui.MovieAdapter.Companion.IMAGE_PREFIX

class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = args.movie

        binding.apply {
            tvOverview.text = movie.overview
            tvRelase.text = movie.releaseDate
            tvTitleMovie.text = movie.title

            Glide.with(this@MovieDetailFragment)
                .load(IMAGE_PREFIX + movie.posterPath)
                .transform(RoundedCorners(20))
                .into(imgPosterMovie)

            btnShare.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, movie.title)
                    putExtra(Intent.EXTRA_TITLE, "Share your Movie")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }
}