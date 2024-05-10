package com.example.arkpedia.data.retrofit

import retrofit2.Call
import com.example.arkpedia.data.response.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("operator")
    fun getOperators(): Call<ArrayList<UserResponse>>
}