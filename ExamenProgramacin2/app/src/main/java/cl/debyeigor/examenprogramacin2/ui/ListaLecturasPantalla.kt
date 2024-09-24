package cl.debyeigor.examenprogramacin2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.debyeigor.examenprogramacin2.data.Lectura
import cl.debyeigor.examenprogramacin2.R

@Composable
fun ListaLecturasPantalla(
    viewModel: ListaLecturasViewModel,
    onNavigateToRegistro: () -> Unit
) {
    val lecturas by viewModel.lecturas.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToRegistro) {
                Text("+") // Botón para añadir nueva lectura
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Títulos de las columnas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.tipo), style = MaterialTheme.typography.titleMedium )
                Text(text = stringResource(id = R.string.medicion), style = MaterialTheme.typography.titleMedium)
                Text(text = stringResource(id = R.string.fecha), style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Lista de lecturas
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(lecturas) { lectura ->
                    LecturaItem(lectura)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            /* Boton para borrar todo descativadp
            Button(
                onClick = {
                    viewModel.borrarLecturas()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Borrar todas las lecturas")
            } */
        }
    }
}

@Composable
fun LecturaItem(lectura: Lectura) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícono según el tipo de lectura
        val icon = when (lectura.tipo) {
            "Agua" -> R.drawable.agua
            "Luz" -> R.drawable.luz
            "Gas" -> R.drawable.gas
            else -> R.drawable.agua
        }
        Image(
            painter = painterResource(id = icon),
            contentDescription = lectura.tipo,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Texto de la lectra
        Text(
            text = lectura.tipo,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = lectura.valor.toInt().toString(),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = lectura.fecha.toString(),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
