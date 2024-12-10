package com.aplicacion.appcomunidad;

public class CarouselItem {
    private String titulo;
    private String subtitulo;
    private String contenido;
    private String imagen;

    public CarouselItem(String titulo, String subtitulo, String contenido, String imagen) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.contenido = contenido;
        this.imagen = imagen;
    }

    public String getTitulo() { return titulo; }
    public String getSubtitulo() { return subtitulo; }
    public String getContenido() { return contenido; }
    public String getImagen() { return imagen; }
}
