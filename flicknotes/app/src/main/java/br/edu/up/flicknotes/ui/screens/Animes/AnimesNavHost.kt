package br.edu.up.flicknotes.ui.screens.Animes

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.flicknotes.ui.screens.Filmes.TelaTitulos

object TelaTres {
    val TELA_TITULOS_ROUTE = "t1a"
    val TELA_DATAS_ROUTE = "t1b"
    val TELA_NOTAS_ROUTE = "t1c"
}

@Composable
fun TelaAnimes(drawerState: DrawerState) {

    val navCtrlBottonNav = rememberNavController()
    NavHost(
        navController = navCtrlBottonNav,
        startDestination = TelaTres.TELA_TITULOS_ROUTE
    ) {
        composable(TelaTres.TELA_TITULOS_ROUTE) {
            TelaTitulos(drawerState, navCtrlBottonNav)
        }
        composable(TelaTres.TELA_DATAS_ROUTE) {
            TelaDatas(drawerState, navCtrlBottonNav) // Renomeie essa função para TelaDatas se necessário.
        }
        composable(TelaTres.TELA_NOTAS_ROUTE) {
            TelaNotas(drawerState, navCtrlBottonNav)
        }
    }
}