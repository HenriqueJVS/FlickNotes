package br.edu.up.flicknotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.edu.up.flicknotes.ui.screens.Animes.TelaAnimes
import br.edu.up.flicknotes.ui.screens.Filmes.TelaFilmes
import br.edu.up.flicknotes.ui.screens.Series.TelaSeries
import kotlinx.coroutines.launch

const val TELA_FILMES_ROTA = "tela_filmes"
const val TELA_SERIES_ROTA = "tela_series"
const val TELA_ANIMES_ROTA = "tela_animes"

@Preview(device = Devices.PIXEL)
@Composable
fun FlickNotesNavDrawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navCtrlDrawer = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navCtrlDrawer, drawerState)
        },
        content = {
            NavHost(
                navController = navCtrlDrawer,
                startDestination = TELA_FILMES_ROTA
            ) {
                composable(TELA_FILMES_ROTA) {
                    TelaFilmes(drawerState)
                }
                composable(TELA_SERIES_ROTA) {
                    TelaSeries(drawerState)
                }
                composable(TELA_ANIMES_ROTA) {
                    TelaAnimes(drawerState)
                }
            }
        }
    )
}

@Composable
private fun DrawerContent(navController: NavController, drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()
    val currentBack by navController.currentBackStackEntryAsState()
    val rotaAtual = currentBack?.destination?.route ?: TELA_FILMES_ROTA

    Column(
        modifier = Modifier
            .width(300.dp)
            .background(Color.White)
            .padding(30.dp)
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(70.dp))

        // Botão Filmes
        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(rotaAtual == TELA_FILMES_ROTA)),
            onClick = {
                navController.navigate(TELA_FILMES_ROTA)
                coroutineScope.launch { drawerState.close() }
            }) {
            Icon(
                painter = painterResource(id = R.drawable.checklist),
                contentDescription = "Filmes",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(rotaAtual == TELA_FILMES_ROTA)
            )
            Text(text = "Filmes", fontSize = 30.sp, color = getColorTexto(rotaAtual == TELA_FILMES_ROTA))
        }

        // Botão Séries
        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(rotaAtual == TELA_SERIES_ROTA)),
            onClick = {
                navController.navigate(TELA_SERIES_ROTA)
                coroutineScope.launch { drawerState.close() }
            }) {
            Icon(
                painter = painterResource(id = R.drawable.checklist),
                contentDescription = "Séries",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(rotaAtual == TELA_SERIES_ROTA)
            )
            Text(text = "Séries", fontSize = 30.sp, color = getColorTexto(rotaAtual == TELA_SERIES_ROTA))
        }

        // Botão Animes
        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(rotaAtual == TELA_ANIMES_ROTA)),
            onClick = {
                navController.navigate(TELA_ANIMES_ROTA)
                coroutineScope.launch { drawerState.close() }
            }) {
            Icon(
                painter = painterResource(id = R.drawable.checklist),
                contentDescription = "Animes",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(rotaAtual == TELA_ANIMES_ROTA)
            )
            Text(text = "Animes", fontSize = 30.sp, color = getColorTexto(rotaAtual == TELA_ANIMES_ROTA))
        }
    }
}

fun getColorMenu(estaSelecionada: Boolean): Color {
    return if (estaSelecionada) Color.Yellow else Color.Transparent
}

fun getColorTexto(estaSelecionada: Boolean): Color {
    return if (estaSelecionada) Color.Black else Color.DarkGray
}
