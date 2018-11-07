package itk.myoganugraha.moviecataloguekotlin.API

import itk.myoganugraha.moviecataloguekotlin.Model.Movie
import itk.myoganugraha.moviecataloguekotlin.Model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BaseAPIService {

    @GET("search/movie")
    abstract fun searchMovie(
            @Query("api_key") apiKey: String,
            @Query("query") query: String
    ): Call<MovieResponse>

    @GET("movie/popular")
    abstract fun getPopularMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Int
    ): Call<MovieResponse>


    @GET("movie/now_playing")
    abstract fun getNowPlayingMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Call<MovieResponse>

    @GET("movie/upcoming")
    abstract fun getUpcomingMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    abstract fun getMovie(
            @Path("movie_id") id: Int,
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Call<Movie>


}
