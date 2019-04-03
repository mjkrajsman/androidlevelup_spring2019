package pl.daftacademy.androidlevelup.database

import pl.daftacademy.androidlevelup.entity.Movie
import pl.daftacademy.androidlevelup.entity.Movies
import pl.daftacademy.androidlevelup.database.Movie as DbMovie

class RoomMovies(private val movieDao: MovieDao, private val studioDao: StudioDao) : Movies {

//    override fun add(movies: Collection<Movie>) = movieDao.add(movies.map(DbMovie.Companion::fromEntity))
//    override fun get(): List<Movie> = movieDao.get().map(DbMovie::toEntity)

    override fun add(movies: Collection<Movie>) = movieDao.add(movies.map {
        DbMovie(0, it.title, it.year, it.genres.joinToString(","), // id: 0 = not set
            //studioDao.getIdByName(it.studio.toString()) // android.database.sqlite.SQLiteConstraintException: FOREIGN KEY constraint failed (code 787 SQLITE_CONSTRAINT_FOREIGNKEY)
            studioDao.add(Studio(0, it.studio.toString())).toInt() // works, but tries to add a Studio every time
        )
    })

    override fun get(): List<Movie> = movieDao.get().map { movie: DbMovie ->
        Movie(movie.title, movie.year, movie.genres.split(","),
            studioDao.getNameById(movie.studioId))
    }
}
