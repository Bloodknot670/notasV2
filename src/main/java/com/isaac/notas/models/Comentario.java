package com.isaac.notas.models;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.util.Date;
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComentario;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaComentario;
    private String textoComentario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="fk_usuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "fk_nota",referencedColumnName = "idNota")
    private Nota nota;
    @Transient
    private String nombreUsuario;
    @Transient
    private Integer idRol;
    @Transient
    private Integer idNota;
    @Transient
    private Integer idUsuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comentario")
    private List<Respuesta> respuestas;

    public Comentario() {
    }

    public Comentario(Integer idComentario, Date fechaComentario, String textoComentario, Usuario usuario, Nota nota, String nombreUsuario, Integer idRol, Integer idNota, Integer idUsuario, List<Respuesta> respuestas) {
        this.idComentario = idComentario;
        this.fechaComentario = fechaComentario;
        this.textoComentario = textoComentario;
        this.usuario = usuario;
        this.nota = nota;
        this.nombreUsuario = nombreUsuario;
        this.idRol = idRol;
        this.idNota = idNota;
        this.idUsuario = idUsuario;
        this.respuestas = respuestas;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public String getNombreUsuario(){
        if(usuario!=null){
            return usuario.getNombre();
        }
        return nombreUsuario;
    }

    public Integer getIdUsr(){
        if(usuario!=null){
            return usuario.getRol().getIdRol();
        }
        return idRol;
    }

    public Integer getIdNota(){
        if(nota!=null){
            return nota.getIdNota();
        }
        return idNota;
    }

    public Integer getIdUsur(){
        if (usuario!= null){
            return  usuario.getIdUsuario();
        }
        return idUsuario;
    }
}
