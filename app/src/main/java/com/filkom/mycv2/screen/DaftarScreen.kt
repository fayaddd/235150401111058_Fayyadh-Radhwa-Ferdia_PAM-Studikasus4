package com.filkom.mycv2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DaftarScreen(
    onNavigateToDetail: (nim: String, nama: String) -> Unit
) {
    var nim by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }

    val isFormValid = nim.isNotBlank() && nama.isNotBlank() && email.isNotBlank() && alamat.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Halaman Daftar ", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))

        // Input NIM
        OutlinedTextField(value = nim, onValueChange = { nim = it }, label = { Text("NIM") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        // Input Nama
        OutlinedTextField(value = nama, onValueChange = { nama = it }, label = { Text("Nama") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        // Input Email
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        // Input Alamat
        OutlinedTextField(value = alamat, onValueChange = { alamat = it }, label = { Text("Alamat") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Simpan ke Halaman Detail
        Button(
            onClick = {
                onNavigateToDetail(nim, nama)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid
        ) {
            Text("SIMPAN")
        }
    }
}