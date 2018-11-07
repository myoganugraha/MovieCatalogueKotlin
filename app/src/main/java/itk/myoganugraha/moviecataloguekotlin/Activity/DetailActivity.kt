package itk.myoganugraha.moviecataloguekotlin.Activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import itk.myoganugraha.moviecataloguekotlin.Model.Movie
import itk.myoganugraha.moviecataloguekotlin.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var mContext : Context
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mContext = this


        movie = intent.getParcelableExtra<Movie>("movieData")
        Log.d("Check data", movie.toString())

        initComponents()
    }

    private fun initComponents() {
        movieTitle_detail.setText(movie.title)
        movieRelease_detail.setText(movie.release_date)
        movieSynopsis_detail.setText(movie.overview)

        Glide.with(mContext)
                .load("http://image.tmdb.org/t/p/w780" + movie.backdrop_path)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(detailPoster)
    }
}
