package com.capstone.skinsense.data.response

import com.google.gson.annotations.SerializedName

data class Register(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
typealias RegisterResponse = ApiResponse<Register>