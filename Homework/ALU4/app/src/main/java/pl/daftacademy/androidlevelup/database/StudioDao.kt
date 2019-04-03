package pl.daftacademy.androidlevelup.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by: Maciej Janusz Krajsman
 */
@Dao
interface StudioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(studio: Studio) : Long

    @Query("SELECT id FROM Studio WHERE name = :name")
    fun getIdByName(name: String) : Int

    @Query("SELECT name FROM Studio WHERE id = :id")
    fun getNameById(id: Int) : String
}