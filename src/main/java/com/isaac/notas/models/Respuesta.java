package com.isaac.notas.models;



import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRespuesta;
    private String textoRespuesta;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaRespuesta;
    @ManyToOne
    @JoinColumn(name = "fk_comentario", referencedColumnName = "idComentario")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Comentario comentario;
    @ManyToOne
    @JoinColumn(name = "fk_usuario", referencedColumnName = "idUsuario")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    @Transient
    String nombreUsuario;

    @Transient
    Integer idUser;

    public Respuesta() {
    }

    public Respuesta(Integer idRespuesta, String textoRespuesta, Date fechaRespuesta, Comentario comentario, Usuario usuario) {
        this.idRespuesta = idRespuesta;
        this.textoRespuesta = textoRespuesta;
        this.fechaRespuesta = fechaRespuesta;
        this.comentario = comentario;
        this.usuario = usuario;
    }

    public Integer getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario(){
        if(usuario!=null){
            return usuario.getNombre();
        }
        return nombreUsuario;
    }
    public Integer getUsuarioId(){
        if(usuario!=null){
            return usuario.getIdUsuario();
        }
        return idUser;
    }
}
