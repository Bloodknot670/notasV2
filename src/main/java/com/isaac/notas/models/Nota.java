package com.isaac.notas.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNota;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPublicacion;
    private String titulo;
    private String contenido;
    @ManyToOne
    @JoinColumn(name="fk_usuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nota")
    private List<Comentario> comentarios;
    public Nota() {
    }

    public Nota(Integer idNota, Date fechaPublicacion, String titulo, String contenido, Usuario usuario, List<Comentario> comentarios) {
        this.idNota = idNota;
        this.fechaPublicacion = fechaPublicacion;
        this.titulo = titulo;
        this.contenido = contenido;
        this.usuario = usuario;
        this.comentarios = comentarios;
    }

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
