package br.edu.up.flicknotes.ui.screens.Series

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.up.flicknotes.ui.screens.util.FlickNotesTopBar
import br.edu.up.flicknotes.ui.screens.util.TelaUmBottomBar

@Composable
fun TelaNotas(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController
) {
    // Estado para armazenar as anotações
    var notaText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            FlickNotesTopBar(drawerState)
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Minhas Anotações",
                    modifier = Modifier.padding(30.dp),
                    fontSize = 40.sp
                )

                // Campo de entrada para anotações
                OutlinedTextField(
                    value = notaText,
                    onValueChange = { notaText = it },
                    label = { Text("Digite suas anotações aqui") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    maxLines = 10 // Permite múltiplas linhas
                )
            }
        },
        bottomBar = { TelaUmBottomBar(navCtrlBottomNav) }
    )
}
