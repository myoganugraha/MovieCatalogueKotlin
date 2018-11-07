package itk.myoganugraha.moviecataloguekotlin.API

import itk.myoganugraha.moviecataloguekotlin.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MovieRepository {
    val BASE_URL_API = "https://api.themoviedb.org/3/"
    val LANGUAGE = "en-US"
    val API_KEY = "02c64ad2e3c5f83aaee9677f967b4327"

    private lateinit var baseAPIService: BaseAPIService

    fun getMovieRepository(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getInitInstance(): BaseAPIService{
        return getMovieRepository().create(BaseAPIService::class.java)
    }
}