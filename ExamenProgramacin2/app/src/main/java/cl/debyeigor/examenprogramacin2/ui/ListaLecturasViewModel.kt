package cl.debyeigor.examenprogramacin2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import cl.debyeigor.examenprogramacin2.data.Lectura
import cl.debyeigor.examenprogramacin2.data.LecturaDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListaLecturasViewModel(private val lecturaDao: LecturaDao) : ViewModel() {


    // Flujo de estado para la lista de lecturas
    private val _lecturas = MutableStateFlow<List<Lectura>>(emptyList())
    val lecturas: StateFlow<List<Lectura>> = _lecturas

    init {
        obtenerLecturas()
    }

    // Método para obtener lecturas desde el Dao
    private fun obtenerLecturas() {
        viewModelScope.launch {
            _lecturas.value = lecturaDao.obtenerLecturas()
        }
    }

    class Factory(private val lecturaDao: LecturaDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListaLecturasViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ListaLecturasViewModel(lecturaDao) as T
            }
            throw IllegalArgumentException("Clase ViewModel desconocida")
        }
    }
    // Metodo para agregar una nueva lectura
    fun agregarLectura(nuevaLectura: Lectura) {
        viewModelScope.launch {
            lecturaDao.insertarLectura(nuevaLectura)
            obtenerLecturas() // Actualiza la lista
        }
    }
    //Metodo para borrar todos los datos de la tabla(Para pruebas, desactivado el boton que lo utiliza)
    fun borrarLecturas() {
        viewModelScope.launch {
            lecturaDao.borrarTodasLasLecturas()
            _lecturas.value = emptyList()
        }
    }
}