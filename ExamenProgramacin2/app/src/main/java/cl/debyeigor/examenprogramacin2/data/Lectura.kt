package cl.debyeigor.examenprogramacin2.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "lecturas")
data class Lectura (
    @PrimaryKey(autoGenerate = true) val id:Long? = null,
    var tipo: String,
    var valor: Float,
    var fecha: LocalDate
)

