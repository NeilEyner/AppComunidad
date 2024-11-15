package com.aplicacion.appcomunidad;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

// Interfaz ApiService
public interface ApiService {
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> login(
            @Field("Correo_electronico") String correo,
            @Field("Contrasena") String contrasena
    );
}
