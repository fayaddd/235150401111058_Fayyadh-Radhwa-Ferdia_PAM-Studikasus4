package com.filkom.mycv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.filkom.mycv2.ui.theme.MyCV2Theme 

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCV2Theme { 
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Screen.Login.route 
    ) {
        
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToDetail = { nim, nama ->
                    navController.navigate(Screen.Detail.createRoute(nim, nama)) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    
                    navController.navigate(Screen.Daftar.route)
                }
            )
        }

       
        composable(Screen.Daftar.route) {
            DaftarScreen( 
                onNavigateToDetail = { nim, nama ->
                    navController.navigate(Screen.Detail.createRoute(nim, nama)) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

      
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("nim") { type = NavType.StringType },
                navArgument("nama") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim") ?: "N/A"
            val nama = backStackEntry.arguments?.getString("nama") ?: "N/A"

            DetailScreen(
                nim = nim,
                nama = nama,
                onNavigateToRegister = {
                    
                    navController.navigate(Screen.Daftar.route)
                }
            )
        }
    }
}
