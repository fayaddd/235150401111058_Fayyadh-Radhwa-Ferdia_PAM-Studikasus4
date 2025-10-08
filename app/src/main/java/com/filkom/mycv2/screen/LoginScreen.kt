package com.filkom.mycv2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onNavigateToDetail: (nim: String, nama: String) -> Unit,
    onNavigateToRegister: () -> Unit
) {
    // --- BAGIAN INI YANG DIUBAH ---
    // Mengatur nilai awal (default) untuk NIM dan Nama
    var nimInput by remember { mutableStateOf("235150401111058") } // Ganti nilai default NIM
    var namaInput by remember { mutableStateOf("Fayyadh Radhwa Ferdia") } // Ganti nilai default Nama
    // -----------------------------

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Halaman Login ", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))

        // Input NIM
        OutlinedTextField(
            value = nimInput,
            onValueChange = { nimInput = it },
            label = { Text("NIM") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Input Nama
        OutlinedTextField(
            value = namaInput,
            onValueChange = { namaInput = it },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Login: ke Halaman Detail
        Button(
            onClick = {
                // Nilai yang dikirim adalah nilai yang ada di field input (NIM dan Nama Anda)
                onNavigateToDetail(nimInput, namaInput)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = nimInput.isNotBlank() && namaInput.isNotBlank()
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Daftar: ke Halaman Daftar
        OutlinedButton(
            onClick = onNavigateToRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Daftar")
        }
    }
}