package com.juanviana.ene.ui.main.access

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.juanviana.ene.R
import com.juanviana.ene.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get()= _binding!!
    private lateinit var auth : FirebaseAuth;



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentLoginBinding.inflate(inflater,container,false)
        val root: View = binding.root
        auth= Firebase.auth

        with(binding) {
            iniciosesButton.setOnClickListener {
            val email = correoEditText.text.toString()
            var contra = contraEditText.text.toString()
            singIn(email,contra)
        }
            RegisterTextview.setOnClickListener{
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }



        return root


    }

    private fun singIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Login", "signInWithEmail:success")
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
                    Toast.makeText(requireContext(),msg,
                        Toast.LENGTH_SHORT).show()

                }
            }

    }


}