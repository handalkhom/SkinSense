package com.capstone.skinsense.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.skinsense.data.TokenPreferences

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val tokenPreferences = TokenPreferences(application)

    // Expose user profile data as LiveData
    val username: LiveData<String?> = tokenPreferences.username.asLiveData()
}