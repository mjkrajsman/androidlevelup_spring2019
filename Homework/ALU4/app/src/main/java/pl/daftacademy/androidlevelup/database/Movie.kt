package pl.daftacademy.androidlevelup.database

import androidx.room.*

@Entity(
    foreignKeys = [
        ForeignKey(entity = Studio::class,
            parentColumns = ["id"],
            childColumns = ["studioId"],
            onUpdate = ForeignKey.CASCADE)
    ], indices = [Index(name="studioId_index", unique=false, value=["studioId"])]
)
class Movie(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val year: Int,
    val genres: String,
    @ColumnInfo(index = true) val studioId: Int
)
