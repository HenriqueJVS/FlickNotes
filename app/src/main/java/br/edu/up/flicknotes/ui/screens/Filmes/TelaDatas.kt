package br.edu.up.flicknotes.ui.screens.Filmes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.up.flicknotes.ui.screens.util.FlickNotesTopBar
import br.edu.up.flicknotes.ui.screens.util.TelaUmBottomBar

@Composable
fun TelaRotina(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController,
) {

    var dia by remember { mutableStateOf("") }
    var mes by remember { mutableStateOf("") }
    var ano by remember { mutableStateOf("") }

    // Inicializando a lista de registros com duas datas pré-cadastradas
    val registros = remember { mutableStateListOf(
        "Assistido em: 12/07/2023",
        "Assistido em: 25/08/2023"
    ) }

    Scaffold(
        topBar = {
            FlickNotesTopBar(drawerState)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),  // Adicionando padding ao redor da coluna
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Campos de Data
                Text(
                    text = "Preencha a data em que assistiu:",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = dia,
                        onValueChange = { dia = it },
                        label = { Text("Dia") },
                        modifier = Modifier
                            .weight(1f)  // Usando weight para distribuição igual
                            .padding(end = 8.dp)
                    )
                    TextField(
                        value = mes,
                        onValueChange = { mes = it },
                        label = { Text("Mês") },
                        modifier = Modifier
                            .weight(1f)  // Usando weight para distribuição igual
                            .padding(end = 8.dp)
                    )
                    TextField(
                        value = ano,
                        onValueChange = { ano = it },
                        label = { Text("Ano") },
                        modifier = Modifier.weight(1f)  // Usando weight para distribuição igual
                    )
                }

                // Botão para adicionar registro
                Button(
                    onClick = {
                        if (dia.isNotBlank() && mes.isNotBlank() && ano.isNotBlank()) {
                            val dataAssistida = "Assistido em: $dia/$mes/$ano"
                            registros.add(dataAssistida)
                            dia = ""
                            mes = ""
                            ano = ""
                        }
                    },
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar")
                    Text("Adicionar")
                }

                // Lista de registros
                Text(
                    text = "Registros de Datas:",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(registros) { registro ->
                        Text(
                            text = registro,
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
