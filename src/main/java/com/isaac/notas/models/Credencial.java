package com.isaac.notas.models;

import jakarta.persistence.*;

@Entity
public class Credencial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCredencial;
    private String usr;
    private String contrasennia;
    @OneToOne
    @JoinColumn(name = "fk_usuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    public Credencial() {
    }

    public Credencial(Integer idCredencial, String usr, String contrasennia, Usuario usuario) {
        this.idCredencial = idCredencial;
        this.usr = usr;
        this.contrasennia = contrasennia;
        this.usuario = usuario;
    }

    public Integer getIdCredencial() {
        return idCredencial;
    }

    public void setIdCredencial(Integer idCredencial) {
        this.idCredencial = idCredencial;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getContrasennia() {
        return contrasennia;
    }

    public void setContrasennia(String contrasennia) {
        this.contrasennia = contrasennia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
