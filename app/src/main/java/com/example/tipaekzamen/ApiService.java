package com.example.tipaekzamen;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/auth/login")
    Call<LoginResponse> loginUser(@Body com.example.tipaekzamen.LoginRequest loginRequest);

    @POST("/auth/register")
    Call<com.example.tipaekzamen.RegisterResponse> registerUser(@Body com.example.tipaekzamen.RegisterRequest registerRequest);

}
