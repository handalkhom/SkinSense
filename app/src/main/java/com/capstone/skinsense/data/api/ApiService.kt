package com.capstone.skinsense.data.api

import com.capstone.skinsense.data.request.LoginRequest
import com.capstone.skinsense.data.request.RegisterRequest
import com.capstone.skinsense.data.response.ApiResponse
import com.capstone.skinsense.data.response.FileUploadResponse
import com.capstone.skinsense.data.response.LoginResponse
import com.capstone.skinsense.data.response.PredictResponse
import com.capstone.skinsense.data.response.RegisterResponse
import com.capstone.skinsense.data.response.Skin
import com.capstone.skinsense.data.response.SkinDetailResponse
import com.capstone.skinsense.data.response.SkinListResponse
import com.capstone.skinsense.data.response.UserDetailResponse
import com.capstone.skinsense.data.response.UserListResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    //User
    @GET("users")
    suspend fun getUsers(): UserListResponse

    @GET("users/{userId}")
    suspend fun getUserDetail(
        @Path("userId") userId: Int
    ): UserDetailResponse

    @POST("users/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("users/signup")
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    //Skin
    @GET("skin")
    suspend fun getSkins(): SkinListResponse

    @GET("skin/user/{userId}")
    suspend fun getSkinByUserId(
        @Path("userId") userId: Int
    ): SkinListResponse

    @GET("skin/{skinId}")
    suspend fun getSkinById(
        @Path("skinId") skinId: Int
    ): SkinDetailResponse

    @DELETE("skin/{skinId}")
    suspend fun deleteSkinById(
        @Path("skinId") skinId: Int
    ): ApiResponse<Unit>

    @Multipart
    @POST("skin/predict")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("user_id") userId: RequestBody
    ): PredictResponse





}