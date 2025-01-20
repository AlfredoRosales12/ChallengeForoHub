package com.alura.ForoHub.domain.topico;


import com.alura.ForoHub.domain.usuario.Status;
import com.alura.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name="topicos")
@Entity(name="Topico")

public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    private String titulo;

    @Column (unique = true)
    private String mensaje;

    private LocalDateTime fechaDeCreacion;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JoinColumn(name="usuario_id")
    private Usuario autor;

    private String curso;


    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.autor = datosRegistroTopico.usuario();
        this.curso = datosRegistroTopico.curso();
    }

    public Topico() {

    }


    //Getters and Setters
   public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
