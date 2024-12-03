package com.aplicacion.appcomunidad;

public class Producto {
    private String ID;
    private String Nombre;
    private String Precio;
    private String Stock;
    private String Imagen_URL;
    private String Descripcion;
    private String imagen_url;

    public Producto(String ID, String Nombre, String Precio, String Stock,
                    String Imagen_URL, String Descripcion, String imagen_url) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Stock = Stock;
        this.Imagen_URL = Imagen_URL;
        this.Descripcion = Descripcion;
        this.imagen_url = imagen_url;
    }

    public String getID() { return ID; }
    public String getNombre() { return Nombre; }
    public String getPrecio() { return Precio; }
    public String getStock() { return Stock; }
    public String getImagen_URL() { return Imagen_URL; }
    public String getDescripcion() { return Descripcion; }
    public String getImagen_url() { return imagen_url; }

    public void setID(String ID) { this.ID = ID; }
    public void setNombre(String Nombre) { this.Nombre = Nombre; }
    public void setPrecio(String Precio) { this.Precio = Precio; }
    public void setStock(String Stock) { this.Stock = Stock; }
    public void setImagen_URL(String Imagen_URL) { this.Imagen_URL = Imagen_URL; }
    public void setDescripcion(String Descripcion) { this.Descripcion = Descripcion; }
    public void setImagen_url(String imagen_url) { this.imagen_url = imagen_url; }
}
