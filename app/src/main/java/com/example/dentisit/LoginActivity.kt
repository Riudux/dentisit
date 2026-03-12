package com.example.dentisit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Habilita el modo pantalla completa moderno
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // 2. Ajusta los márgenes para que el diseño no choque con la barra de estado
        val mainLayout = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main) // Asegúrate que tu XML tenga este ID en el layout padre
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 3. Referencias a los componentes
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // 4. Lógica de Login (Simulado para pruebas)
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString().trim()

            // Definimos nuestras credenciales de prueba
            val adminEmail = "admin@admin.admin"
            val adminPass = "admin1234"

            when {
                email.isEmpty() || pass.isEmpty() -> {
                    Toast.makeText(this, "Por favor completa los campos", Toast.LENGTH_SHORT).show()
                }
                email == adminEmail && pass == adminPass -> {
                    // ¡ÉXITO! Las credenciales coinciden
                    Toast.makeText(this, "¡Acceso concedido!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    // Error si los datos son incorrectos
                    Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}