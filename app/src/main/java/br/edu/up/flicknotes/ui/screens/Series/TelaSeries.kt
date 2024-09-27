package br.edu.up.flicknotes.ui.screens.Series

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


object TelaUm {
    val TELA_TITULOS_ROUTE = "t1a"
    val TELA_DATAS_ROUTE = "t1b"
    val TELA_NOTAS_ROUTE = "t1c"
}

@Composable

fun TelaSeries(drawerState: DrawerState) {
    val navCtrlBottonNav = rememberNavController()
    NavHost(
        navController = navCtrlBottonNav,
        startDestination = TelaUm.TELA_TITULOS_ROUTE
    ) {
        composable(TelaUm.TELA_TITULOS_ROUTE) {
            TelaTitulos(drawerState, navCtrlBottonNav)
        }
        composable(TelaUm.TELA_DATAS_ROUTE) {
            TelaData(drawerState, navCtrlBottonNav)
        }
        composable(TelaUm.TELA_NOTAS_ROUTE) {
            TelaNotas(drawerState, navCtrlBottonNav)
        }
    }
}



