package com.aplicacion.appcomunidad;

import java.io.Serializable;
import java.util.List;

public class Envio implements Serializable {
    private String ID;
    private String ID_Compra;
    private String Nombre;
    private String Direccion_Destino;
    private String Estado;
    private String Costo_envio;
    private String Latitud;
    private String Longitud;
    private List<DetalleEnvio> detalles;

    // Getters y Setters
    public String getID() { return ID; }
    public void setID(String ID) { this.ID = ID; }
    public String getID_Compra() { return ID_Compra; }
    public void setID_Compra(String ID_Compra) { this.ID_Compra = ID_Compra; }
    public String getNombre() { return Nombre; }
    public void setNombre(String nombre) { Nombre = nombre; }
    public String getDireccion_Destino() { return Direccion_Destino; }
    public void setDireccion_Destino(String direccion_Destino) { Direccion_Destino = direccion_Destino; }
    public String getEstado() { return Estado; }
    public void setEstado(String estado) { Estado = estado; }
    public String getCosto_envio() { return Costo_envio; }
    public void setCosto_envio(String costo_envio) { Costo_envio = costo_envio; }
    public String getLatitud() { return Latitud; }
    public void setLatitud(String latitud) { Latitud = latitud; }
    public String getLongitud() { return Longitud; }
    public void setLongitud(String longitud) { Longitud = longitud; }
    public List<DetalleEnvio> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleEnvio> detalles) { this.detalles = detalles; }
}
