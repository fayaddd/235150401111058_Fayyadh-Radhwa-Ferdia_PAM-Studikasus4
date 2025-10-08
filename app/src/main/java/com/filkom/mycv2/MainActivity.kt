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
import com.filkom.mycv2.ui.theme.MyCV2Theme // Asumsi nama Theme tetap ini

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCV2Theme { // Ganti dengan nama theme Anda
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    // NavController mengelola stack dan back stack dari composable (layar)
    val navController = rememberNavController()

    // NavHost mendefinisikan grafik navigasi
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route // Layar awal adalah Login
    ) {
        // --- 1. Login Screen ---
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToDetail = { nim, nama ->
                    // Tombol Login ke Halaman Detail
                    // popUpTo membersihkan back stack
                    navController.navigate(Screen.Detail.createRoute(nim, nama)) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    // Tombol Daftar ke Halaman Daftar (Screen.Daftar)
                    navController.navigate(Screen.Daftar.route)
                }
            )
        }

        // --- 2. Daftar Screen (Sesuai DaftarScreen.kt) ---
        composable(Screen.Daftar.route) {
            DaftarScreen( // Memanggil Composable DaftarScreen
                onNavigateToDetail = { nim, nama ->
                    // Tombol Simpan ke Halaman Detail
                    // popUpTo membersihkan back stack
                    navController.navigate(Screen.Detail.createRoute(nim, nama)) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // --- 3. Detail Screen ---
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("nim") { type = NavType.StringType },
                navArgument("nama") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim") ?: "N/A"
            val nama = backStackEntry.arguments?.getString("nama") ?: "N/A"

            DetailScreen( // Memanggil Composable DetailScreen
                nim = nim,
                nama = nama,
                onNavigateToRegister = {
                    // Tombol Daftar dari Halaman Detail ke Halaman Daftar (Screen.Daftar)
                    navController.navigate(Screen.Daftar.route)
                }
            )
        }
    }
}