package cl.debyeigor.examenprogramacin2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LecturaDao {

    @Insert
    suspend fun insertarLectura(lectura: Lectura)

    @Query("SELECT * FROM lecturas ORDER BY fecha DESC")
    suspend fun obtenerLecturas(): List<Lectura>

    @Query("DELETE FROM lecturas")
    suspend fun borrarTodasLasLecturas()

}