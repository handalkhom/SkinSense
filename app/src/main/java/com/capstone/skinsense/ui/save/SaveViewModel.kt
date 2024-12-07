package com.capstone.skinsense.ui.save

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SaveViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is save Fragment"
    }
    val text: LiveData<String> = _text
}