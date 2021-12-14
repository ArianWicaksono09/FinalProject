package com.arian.finalproject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arian.finalproject.data.model.MovieData
import com.arian.finalproject.databinding.ItemMovieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener
    private var listMovies = ArrayList<MovieData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    fun setDataMovies(movies: ArrayList<MovieData>) {
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    companion object {
        const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/original"
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemMovie: MovieData) {
            with(binding) {
                tvTitle.text = itemMovie.title
                Glide.with(itemView.context)
                    .load(IMAGE_PREFIX + itemMovie.posterPath)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgPoster)

                movieCard.setOnClickListener {
                    onItemClickListener.onItemClicked(itemMovie)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(movie: MovieData)
    }
}