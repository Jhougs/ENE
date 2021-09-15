package com.juanviana.ene.ui.main.pages.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.juanviana.ene.R
import com.juanviana.ene.databinding.CardViewPrendaHomeBinding
import com.juanviana.ene.server.Prenda
import com.squareup.picasso.Picasso

class HomePageAdapter(private val onItemClicked: (Prenda) -> Unit,
): RecyclerView.Adapter<HomePageAdapter.ViewHolder>() {

    private var listPrenda: MutableList<Prenda> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_prenda_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind((listPrenda[position]))
        holder.itemView.setOnClickListener { onItemClicked(listPrenda[position]) }
    }

    override fun getItemCount(): Int {
        return listPrenda.size

    }

    fun appendItems(newItems: MutableList<Prenda>) {
        listPrenda.clear()
        listPrenda.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CardViewPrendaHomeBinding.bind(view)
        fun bind(Prenda: Prenda) {
            with(binding) {
                textViewNombrePrenda.setText(Prenda.Nombre)
                textViewPrecio.setText(Prenda.Precio.toString())
                if (Prenda.Imagenes != null) {
                    Picasso.get().load(Prenda.Imagenes).into(prendaImageView);
                }
            }

        }
    }
}






