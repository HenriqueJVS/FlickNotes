package br.edu.up.flicknotes.ui.screens.Series

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.up.flicknotes.ui.screens.util.FlickNotesTopBar
import br.edu.up.flicknotes.ui.screens.util.TelaUmBottomBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

@Composable
fun TelaNotas(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController
) {
    var anotacao by remember { mutableStateOf("") }
    val anotacoes = remember { mutableStateListOf<String>() }

    // Anotações pré-cadastradas
    val anotacoesPreCadastradas = remember {
        listOf(
            "Stranger Things: Uma série sobre crianças enfrentando forças sobrenaturais.",
            "The Crown: Um olhar fascinante sobre a vida da Rainha Elizabeth II."
        )
    }

    Scaffold(
        topBar = {
            FlickNotesTopBar(drawerState)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top // Alinha ao topo
            ) {
                TextField(
                    value = anotacao,
                    onValueChange = { anotacao = it },
                    label = { Text("Digite sua anotação") },
                    modifier = Modifier.padding(16.dp)
                )

                FloatingActionButton(
                    onClick = {
                        if (anotacao.isNotBlank()) {
                            anotacoes.add(anotacao)
                            anotacao = "" // Limpa o campo de texto após salvar
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar")
                }

                // Exibir as anotações
                Text(
                    text = "Anotações",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Exibir anotações pré-cadastradas
                    items(anotacoesPreCadastradas) { anotacao ->
                        Text(
                            text = anotacao,
                            modifier = Modifier.padding(8.dp),
                            fontSize = 20.sp
                        )
                    }

                    // Exibir anotações adicionadas pelo usuário
                    items(anotacoes) { anotacao ->
                        Text(
                            text = anotacao,
                            modifier = Modifier.padding(8.dp),
                            fontSize = 20.sp
                        )
                    }
                }
            }
        },
        bottomBar = { TelaUmBottomBar(navCtrlBottomNav) }
    )
}
