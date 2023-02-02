package com.rquintanilha.tutorial.introducaoaofirebase.view.telaprincipal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.rquintanilha.tutorial.introducaoaofirebase.R
import com.rquintanilha.tutorial.introducaoaofirebase.databinding.ActivityTelaPrincipalBinding
import com.rquintanilha.tutorial.introducaoaofirebase.view.formlogin.FormLogin

class TelaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtDeslogar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val voltarTelaLogin = Intent (this,FormLogin::class.java)
            startActivity(voltarTelaLogin)
            finish()
        }
    }
}