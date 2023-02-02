package com.rquintanilha.tutorial.introducaoaofirebase.view.formlogin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.rquintanilha.tutorial.introducaoaofirebase.databinding.ActivityFormLoginBinding
import com.rquintanilha.tutorial.introducaoaofirebase.view.formcadastro.FormCadastro
import com.rquintanilha.tutorial.introducaoaofirebase.view.telaprincipal.TelaPrincipal

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding
    private val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btEntrar.setOnClickListener { view ->

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                val snackbar =
                    Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                auth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener { autenticacao ->
                        if (autenticacao.isSuccessful) {
                            navegarTelaPrincipal()
                        }
                    }.addOnFailureListener { exception ->
                        val mensagemErro = when (exception) {

                            is FirebaseAuthWeakPasswordException -> "Senha incorreta!"
                            is FirebaseAuthInvalidCredentialsException -> "Senha incorreta!"
                            is FirebaseNetworkException -> "Sem conexão com a internet."
                            else -> "Erro no login!"

                        }
                        val snackbar =
                            Snackbar.make(view, mensagemErro, Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.show()
                    }
            }
        }

        binding.txtTelaCadastro.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }
    }

    private fun navegarTelaPrincipal() {
        val intent = Intent(this, TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()


        val usuarioAtual = FirebaseAuth.getInstance().currentUser
        if (usuarioAtual != null){
            navegarTelaPrincipal()
        }
    }

}