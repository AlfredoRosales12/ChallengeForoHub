package com.alura.ForoHub.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaDeCreacion,
    String nombreDeUsuario,
    String curso

) {

    public DatosRespuestaTopico(Topico topico){
        this(   topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getAutor().getNombre(),
                topico.getCurso()
        );
    }

}
