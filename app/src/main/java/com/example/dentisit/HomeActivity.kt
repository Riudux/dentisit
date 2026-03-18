package com.example.dentisit

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.location.LocationManager
import android.webkit.WebView
import android.content.Context
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_home)

        // --- CONFIGURACIÓN DEL MAPA ---
        val webViewMapa = findViewById<WebView>(R.id.webViewMapa)
        webViewMapa.settings.javaScriptEnabled = true
        webViewMapa.webViewClient = WebViewClient()

        // 1. Obtener el gestor de ubicación de forma segura
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val location = try {
            // Intentamos obtener la última ubicación conocida por red o GPS
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                ?: locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        } catch (e: SecurityException) {
            null
        }

        // 2. Coordenadas dinámicas
        val lat = location?.latitude ?: 24.022093
        val lon = location?.longitude ?: -104.659398

        // 3. HTML corregido (se añadió el ")" que faltaba en setView)
        val contenidoHtml = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
                <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
                <style>
                    #map { height: 100vh; width: 100vw; margin: 0; padding: 0; }
                    body { margin: 0; overflow: hidden; }
                </style>
            </head>
            <body>
                <div id="map"></div>
                <script>
                    var map = L.map('map').setView([$lat, $lon], 16); 
                    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                        attribution: '&copy; OSM'
                    }).addTo(map);
                    L.marker([$lat, $lon]).addTo(map)
                        .bindPopup('¡Estás aquí!')
                        .openPopup();
                </script>
            </body>
            </html>
        """.trimIndent()

        webViewMapa.loadDataWithBaseURL(null, contenidoHtml, "text/html", "UTF-8", null)

        // --- SALUDO Y DISEÑO ---
        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        val nombreRecibido = intent.getStringExtra("USER_NAME") ?: "Usuario"
        tvGreeting.text = "¡Hola $nombreRecibido!"

        val mainView = findViewById<android.view.View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}