package com.capstone.skinsense.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.skinsense.data.TokenPreferences
import com.capstone.skinsense.data.api.ApiConfig
import com.capstone.skinsense.data.request.LoginRequest
import com.capstone.skinsense.data.response.Login
import kotlinx.coroutines.launch

//sebelum edit

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val tokenPreferences = TokenPreferences(application)

    private val _loginResult = MutableLiveData<Result<Login>>()
    val loginResult: LiveData<Result<Login>> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService(getApplication()).loginUser(LoginRequest(email, password))
                if (response.status == 200) {
                    val loginData = response.data
                    loginData.token?.let { tokenPreferences.saveToken(it) }

                    getUserIdFromUsername(loginData.username)

//                    saveLoginData(loginData.token ?: "", loginData.email ?: "")

                    _loginResult.value = Result.success(loginData)
                } else {
                    _loginResult.value = Result.failure(Exception(response.message))
                }
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }

    private fun getUserIdFromUsername(username: String?) {
        viewModelScope.launch {
            try {
                val userResponse = ApiConfig.getApiService(getApplication()).getUsers()
                val user = userResponse.data.find { it.username == username }
                if (user != null) {
                    // Save user_id dari val userId pada response ke DataStore
                    tokenPreferences.saveUserId(user.userId.toString()) // Save user_id
                }
            } catch (e: Exception) {
                // Handle error if needed
            }
        }
    }

    private fun saveLoginData(token: String, userId: String) {
        viewModelScope.launch {
            tokenPreferences.saveToken(token)
            tokenPreferences.saveUserId(userId)
        }
    }
}