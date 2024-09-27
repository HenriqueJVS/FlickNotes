package br.edu.up.flicknotes.ui.screens.Series

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.up.flicknotes.ui.screens.util.FlickNotesTopBar
import br.edu.up.flicknotes.ui.screens.util.TelaUmBottomBar

object TitulosRota {
    const val TELA_LISTAR_TITULOS_ROTA = "listar_titulos"
}

@Composable
fun TelaSeries(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController
) {
    val titulos = remember { mutableStateListOf<Titulo>() }

    // Populando a lista inicial com alguns títulos de séries
    LaunchedEffect(Unit) {
        titulos.add(Titulo(titulo = "Dark", descricao = "Uma série de viagem no tempo", nota = 0)) // Adicionando Dark
        titulos.add(Titulo(titulo = "Stranger Things", descricao = "Série de mistério e aventura", nota = 0))
    }

    Scaffold(
        topBar = { FlickNotesTopBar(drawerState) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                TelaListagemTitulos(titulos)
                Spacer(modifier = Modifier.weight(1f)) // Para empurrar o botão para o fundo
            }
        },
        floatingActionButton = { FloatButton { titulos.add(Titulo(titulo = "", descricao = "", nota = 0)) } },
        bottomBar = { TelaUmBottomBar(navCtrlBottomNav) }
    )
}

@Composable
private fun TelaListagemTitulos(titulos: MutableList<Titulo>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        items(titulos) { titulo ->
            Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${titulo.titulo} - Nota: ${titulo.nota}",
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                )
                // Campo de entrada para nota
                var notaText by remember { mutableStateOf(titulo.nota.toString()) }

                OutlinedTextField(
                    value = notaText,
                    onValueChange = { newValue ->
                        notaText = newValue
                        titulo.nota = newValue.toIntOrNull() ?: 0
                    },
                    label = { Text("Nota") },
                    modifier = Modifier.width(60.dp) // Define uma largura para o campo
                )
            }
        }
    }
}

data class Titulo(
    var titulo: String,
    var descricao: String,
    var nota: Int = 0,
    var concluido: Boolean = false,
    var id: Int? = null
)

@Composable
private fun FloatButton(onAddClick: () -> Unit) {
    FloatingActionButton(onClick = onAddClick) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Adicionar título"
        )
    }
}
