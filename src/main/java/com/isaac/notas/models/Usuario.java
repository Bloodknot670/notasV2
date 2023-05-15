package com.isaac.notas.models;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String apePaterno;
    private String apeMaterno;
    private String nombre;
    private String direccion;
    private Date fechaIngreso;
    @ManyToOne
    @JoinColumn(name="fk_rol",referencedColumnName ="idRol")
    private Rol rol;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "usuario")
    private List<Nota> notas;

    @OneToMany(cascade =CascadeType.ALL,mappedBy = "usuario")
    private List<Comentario> comentarios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Respuesta> respuestas;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Credencial credencial;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String apePaterno, String apeMaterno, String nombre, String direccion, Date fechaIngreso, Rol rol, List<Nota> notas, List<Comentario> comentarios, List<Respuesta> respuestas, Credencial credencial) {
        this.idUsuario = idUsuario;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaIngreso = fechaIngreso;
        this.rol = rol;
        this.notas = notas;
        this.comentarios = comentarios;
        this.respuestas = respuestas;
        this.credencial = credencial;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }
}
