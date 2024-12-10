package com.aplicacion.appcomunidad;
import android.util.Log;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    @GET("api/productos/artesano/{idArtesano}")
    Call<List<ProductoArtesano>> listarProductosPorArtesano(@Path("idArtesano") int idArtesano);
    @GET("api/sobre-nosotros")
    Call<Map<String, String>> obtenerSobreNosotros();
    @GET("api/obtenerContenido")
    Call<Map<String, Object>> obtenerContenido();
    @GET("api/cambiarEstadoUsuario/{id}")
    Call<Void> cambiarEstadoUsuario(@Path("id") String id);

    @GET("api/aumentarStock/{idProducto}")
    Call<Void> aumentarStock(@Path("idProducto") String idProducto);

    @GET("api/reducirStock/{idProducto}")
    Call<Void> reducirStock(@Path("idProducto") String idProducto);

    @GET("api/disponibleProducto/{idProducto}")
    Call<Void> disponibleProducto(@Path("idProducto") String idProducto);

    @GET("api/compraUsuario/{userId}")
    Call<List<Compra>> getCompras(@Path("userId") int userId);

    @GET("api/envioUsuario/{userId}")
    Call<List<Envio>> getEnvios(@Path("userId") int userId);

    @GET("api/confirmarEntrega/{envioId}")
    Call<Void> confirmarEntrega(@Path("envioId") String envioId);
}

