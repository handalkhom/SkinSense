package com.capstone.skinsense.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.capstone.skinsense.R
import com.capstone.skinsense.data.api.ApiConfig
import com.capstone.skinsense.data.request.RegisterRequest
import com.capstone.skinsense.databinding.ActivityRegisterBinding
import kotlinx.coroutines.flow.collect

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory(ApiConfig.getApiService(getApplication()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi View Binding
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        binding.CreateAccountBtn.setOnClickListener {
            val name = binding.SignUpName.text.toString().trim()
            val username = binding.SignUpUsername.text.toString().trim()
            val email = binding.SignUpEmail.text.toString().trim()
            val phone = binding.SignUpPhoneNumber.text.toString().trim()
            val password = binding.SignUpPassword.text.toString()
            val repeatPassword = binding.SignUpRepeatPassword.text.toString()

            if (validateInputs(name, username, email, phone, password, repeatPassword)) {
                val request = RegisterRequest(name, username, email, phone, password, repeatPassword)
                registerViewModel.registerUser(request)
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            registerViewModel.registerState.collect { state ->
                when (state) {
                    is RegisterViewModel.RegisterState.Loading -> {
                        Toast.makeText(this@RegisterActivity, R.string.loading, Toast.LENGTH_SHORT).show()
                    }
                    is RegisterViewModel.RegisterState.Success -> {
                        Toast.makeText(this@RegisterActivity, R.string.register_success, Toast.LENGTH_LONG).show()
                        finish()
                    }
                    is RegisterViewModel.RegisterState.Error -> {
                        Toast.makeText(this@RegisterActivity, state.message, Toast.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun validateInputs(
        name: String,
        username: String,
        email: String,
        phone: String,
        password: String,
        repeatPassword: String
    ): Boolean {
        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
            return false
        }
        if (password != repeatPassword) {
            Toast.makeText(this, R.string.password_mismatch, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
