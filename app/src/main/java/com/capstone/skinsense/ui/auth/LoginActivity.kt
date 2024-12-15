package com.capstone.skinsense.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstone.skinsense.R
import com.capstone.skinsense.databinding.ActivityLoginBinding
import com.capstone.skinsense.ui.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.BtnSignIn.setOnClickListener {
            val email = binding.SignInEmail.text.toString().trim()
            val password = binding.SignInPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.login(email, password)
            } else {
                Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
            }
        }

        binding.BtnSignUp.setOnClickListener {
            // Navigasi ke halaman pendaftaran
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.ForgotPassword.setOnClickListener {
            // Navigasi ke halaman lupa kata sandi
            Toast.makeText(this, "Forgot Password clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        loginViewModel.loginResult.observe(this) { result ->
            result.onSuccess { loginData ->
                // Handle successful login
                Toast.makeText(this, "Welcome ${loginData.username}!", Toast.LENGTH_SHORT).show()
                // Navigasi ke MainActivity setelah login berhasil
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

            result.onFailure { exception ->
                // Handle login error
                Toast.makeText(this, exception.message ?: getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
