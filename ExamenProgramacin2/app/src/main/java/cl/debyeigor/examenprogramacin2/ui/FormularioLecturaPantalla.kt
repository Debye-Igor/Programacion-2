package cl.debyeigor.examenprogramacin2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.debyeigor.examenprogramacin2.R
import cl.debyeigor.examenprogramacin2.data.Lectura
import java.time.LocalDate
import cl.debyeigor.examenprogramacin2.ui.theme.ExamenProgramacion2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioLecturaPantalla(
    onLecturaAgregada: (Lectura) -> Unit,
    onVolver: () -> Unit
) {
    var tipo by remember { mutableStateOf("Agua") }
    var valor by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf(LocalDate.now().toString()) }

    var valorError by remember { mutableStateOf(false) }

    // Mensaje de error al ingresar valor equivocado
    val valorErrorMessage = if (valorError) stringResource(id = R.string.valor_error) else ""

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id= R.string.titulo_registro )) })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(4.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), // scroll para ver toda la pantalla
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) { // Texto de ingreso del valor
            TextField(
                value = valor,
                onValueChange = {
                    valor = it
                    valorError = false
                },
                label = { Text(stringResource(id = R.string.valor_lectura)) },
                isError = valorError,
                modifier = Modifier.fillMaxWidth()
            )
            if (valorError) {
                Text(valorErrorMessage, color = MaterialTheme.colorScheme.error)
            }

            /*TextField(
                value = tipo,
                onValueChange = { tipo = it },
                label = { Text("Tipo de lectura (Agua, Luz, Gas)") },
                modifier = Modifier.fillMaxWidth()
            )
            */

            Spacer(modifier = Modifier.height(10.dp))

            //Ingreso de fecha
            TextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text(stringResource(id = R.string.formato_fecha)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))

            // RadioButtons para seleccionar el tipo de lectura
            Text(stringResource(id = R.string.tipo_medidor))
            Column(modifier = Modifier.fillMaxWidth()) {
               Row (verticalAlignment = Alignment.CenterVertically){
               RadioButton(
                    selected = tipo == "Agua",
                    onClick = { tipo = "Agua" }
                )
                Text(stringResource(id = R.string.agua))
               }

                Spacer(modifier = Modifier.width(6.dp))
                Row (verticalAlignment = Alignment.CenterVertically){
                    RadioButton(
                    selected = tipo == "Luz",
                    onClick = { tipo = "Luz" }
                )
                Text(stringResource(id = R.string.luz))
                }

                Spacer(modifier = Modifier.width(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = tipo == "Gas",
                    onClick = { tipo = "Gas" }
                )
                Text(stringResource(id = R.string.gas))
                }

                Spacer(modifier = Modifier.height(8.dp))

                //Boton para guardar
                Button(
                    onClick = {
                        val valorFloat = valor.toFloatOrNull()  // Intenta convertir a Float
                        if (valorFloat == null) {
                            valorError = true // Mostrar error si la conversion fala
                        } else {
                            valorError = false // No hay error, se agregav la lectura

                            // Crear una nueva lectura si el valor es valido
                            val nuevaLectura = Lectura(
                                tipo = tipo,
                                valor = valorFloat,  // Usar el valor convertido
                                fecha = LocalDate.parse(fecha)
                            )
                            onLecturaAgregada(nuevaLectura)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(id = R.string.guardar_lectura))
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Boton para cancelar y volver a la lista
                Button(
                    onClick = onVolver,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(id = R.string.cancelar))
                }
            }
        }
    }
}