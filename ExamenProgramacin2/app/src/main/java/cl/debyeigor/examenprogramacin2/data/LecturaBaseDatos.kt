package cl.debyeigor.examenprogramacin2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Lectura::class], version = 1)
@TypeConverters(LocalDateConverter::class)

abstract class LecturaBaseDatos : RoomDatabase() {

    abstract fun lecturaDao(): LecturaDao
}