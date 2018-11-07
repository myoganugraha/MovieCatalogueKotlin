package itk.myoganugraha.moviecataloguekotlin.Activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import itk.myoganugraha.moviecataloguekotlin.API.MovieRepository
import itk.myoganugraha.moviecataloguekotlin.Adapter.MovieAdapter
import itk.myoganugraha.moviecataloguekotlin.BuildConfig
import itk.myoganugraha.moviecataloguekotlin.Model.MovieResponse
import itk.myoganugraha.moviecataloguekotlin.R
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.fragment_now_playing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultActivity : AppCompatActivity() {

    private lateinit var mContext : Context

    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        mContext = this

        initComponent()
    }

    private fun initComponent() {
        query = intent.getStringExtra("query")
        query_searchResult.setText(query)

        var apiService = MovieRepository().getInitInstance()
        var call = apiService.searchMovie("02c64ad2e3c5f83aaee9677f967b4327", query)

        call.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                loadFailed()
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                var data = response.body()?.movie
                val adapter = MovieAdapter(data, mContext)

                searcResult_recycler_view.adapter = adapter
                searcResult_recycler_view?.layoutManager = LinearLayoutManager(mContext)
            }
        })
    }

    private fun loadFailed() {
        Toast.makeText(mContext, "Load Failed", Toast.LENGTH_SHORT).show()
    }
}
