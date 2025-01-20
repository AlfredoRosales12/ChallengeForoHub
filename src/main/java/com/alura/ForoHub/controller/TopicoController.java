package com.alura.ForoHub.controller;


import com.alura.ForoHub.domain.topico.DatosRegistroTopico;
import com.alura.ForoHub.domain.topico.DatosRespuestaTopico;
import com.alura.ForoHub.domain.topico.Topico;
import com.alura.ForoHub.domain.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;


    //Registro de un nuevo Tópico.
    @PostMapping
    @RequestMapping("/registro")
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistro){
        Topico topico = topicoRepository.save(new Topico(datosRegistro));
        DatosRespuestaTopico datosRespuesta = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaDeCreacion(), topico.getAutor().getNombre(), topico.getCurso());
        return ResponseEntity.ok(datosRespuesta);
    }

}
