package com.aplicacion.appcomunidad;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Part;

// Interfaz ApiService
public interface ApiService {
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> login(
            @Field("Correo_electronico") String correo,
            @Field("Contrasena") String contrasena
    );

    @GET("api/usuarios")
    Call<List<Usuario>> obtenerUsuarios();
    @GET("api/productos")
    Call<List<Producto>> obtenerProductos();
}