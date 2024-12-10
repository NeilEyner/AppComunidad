package com.aplicacion.appcomunidad;

public class ProductoArtesano {

    private String ID;
    private String Nombre;
    private String Precio;
    private String Stock;
    private String Disponibilidad;
    private String Imagen_URL;
    private String Descripcion;
    private String imagen_url;

    // Constructor vacío
    public ProductoArtesano() {}

    // Constructor con todos los atributos
    public ProductoArtesano(String ID, String Nombre, String Precio, String Stock,
                            String Disponibilidad, String Imagen_URL, String Descripcion,
                            String imagen_url) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Stock = Stock;
        this.Disponibilidad = Disponibilidad;
        this.Imagen_URL = Imagen_URL;
        this.Descripcion = Descripcion;
        this.imagen_url = imagen_url;
    }

    // Getters y setters
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String Stock) {
        this.Stock = Stock;
    }

    public String getDisponibilidad() {
        return Disponibilidad;
    }

    public void setDisponibilidad(String Disponibilidad) {
        this.Disponibilidad = Disponibilidad;
    }

    public String getImagen_URL() {
        return Imagen_URL;
    }

    public void setImagen_URL(String Imagen_URL) {
        this.Imagen_URL = Imagen_URL;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }
}
