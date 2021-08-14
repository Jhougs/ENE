package com.juanviana.ene

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.juanviana.ene.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var HomeBinding: FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        HomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_home, container, false)

    }
          // var data = intent.extras
          // var email= data?.getString("email")
          // var contra= data?.getString("contraseña")
          // mainbinding.textView.text = email //mainbinding.contraTextview.text= contra

           // Log.d("metodo", "onCreate")
           //}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout_menu -> {
                //enviar()


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
