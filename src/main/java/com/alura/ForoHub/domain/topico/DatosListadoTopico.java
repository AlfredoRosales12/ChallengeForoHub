package com.alura.ForoHub.domain.topico;

import com.alura.ForoHub.domain.usuario.Status;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        String nombreDeUsuario,
        Status status,
        String curso

) {

    public DatosListadoTopico(Topico topico){
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaDeCreacion(),topico.getAutor().getNombre(),topico.getStatus(),topico.getCurso());
    }

}
