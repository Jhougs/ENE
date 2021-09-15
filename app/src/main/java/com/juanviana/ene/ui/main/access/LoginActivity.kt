package com.juanviana.ene.ui.main.access

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.juanviana.ene.MainActivity
import com.juanviana.ene.R
import com.juanviana.ene.databinding.ActivityLoginBinding
import com.juanviana.ene.ui.main.model.User
import java.util.regex.Pattern


private lateinit var loginBinding: ActivityLoginBinding
private lateinit var auth : FirebaseAuth;

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("metodo", "onCreate")
        setContentView(R.layout.activity_login)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        auth= Firebase.auth


        loginBinding.iniciosesButton.setOnClickListener {
            val email = loginBinding.correoEditText.text.toString()
            var contra = loginBinding.contraEditText.text.toString()
            var data = intent.extras



           singIn(email,contra)

        }

        loginBinding.RegisterTextview.setOnClickListener {
            var intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)

        }

    }

    private fun singIn(email: String, password:String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Login", "signInWithEmail:success")
                    inicioSesion()
                    val user = auth.currentUser

                } else {
                    var msg= ""
                    if(task.exception?.localizedMessage== "The email address is badly formatted.")
                        msg= "El correo electronico está mal digitado"
                    else if (task.exception?.localizedMessage=="There is no user record corresponding to this identifier. The user may have been deleted.")
                        msg= "No existe una cuenta con este correo electronico "
                    if(task.exception?.localizedMessage== "The password is invalid or the user does not have a password.")
                        msg= "Su correo o contraseña son invalidas"
                    // If sign in fails, display a message to the user.
                    Log.w("Login", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext,msg,
                        Toast.LENGTH_SHORT).show()

                }
            }
    }



    private fun inicioSesion() {

        val intent = Intent(this, MainActivity::class.java)
        //intent.putExtra("email", email)
        //intent.putExtra("contraseña", contra)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }


}