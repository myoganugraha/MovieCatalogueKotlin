package itk.myoganugraha.moviecataloguekotlin.Fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import itk.myoganugraha.moviecataloguekotlin.API.BaseAPIService
import itk.myoganugraha.moviecataloguekotlin.API.MovieRepository
import itk.myoganugraha.moviecataloguekotlin.Adapter.MovieAdapter
import itk.myoganugraha.moviecataloguekotlin.BuildConfig
import itk.myoganugraha.moviecataloguekotlin.Model.MovieResponse

import itk.myoganugraha.moviecataloguekotlin.R
import kotlinx.android.synthetic.main.fragment_now_playing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NowPlayingFragment : Fragment() {

    private lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_now_playing, container, false)

        mContext = view.context

        setupList()
        return view
    }

    private fun setupList() {
        var apiService = MovieRepository().getInitInstance()
        var call = apiService.getNowPlayingMovies("02c64ad2e3c5f83aaee9677f967b4327", "en-US")

        call.enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                loadFailed()
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                var data = response.body()?.movie
                val adapter = MovieAdapter(data, mContext)

                recyclerView_fragment_nowPlaying.adapter = adapter
                recyclerView_fragment_nowPlaying?.layoutManager = LinearLayoutManager(mContext)
            }
        })
    }

    private fun loadFailed() {
        Toast.makeText(mContext, "Load Failed", Toast.LENGTH_SHORT).show()
    }
}
