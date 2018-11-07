package itk.myoganugraha.moviecataloguekotlin.Model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(var isAdult:Boolean, var backdrop_path: String?, var genres: List<Long>?, var id: Long, var original_language: String?,
                 var original_title: String?,  var overview: String?,  var release_date: String?, var poster_path: String?, var popularity: Double,
                 var title: String?, var isVideo: Boolean, var vote_average: Float, var vote_count: Long, var media_type: String?) : Parcelable