package br.edu.up.flicknotes.ui.screens.Animes

import android.app.DatePickerDialog
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
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun TelaDatas(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController
) {
    // Estado para a data selecionada
    var selectedDate by remember { mutableStateOf("Data Selecionada:") }
    val context = LocalContext.current // Obtemos o contexto aqui

    // Obtendo a data atual
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

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
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Tela Datas",
                    Modifier.padding(30.dp),
                    fontSize = 40.sp
                )
                Text(
                    text = selectedDate,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
                Button(onClick = {
                    // Usamos o contexto aqui
                    DatePickerDialog(
                        context,
                        { _, selectedYear, selectedMonth, selectedDay ->
                            selectedDate = "Data Selecionada: $selectedDay/${selectedMonth + 1}/$selectedYear"
                        },
                        year,
                        month,
                        day
                    ).show()
                }) {
                    Text("Selecionar Data")
                }
            }
        },
        bottomBar = { TelaUmBottomBar(navCtrlBottomNav) }
    )
}
