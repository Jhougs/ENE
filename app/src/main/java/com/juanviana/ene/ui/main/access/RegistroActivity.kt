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
import com.juanviana.ene.R
import com.juanviana.ene.databinding.ActivityRegistroBinding
import com.juanviana.ene.ui.main.model.User
import java.util.regex.Pattern

private lateinit var registerBinding: ActivityRegistroBinding
private lateinit var auth: FirebaseAuth

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        registerBinding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        registerBinding.buttonRegister.setOnClickListener {
            var usuario = registerBinding.UsuarioEt.text.toString()
            var correo = registerBinding.CorreoEt.text.toString()
            var password = registerBinding.contraEt.text.toString()
            var confir = registerBinding.confirEt.text.toString()

            if(password != confir){
                Toast.makeText(this,"Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(correo, password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {

                            Log.d("Register", "createUserWithEmail:success")
                            Log.w("Register", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Registro exitoso",
                                Toast.LENGTH_SHORT).show()
                            createUser(correo,usuario)

                            val user = auth.currentUser

                        } else {
                            var msg= ""
                            if(task.exception?.localizedMessage== "The email address is badly formatted.")
                                msg= "El correo electronico está mal digitado"
                            else if(task.exception?.localizedMessage== "The given password is invalid. [ Password should be at least 6 characters ]")
                                msg = "La contraseña debe ser de minino 6 caracteres"
                            else if(task.exception?.localizedMessage== "The email address is already in use by another account.")
                                msg= "Ya existe una cuenta con estas credenciales"

                            Log.w("Register", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, msg,
                                Toast.LENGTH_SHORT).show()

                        }
                    }

            }

        }

    }

    private fun createUser(email: String, usuario: String) {
        val id= auth.currentUser?.uid
        id?.let { id ->
            val user = User(id = id, email = email, usuario= usuario)
            val db = Firebase.firestore
            db.collection("users").document(id)
                .set(user)
                .addOnSuccessListener { documentReference ->
                    Log.d("CreateInDB", "DocumentSnapshot added with ID: ${id}")
                    envioLogin()
                }
                .addOnFailureListener { e ->
                    Log.w("CreateInDB", "Error adding document", e)
                }
        }
    }


    private fun envioLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        //intent.putExtra("email", registerBinding.CorreoEt.text.toString())
        //intent.putExtra("Contra", registerBinding.contraEt.text.toString())
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)

    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}