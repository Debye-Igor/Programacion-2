package cl.debyeigor.examenprogramacin2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import cl.debyeigor.examenprogramacin2.data.LecturaBaseDatos
import cl.debyeigor.examenprogramacin2.ui.ListaLecturasPantalla
import cl.debyeigor.examenprogramacin2.ui.ListaLecturasViewModel
import cl.debyeigor.examenprogramacin2.ui.FormularioLecturaPantalla
import cl.debyeigor.examenprogramacin2.ui.theme.ExamenProgramacion2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crear instancia de la base de datos y DAO
        val database = Room.databaseBuilder(
            applicationContext,
            LecturaBaseDatos::class.java,
            "lectura_database"
        ).build()
        val lecturaDao = database.lecturaDao()

        // Crear el ViewModel utilizando el Factory
        val viewModelFactory = ListaLecturasViewModel.Factory(lecturaDao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ListaLecturasViewModel::class.java)

        setContent {
            ExamenProgramacion2Theme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "listaLecturas") {
                    composable("listaLecturas") {
                        ListaLecturasPantalla(
                            viewModel = viewModel,
                            onNavigateToRegistro = {
                                navController.navigate("formularioLectura")
                            }
                        )
                    }
                    composable("formularioLectura") {
                        FormularioLecturaPantalla(
                            onLecturaAgregada = { nuevaLectura ->
                                viewModel.agregarLectura(nuevaLectura)
                                navController.popBackStack() // Volver a la pantalla anterior
                            },
                            onVolver = {
                                navController.popBackStack() // Regresar sin guardar
                            }
                        )
                    }
                }
            }
        }
    }
}
