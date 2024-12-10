package com.aplicacion.appcomunidad;

import java.io.Serializable;
import java.util.List;

public class Compra implements Serializable {
    private String ID;
    private String Fecha;
    private String Estado;
    private String Total;
    private String Nombre;
    private List<DetalleProducto> detalles;

    // Getters y Setters
    public String getID() { return ID; }
    public void setID(String ID) { this.ID = ID; }
    public String getFecha() { return Fecha; }
    public void setFecha(String fecha) { Fecha = fecha; }
    public String getEstado() { return Estado; }
    public void setEstado(String estado) { Estado = estado; }
    public String getTotal() { return Total; }
    public void setTotal(String total) { Total = total; }
    public String getNombre() { return Nombre; }
    public void setNombre(String nombre) { Nombre = nombre; }
    public List<DetalleProducto> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleProducto> detalles) { this.detalles = detalles; }
}
