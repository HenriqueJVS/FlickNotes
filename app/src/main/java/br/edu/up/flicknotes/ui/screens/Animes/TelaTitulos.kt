package br.edu.up.flicknotes.ui.screens.Animes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.up.flicknotes.ui.screens.util.FlickNotesTopBar
import br.edu.up.flicknotes.ui.screens.util.TelaUmBottomBar

data class Titulo(
    var titulo: String,
    var descricao: String,
    var nota: String? = null,
    var concluido: Boolean = false,
    var id: Int? = null
)

@Composable
fun TelaTitulos(drawerState: DrawerState, navCtrlBottomNav: NavController) {
    val titulos = remember { mutableStateListOf(
        Titulo(titulo = "Attack on Titan", descricao = "Uma batalha pela sobrevivência da humanidade."),
        Titulo(titulo = "My Hero Academia", descricao = "Estudantes de uma escola de super-heróis.")
    ) }

    var novoTitulo by remember { mutableStateOf("") }
    var novaDescricao by remember { mutableStateOf("") }
    var snackbarVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { FlickNotesTopBar(drawerState) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Campo para o título
                TextField(
                    value = novoTitulo,
                    onValueChange = { novoTitulo = it },
                    label = { Text("Título") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                // Campo para a descrição
                TextField(
                    value = novaDescricao,
                    onValueChange = { novaDescricao = it },
                    label = { Text("Descrição") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                // Botão para adicionar título
                Button(
                    onClick = {
                        if (novoTitulo.isNotBlank() && novaDescricao.isNotBlank()) {
                            val titulo = Titulo(titulo = novoTitulo, descricao = novaDescricao)
                            titulos.add(titulo)
                            novoTitulo = ""
                            novaDescricao = ""
                            snackbarVisible = true // Exibe Snackbar
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar")
                    Text("Adicionar Título")
                }

                // Snackbar para feedback
                if (snackbarVisible) {
                    Snackbar(
                        action = {
                            TextButton(onClick = { snackbarVisible = false }) {
                                Text("Fechar")
                            }
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Título adicionado com sucesso!")
                    }
                }

                // Título da lista
                Text(
                    text = "Lista de Animes",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )

                // Lista de títulos
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(titulos) { titulo ->
                        var nota by remember { mutableStateOf("") } // Estado da nota

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = titulo.titulo,
                                    fontSize = 20.sp,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = titulo.descricao,
                                    fontSize = 16.sp,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            // Campo para adicionar nota
                            TextField(
                                value = nota,
                                onValueChange = { nota = it },
                                label = { Text("Nota") },
                                modifier = Modifier
                                    .width(60.dp)
                                    .padding(start = 16.dp)
                            )
                        }

                        // Armazena a nota no título
                        if (nota.isNotBlank()) {
                            titulo.nota = nota
                        }
                    }
                }
            }
        },
        bottomBar = { TelaUmBottomBar(navCtrlBottomNav) }
    )
}