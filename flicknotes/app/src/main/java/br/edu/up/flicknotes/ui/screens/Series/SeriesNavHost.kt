package br.edu.up.flicknotes.ui.screens.Series

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.flicknotes.ui.screens.Filmes.TelaDatass
import br.edu.up.flicknotes.ui.screens.Filmes.TelaTitulos

object TelaDois {
    val TELA_TITULOS_ROUTE = "t1a"
    val TELA_DATAS_ROUTE = "t1b"
    val TELA_NOTAS_ROUTE = "t1c"
}

@Composable
fun TelaSeries(drawerState: DrawerState) {

    val navCtrlBottonNav = rememberNavController()
    NavHost(
        navController = navCtrlBottonNav,
        startDestination = TelaDois.TELA_TITULOS_ROUTE
    ) {
        composable(TelaDois.TELA_TITULOS_ROUTE) {
            TelaTitulos(drawerState, navCtrlBottonNav)
        }
        composable(TelaDois.TELA_DATAS_ROUTE) {
            TelaDatasss(drawerState, navCtrlBottonNav) // Renomeie essa função para TelaDatas se necessário.
        }
        composable(TelaDois.TELA_NOTAS_ROUTE) {
            TelaNotas(drawerState, navCtrlBottonNav)
        }
    }
}
