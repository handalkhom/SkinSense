package com.capstone.skinsense.data.response

import com.google.gson.annotations.SerializedName

data class Login(

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class LoginRequest(val username: String, val password: String)

typealias LoginResponse = ApiResponse<Login>
