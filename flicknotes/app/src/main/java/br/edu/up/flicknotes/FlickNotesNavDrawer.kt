package br.edu.up.flicknotes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
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

object FlickNotesRotas {
    const val TELA_FILMES_ROTA = "tela_um"
    const val TELA_SERIES_ROTA = "tela_dois"
    const val TELA_ANIMES_ROTA = "tela_tres"
}

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
                startDestination = FlickNotesRotas.TELA_FILMES_ROTA
            ) {
                composable(FlickNotesRotas.TELA_FILMES_ROTA) {
                    TelaFilmes(drawerState)
                }
                composable(FlickNotesRotas.TELA_SERIES_ROTA) {
                    TelaSeries(drawerState, navCtrlDrawer)
                }
                composable(FlickNotesRotas.TELA_ANIMES_ROTA) {
                    TelaAnimes(drawerState, navCtrlDrawer)
                }
            }
        }
    )
}

@Composable
private fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState
) {
    val coroutineScope = rememberCoroutineScope()
    val currentBack by navController.currentBackStackEntryAsState()
    val rotaAtual = currentBack?.destination?.route ?: FlickNotesRotas.TELA_FILMES_ROTA

    val ehRotaUm = rotaAtual == FlickNotesRotas.TELA_FILMES_ROTA
    val ehRotaDois = rotaAtual == FlickNotesRotas.TELA_SERIES_ROTA
    val ehRotaTres = rotaAtual == FlickNotesRotas.TELA_ANIMES_ROTA

    Column(
        modifier = Modifier
            .width(300.dp)
            .background(Color.White)
            .padding(30.dp)
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(70.dp))

        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(ehRotaUm)),
            onClick = {
                navController.navigate(FlickNotesRotas.TELA_FILMES_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.checklist),
                contentDescription = "Filmes",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaUm)
            )
            Text(text = "Filmes", fontSize = 30.sp, color = getColorTexto(ehRotaUm))
        }

        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(ehRotaDois)),
            onClick = {
                Log.d("Navigation", "Navigating to Series")
                navController.navigate(FlickNotesRotas.TELA_SERIES_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.checklist),
                contentDescription = "Séries",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaDois)
            )
            Text(text = "Séries", fontSize = 30.sp, color = getColorTexto(ehRotaDois))
        }

        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(ehRotaTres)),
            onClick = {
                navController.navigate(FlickNotesRotas.TELA_ANIMES_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.checklist),
                contentDescription = "Animes",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaTres)
            )
            Text(text = "Animes", fontSize = 30.sp, color = getColorTexto(ehRotaTres))
        }
    }
}

fun getColorMenu(estaSelecionada: Boolean): Color {
    return if (estaSelecionada) {
        Color.Yellow
    } else {
        Color.Transparent
    }
}

fun getColorTexto(estaSelecionada: Boolean): Color {
    return if (estaSelecionada) {
        Color.Black
    } else {
        Color.DarkGray
    }
}
