package com.aplicacion.appcomunidad;

import java.io.Serializable;

public class DetalleProducto implements Serializable {
    private String ID_Producto;
    private String Nombre;
    private String Imagen_URL;
    private String Estado;
    private String Cantidad;
    private String Latitud;
    private String Longitud;

    // Getters y Setters
    public String getID_Producto() { return ID_Producto; }
    public void setID_Producto(String ID_Producto) { this.ID_Producto = ID_Producto; }
    public String getNombre() { return Nombre; }
    public void setNombre(String nombre) { Nombre = nombre; }
    public String getImagen_URL() { return Imagen_URL; }
    public void setImagen_URL(String imagen_URL) { Imagen_URL = imagen_URL; }
    public String getEstado() { return Estado; }
    public void setEstado(String estado) { Estado = estado; }
    public String getCantidad() { return Cantidad; }
    public void setCantidad(String cantidad) { Cantidad = cantidad; }
    public String getLatitud(){return  Latitud;}
    public void setLatitud(String latitud) {Latitud = latitud; }
    public String getLongitud() {return Longitud;}
    public void setLongitud(String longitud) { Longitud = longitud;}
}
