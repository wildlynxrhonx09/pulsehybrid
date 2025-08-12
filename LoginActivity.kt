package com.pulsehybridx.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pulsehybridx.app.databinding.ActivityLoginBinding
import com.pulsehybridx.app.utils.FirebaseUtils

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                FirebaseUtils.signIn(email, pass) { success, msg ->
                    if (success) {
                        startActivity(Intent(this, MapActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, msg ?: "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Enter email & password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                FirebaseUtils.register(email, pass) { success, msg ->
                    if (success) {
                        startActivity(Intent(this, MapActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, msg ?: "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Enter email & password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
