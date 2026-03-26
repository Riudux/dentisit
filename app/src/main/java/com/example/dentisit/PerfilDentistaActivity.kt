package com.example.dentisit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PerfilDentistaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil_dentista)

        // --- 1. CONFIGURACIÓN DE MÁRGENES (SISTEMA) ---
        val mainView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- 2. SALUDO PERSONALIZADO (como en la Home) ---
        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        // Podrías pasar el nombre de usuario también, o dejarlo fijo por ahora.
        tvGreeting.text = "¡Hola Richi!"

        // --- 3. REFERENCIAS A LOS COMPONENTES DE LA INTERFAZ ---
        // Identidad
        val tvNombreDentista = findViewById<TextView>(R.id.tvPerfilNombreDentista)
        val tvClinicaNombre = findViewById<TextView>(R.id.tvPerfilClinicaNombre)

        // Tarjeta de Citas
        val cardCitas = findViewById<CardView>(R.id.cardProximasCitas)
        val tvCitaDoctorEsp = findViewById<TextView>(R.id.tvCitaDoctorEspecialidad)
        val tvCitaFecha = findViewById<TextView>(R.id.tvCitaFecha)

        // --- 4. RECUPERAR LOS DATOS DEL DENTISTA DEL INTENT ---
        // Cuando se abre esta pantalla, debemos buscar qué dentista fue seleccionado.
        val intent = intent
        // Usamos el ID del dentista para luego buscarlo en Firebase (Paso futuro)
        val dentistaId = intent.getStringExtra("DENTISTA_ID") ?: "0"

        // Por ahora, como es estático, crearemos un dentista de ejemplo que coincida con tu imagen
        val dentistaEstaticoEjemplo = DentistaResult(
            "1",
            "DRA. CARLA RODRÍGUEZ",
            "Ortodoncia",
            "Blvr. Francisco Villa",
            5,
            null,
            "CLÍNICA DENTAL",
            "Dr. Juan Perez",
            "Odontología",
            "Hoy, 3:00 p.m",
            true // Para que se muestre la tarjeta de cita
        )

        // --- 5. LLENAR LA INTERFAZ CON LOS DATOS DEL DENTISTA ---
        tvNombreDentista.text = dentistaEstaticoEjemplo.nombre.uppercase() // Usamos mayúsculas como en tu imagen
        tvClinicaNombre.text = dentistaEstaticoEjemplo.clinicaNombre?.uppercase()

        // Configurar la tarjeta de citas
        if (dentistaEstaticoEjemplo.tieneCita) {
            // Si tiene cita, la mostramos y llenamos sus datos
            cardCitas.visibility = View.VISIBLE
            tvCitaDoctorEsp.text = "${dentistaEstaticoEjemplo.proximaCitaDoctor} - ${dentistaEstaticoEjemplo.proximaCitaEspecialidad}"
            tvCitaFecha.text = dentistaEstaticoEjemplo.proximaCitaFecha
        } else {
            // Si no tiene cita, ocultamos la tarjeta por completo
            cardCitas.visibility = View.GONE
        }
    }
}