package itk.myoganugraha.moviecataloguekotlin.Adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import itk.myoganugraha.moviecataloguekotlin.Activity.DetailActivity
import itk.myoganugraha.moviecataloguekotlin.Model.Movie
import itk.myoganugraha.moviecataloguekotlin.R
import kotlinx.android.synthetic.main.cardview.*
import kotlinx.android.synthetic.main.cardview.view.*
import java.text.SimpleDateFormat
import java.util.*

class MovieAdapter(var movieList: ArrayList<Movie>?, val mContext : Context) : RecyclerView.Adapter<MovieAdapter.CustomViewHolder>() {

//    val movieData = "movieData"

    fun clearAll(){
        movieList!!.clear()
        notifyDataSetChanged()
    }

    fun replaceAll(movieList: ArrayList<Movie>){
        this.movieList!!.clear()
        this.movieList = movieList
        notifyDataSetChanged()
    }

    fun updateData(movieList: List<Movie>){
        this.movieList!!.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomViewHolder {
       val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview, viewGroup, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    override fun onBindViewHolder(customViewHolder: CustomViewHolder, position: Int) {
        val movie = movieList!!.get(position)

        customViewHolder.tvTitle.setText(movie.title)
        customViewHolder.tv_synopsis.setText(movie.overview)
        customViewHolder.tvReleaseDate.setText(movie.release_date)

        Glide.with(mContext)
            .load("http://image.tmdb.org/t/p/w500"  + movie.poster_path)
            .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
            .into(customViewHolder.ivPoster)

        customViewHolder.ivPoster.setOnClickListener {
            Toast.makeText(mContext, movie.title, Toast.LENGTH_SHORT).show()
        }

        customViewHolder.cardView.setOnClickListener {
            Toast.makeText(mContext, movie.title, Toast.LENGTH_SHORT).show()

            val intent = Intent(customViewHolder.itemView.context, DetailActivity::class.java)
            intent.putExtra("movieData", movie)
            Log.d("data akan dikirim : ", movie.toString())
            customViewHolder.itemView.context.startActivity(intent)
        }


    }

    fun getStringFormatted(dateString: String): String{
        val format ="MMM dd, yyyy"
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())

        if(dateString.equals("")){
            return dateString
        }

        return simpleDateFormat.format(Date(dateString.replace("-", "/")))
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPoster = itemView.iv_poster
        val tvTitle = itemView.tv_title
        val tvReleaseDate = itemView.tv_release_date
        val tv_synopsis = itemView.tv_synopsis
        val cardView = itemView.cardViewList
    }
}