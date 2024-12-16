package com.capstone.skinsense.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.skinsense.data.request.RegisterRequest
import com.capstone.skinsense.data.response.RegisterResponse
import com.capstone.skinsense.data.api.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RegisterViewModel(private val apiService: ApiService) : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun registerUser(request: RegisterRequest) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            try {
                val response = apiService.registerUser(request)
                _registerState.value = RegisterState.Success(response)
            } catch (e: HttpException) {
                _registerState.value = RegisterState.Error("HTTP Error: ${e.message}")
            } catch (e: IOException) {
                _registerState.value = RegisterState.Error("Network Error: ${e.message}")
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error("Unexpected Error: ${e.message}")
            }
        }
    }

    sealed class RegisterState {
        object Idle : RegisterState()
        object Loading : RegisterState()
        data class Success(val response: RegisterResponse) : RegisterState()
        data class Error(val message: String) : RegisterState()
    }
}
