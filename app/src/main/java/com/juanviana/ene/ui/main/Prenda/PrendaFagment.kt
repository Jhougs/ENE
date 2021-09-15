package com.juanviana.ene.ui.main.Prenda

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.juanviana.ene.R
import com.juanviana.ene.databinding.FragmentHomeBinding
import com.juanviana.ene.databinding.FragmentPrendaBinding
import com.juanviana.ene.server.Prenda
import com.squareup.picasso.Picasso


class PrendaFagment : Fragment() {

    private val args: PrendaFagmentArgs by navArgs()

    companion object {
        fun newInstance() = PrendaFagment()
    }

    private var _binding: FragmentPrendaBinding? = null
    private val binding get() = _binding!!


    private lateinit var viewModel: PrendaFagmentViewModel

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPrendaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val prenda: Prenda = args.prenda

        with(binding) {
            nombrePrendaTextView.setText(prenda.Nombre.toString())
            precioPrendaTextView.setText(prenda.Precio.toString())
            if (prenda.Imagenes != null) {
                Picasso.get().load(prenda.Imagenes).into(prendaImage);
            }


            return root
        }

        /*override fun onActivityCreated(savedInstanceState: Bundle?) {


        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PrendaFagmentViewModel::class.java)
        val prenda: Prenda = args.prenda

    }

     */

    }
}