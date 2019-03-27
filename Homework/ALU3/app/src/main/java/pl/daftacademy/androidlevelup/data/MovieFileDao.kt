package pl.daftacademy.androidlevelup.data

import android.content.Context
import com.google.gson.Gson
import pl.daftacademy.androidlevelup.entity.Movie
import pl.daftacademy.androidlevelup.entity.MoviesPage
import java.io.InputStreamReader

class MovieFileDao(private val context: Context) : MovieDao {

    @Suppress("UNCHECKED_CAST")
    override fun getAllMovies() =
        InputStreamReader(context.assets.open("movies.json"))
            .let { Gson().fromJson(it, MoviesPage::class.java) }
            .movies

    override fun getMoviesByGenre(genre: String): List<Movie> = if(genre == "All")
             getAllMovies()
        else InputStreamReader(context.assets.open("movies.json"))
            .let { Gson().fromJson(it, MoviesPage::class.java) }
            .movies.filter { it.genres.contains(genre) }
}