package itk.myoganugraha.moviecataloguekotlin.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("page")
    @Expose
    var page: Int = 0

    @SerializedName("total_results")
    @Expose
    var totalResults: Int = 0

    @SerializedName("results")
    @Expose
    var movie: ArrayList<Movie>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int = 0
}
