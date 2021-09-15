package com.juanviana.ene.ui.main.pages.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.juanviana.ene.R
import com.juanviana.ene.databinding.FragmentHomeBinding
import com.juanviana.ene.server.Prenda
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.juanviana.ene.ui.main.access.LoginActivity


class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var HomePrendaAdapter: HomePageAdapter
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        HomePrendaAdapter= HomePageAdapter(onItemClicked = {OnPrendaItemClicked(it)})
        binding.prendaRecyclerView.apply {
            layoutManager= LinearLayoutManager(this@HomeFragment.context)
            adapter= HomePrendaAdapter
            setHasFixedSize(false)
        }

        loadFromServer()
        return root




    }

    private fun loadFromServer() {
       val db= Firebase.firestore
       var listprenda : MutableList<Prenda> = arrayListOf()
        db.collection("prenda").get().addOnSuccessListener { result ->
            for (document in result){
                listprenda.add(document.toObject<Prenda>())
            }
            HomePrendaAdapter.appendItems(listprenda)
        }
    }


    private fun OnPrendaItemClicked(prenda: Prenda) {

        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToPrendaFagment(prenda = prenda))
    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout_menu -> {
                val intent = Intent(requireContext(),LoginActivity::class.java)
                FirebaseAuth.getInstance().signOut()
                startActivity(intent)


            }
        }
        return super.onOptionsItemSelected(item)
    }

}



/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout_menu -> {
                enviar()


            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun enviar() {
        var data = intent.extras
        var email= data?.getString("email")
        var contra= data?.getString("contraseña")
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("confiremail", email)
        intent.putExtra("confircon",contra)
        startActivity(intent)
        finish()

    }

    }

*/
