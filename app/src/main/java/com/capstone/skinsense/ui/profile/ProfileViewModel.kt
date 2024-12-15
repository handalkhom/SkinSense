package com.capstone.skinsense.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.capstone.skinsense.data.TokenPreferences

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val tokenPreferences = TokenPreferences(application)

    // Expose user profile data as LiveData
    val username: LiveData<String?> = tokenPreferences.username.asLiveData()
    val name: LiveData<String?> = tokenPreferences.name.asLiveData()
    val email: LiveData<String?> = tokenPreferences.email.asLiveData()
    val phone: LiveData<String?> = tokenPreferences.phone.asLiveData()
}
