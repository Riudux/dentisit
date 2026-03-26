package com.example.dentisit

// Esta es una data class para guardar los datos. Usamos variables opcionales (nullables)
// para no obligar a tener todos los datos siempre.
data class DentistaResult(
    val id: String,
    val nombre: String,
    val especialidad: String,
    val direccion: String,
    val estrellas: Int,
    val fotoUrl: String? = null,
    // --- NUEVOS CAMPOS PARA EL PERFIL ---
    val clinicaNombre: String? = "Clínica Dental", // Nombre de la clínica debajo del logo del diente
    val proximaCitaDoctor: String? = null,        // El doctor con el que tienes la cita (ej. Juan Perez)
    val proximaCitaEspecialidad: String? = null, // Ej. Odontología
    val proximaCitaFecha: String? = null,         // Ej. Hoy, 3:00 p.m.
    val tieneCita: Boolean = false              // Para mostrar o no la tarjeta de cita
)