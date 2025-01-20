package com.alura.ForoHub.domain.topico;

import com.alura.ForoHub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
    @NotNull
    Long id,
    @NotBlank
    String titulo,
    @NotBlank
    String mensaje,
    @NotBlank
    String curso
)
{
}
