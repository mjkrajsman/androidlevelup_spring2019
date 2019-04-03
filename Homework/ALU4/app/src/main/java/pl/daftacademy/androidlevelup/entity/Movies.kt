package pl.daftacademy.androidlevelup.entity

interface Movies {

    fun add(movies: Collection<Movie>): LongArray

    fun get(): List<Movie>
}
