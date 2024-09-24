package cl.debyeigor.examenprogramacin2

import android.app.Application
import androidx.room.Room
import cl.debyeigor.examenprogramacin2.data.LecturaBaseDatos

class Aplicacion : Application() {
    val db by lazy { Room.databaseBuilder(this, LecturaBaseDatos:: class.java, "lecturas.db").build() }
    val lecturaDao by lazy { db.lecturaDao() }
}