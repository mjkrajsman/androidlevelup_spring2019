package pl.daftacademy.androidlevelup.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Movie::class, Studio::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movies(): MovieDao
    abstract fun studios(): StudioDao

    companion object {
        lateinit var INSTANCE: MovieDatabase

        fun initIfNeeded(context: Context){
            if (MovieDatabase.Companion::INSTANCE.isInitialized.not()) {
                INSTANCE = Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db")
                    .allowMainThreadQueries()
                    //.addCallback(AppDatabaseCallback())
                    .build()
            }
        }


    }

//    private class AppDatabaseCallback() : RoomDatabase.Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            populateDatabase(INSTANCE.studios())
//
//        }
////
////        override fun onOpen(db: SupportSQLiteDatabase) {
////            super.onOpen(db)
////            populateDatabase(INSTANCE.studios())
////        }
////
//        fun populateDatabase(studioDao: StudioDao) {
//            //TODO: Workaround of hardcoding needed, method below works only for system resources
//            //val studios = Resources.getSystem().getStringArray(R.array.studios)
//            val studioNames = arrayOf("19th Decade Fox", "Balt Fisney", "Sory Pictures", "Cosmic Studios")
//            for(studioName in studioNames) {
//                studioDao.add(Studio(0, studioName)) // java.lang.IllegalStateException: getDatabase called recursively
//            }
//        }
//
//    }
}
