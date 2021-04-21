package com.juanviana.ene

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.juanviana.ene.databinding.ActivityLoginBinding
import java.util.regex.Pattern


private lateinit var loginBinding: ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("metodo", "onCreate")
        setContentView(R.layout.activity_login)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)


        loginBinding.iniciosesButton.setOnClickListener {
            val email = loginBinding.correoEditText.text.toString()
            var contra = loginBinding.contraEditText.text.toString()
            var data = intent.extras
            var confircontra = data?.getString("Contra")
            var confircorr = data?.getString("email")


            if (email.isNotEmpty()) {
                if (validarEmail(email)) {
                    loginBinding.correoTextlayout.error = null
                    if (contra.isNotEmpty()) {
                        loginBinding.contraTextlayout.error = null
                        if ((email == confircorr && confircontra == contra) ||
                                (email == data?.getString(("confiremail")) && contra == data.getString("confircon"))) {
                            inicioSesion(email, contra)
                        } else {
                            Toast.makeText(this, R.string.error_register, Toast.LENGTH_LONG).show()
                        }
                    } else {

                        loginBinding.contraTextlayout.error = getString(R.string.Error_message)
                    }

                } else {
                    loginBinding.correoTextlayout.error = getString(R.string.error_email)
                }
            } else {
                loginBinding.correoTextlayout.error = getString(R.string.Email_required)
            }

        }

        loginBinding.RegisterTextview.setOnClickListener {
            var intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)

        }

    }

    private fun inicioSesion(email: String, contra: String) {

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("contraseña", contra)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }


}