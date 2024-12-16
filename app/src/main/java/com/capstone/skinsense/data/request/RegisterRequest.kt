package com.capstone.skinsense.data.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("username") val username: String,
    @field:SerializedName("email") val email: String,
    @field:SerializedName("phone") val phone: String,
    @field:SerializedName("password") val password: String,
    @field:SerializedName("repeatPassword") val repeatPassword: String
)
