package com.filkom.mycv2

sealed class Screen(val route: String) {
    // Halaman utama / awal
    data object Login : Screen("login_screen")

    // Halaman Registrasi/Daftar (Sesuai nama file DaftarScreen.kt)
    data object Daftar : Screen("daftar_screen")

    // Halaman Detail/Home, menerima argumen nim dan nama
    data object Detail : Screen("detail_screen/{nim}/{nama}") {
        // Fungsi untuk membuat path navigasi yang berisi argumen
        fun createRoute(nim: String, nama: String) = "detail_screen/$nim/$nama"
    }
}