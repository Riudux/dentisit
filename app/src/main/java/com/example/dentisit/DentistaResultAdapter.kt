package com.example.dentisit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class DentistaResultAdapter(private val listaDentistas: List<DentistaResult>) :
    RecyclerView.Adapter<DentistaResultAdapter.DentistaViewHolder>() {

    // A. El ViewHolder: Es la "caja de herramientas" que sostiene las referencias a los TextViews, RatingBar, etc. de tu XML.
    class DentistaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFoto: ShapeableImageView = itemView.findViewById(R.id.imgDentistaFoto)
        val tvNombre: TextView = itemView.findViewById(R.id.tvDentistaNombre)
        val tvEspecialidad: TextView = itemView.findViewById(R.id.tvDentistaEspecialidad)
        val tvDireccion: TextView = itemView.findViewById(R.id.tvDentistaDireccion)
        val ratingStars: RatingBar = itemView.findViewById(R.id.ratingDentista)
    }

    // B. onCreateViewHolder: Se llama una vez por cada tarjeta que el RecyclerView necesita "inflar" (dibujar) de tu XML.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DentistaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dentista_busqueda, parent, false)
        return DentistaViewHolder(view)
    }

    // C. onBindViewHolder: ¡Aquí ocurre la magia! Se llama cada vez que una tarjeta entra en la pantalla.
    // Tomas los datos de un dentista específico de tu lista y los pones en los campos correctos.
    override fun onBindViewHolder(holder: DentistaViewHolder, position: Int) {
        val dentista = listaDentistas[position]

        holder.tvNombre.text = dentista.nombre
        holder.tvEspecialidad.text = dentista.especialidad
        holder.tvDireccion.text = dentista.direccion
        holder.ratingStars.rating = dentista.estrellas.toFloat()

        // Por ahora usaremos tu logo por defecto, luego podrías cargar imágenes reales de Firebase/Glide
        holder.imgFoto.setImageResource(R.drawable.ic_dentist_logo)

        // ... (resto de tu onBindViewHolder en DentistaResultAdapter) ...

        // Acción al hacer clic en la tarjeta: ¡NUEVA CONEXIÓN!
        holder.itemView.setOnClickListener {
            // 1. Obtenemos el contexto (la actividad que sostiene la lista)
            val context = holder.itemView.context

            // 2. Creamos el Intent para abrir la nueva pantalla (PerfilDentistaActivity)
            val intent = Intent(context, PerfilDentistaActivity::class.java)

            // 3. Pasamos el ID del dentista como extra. ¡SUPER IMPORTANTE!
            intent.putExtra("DENTISTA_ID", dentista.id)
            // También podrías pasar el nombre de usuario ("Richi") si lo tienes a mano.

            // 4. Iniciamos la actividad
            context.startActivity(intent)

            // Comentamos el Toast viejo:
            // Toast.makeText(holder.itemView.context, "Ver perfil de ${dentista.nombre}", Toast.LENGTH_SHORT).show()
        }

// ... (resto de tu onBindViewHolder) ...
    }

    // D. getItemCount: Indica cuántos elementos hay en total en tu lista.
    override fun getItemCount(): Int {
        return listaDentistas.size
    }
}