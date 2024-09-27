package br.edu.up.flicknotes.ui.screens.util

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.flicknotes.ui.screens.Filmes.TelaUm

@Composable
fun TelaUmBottomBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    NavigationBar(containerColor = Color(0xFFBB86FC)) { // Cor roxa claro
        NavigationBarItem(
            selected = currentRoute == TelaUm.TELA_TITULOS_ROUTE,
            onClick = {
                if (currentRoute != TelaUm.TELA_TITULOS_ROUTE) {
                    navController.navigate(TelaUm.TELA_TITULOS_ROUTE) {
                        // Evitar múltiplas entradas na pilha de navegação
                        popUpTo(TelaUm.TELA_TITULOS_ROUTE) { inclusive = true }
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Titulos",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Titulos") }
        )
        NavigationBarItem(
            selected = currentRoute == TelaUm.TELA_DATAS_ROUTE,
            onClick = {
                if (currentRoute != TelaUm.TELA_DATAS_ROUTE) {
                    navController.navigate(TelaUm.TELA_DATAS_ROUTE) {
                        popUpTo(TelaUm.TELA_DATAS_ROUTE) { inclusive = true }
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Datas",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Datas") }
        )
        NavigationBarItem(
            selected = currentRoute == TelaUm.TELA_NOTAS_ROUTE,
            onClick = {
                if (currentRoute != TelaUm.TELA_NOTAS_ROUTE) {
                    navController.navigate(TelaUm.TELA_NOTAS_ROUTE) {
                        popUpTo(TelaUm.TELA_NOTAS_ROUTE) { inclusive = true }
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Anotações",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Anotações") }
        )
    }
}
