package com.juanviana.ene

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.juanviana.ene.databinding.ActivityMainBinding

private lateinit var mainbinding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainbinding.root)

        var data = intent.extras
        var email= data?.getString("email")
        var contra= data?.getString("contraseña")
        mainbinding.textView.text = email
        mainbinding.contraTextview.text= contra

        Log.d("metodo", "onCreate")
    }

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