package com.juanviana.ene

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.juanviana.ene.databinding.ActivityRegistroBinding
import java.util.regex.Pattern

private lateinit var registerBinding: ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        registerBinding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.buttonRegister.setOnClickListener {
            var usuario = registerBinding.UsuarioEt.text.toString()
            var correo = registerBinding.CorreoEt.text.toString()
            var password = registerBinding.contraEt.text.toString()
            var confir = registerBinding.confirEt.text.toString()

            if (usuario.isNotEmpty()) {
                registerBinding.UsuarioTextLayout.error = null
                if (validarEmail(correo)) {
                    registerBinding.CorreoLayout.error = null
                    if (password.isNotEmpty()) {
                        registerBinding.contraLayout.error = null
                        if (password.length >= 6) {
                            registerBinding.contraLayout.error = null
                            if (confir.isNotEmpty()) {
                                registerBinding.contraLayout.error = null
                                if (password == confir) {
                                    User(correo, password)
                                    envioLogin()
                                    registerBinding.confirLayout.error = null
                                } else {
                                    registerBinding.confirLayout.error = getString(R.string.no_igua_contra)
                                }

                            } else {
                                registerBinding.confirLayout.error = getString(R.string.campo)
                            }
                        } else {
                            registerBinding.contraLayout.error = getString(R.string.contra_6_Dig)
                        }

                    } else {

                        registerBinding.contraLayout.error = getString(R.string.Error_message)
                    }
                } else {
                    registerBinding.CorreoLayout.error = getString(R.string.error_email)
                }
            } else {
                registerBinding.UsuarioTextLayout.error = getString(R.string.campo)

            }


        }

    }


    private fun envioLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("email", registerBinding.CorreoEt.text.toString())
        intent.putExtra("Contra", registerBinding.contraEt.text.toString())
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)

    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}