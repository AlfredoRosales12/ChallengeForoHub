package com.alura.ForoHub.controller;


import com.alura.ForoHub.domain.topico.*;
import com.alura.ForoHub.domain.usuario.Status;
import com.alura.ForoHub.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;


    //Registro de un nuevo Tópico.
    @PostMapping
    @RequestMapping("/registro")
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistro){

        //Obtenemos el usuario que se autenticó
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioActual = (Usuario) authentication.getPrincipal();

        Topico topico = topicoRepository.save(new Topico(datosRegistro,usuarioActual));

        DatosRespuestaTopico datosRespuesta = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaDeCreacion(), topico.getAutor().getNombre(), topico.getCurso());
        return ResponseEntity.ok(datosRespuesta);
    }


    // Listado de topicos
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size=10) Pageable paginacion){
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        Page<DatosListadoTopico> page = topicoRepository.findByStatus(Status.ACTIVO,paginacion).map(DatosListadoTopico::new);
        return ResponseEntity.ok(page);
    }

    // Listado de topicos por ID
    @GetMapping("{id}")
    public ResponseEntity listadoTopicosPorId(@PathVariable Long id){
        var topicoEncontrado = topicoRepository.findById(id);
        if (topicoEncontrado.isPresent()){
            var topico = topicoEncontrado.get();
            var datosRespuesta = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                    topico.getFechaDeCreacion(), topico.getAutor().getNombre(), topico.getCurso());
            return ResponseEntity.ok(datosRespuesta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    // Actualizar topico
    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        //System.out.println("Pase");
        //System.out.println(datosActualizarTopico.id());
        var topicoEncontrado = topicoRepository.findById(datosActualizarTopico.id());

        if (topicoEncontrado.isPresent()){
            System.out.println("encontrado");
            var topico = topicoEncontrado.get();
            topico.actualizarDatos(datosActualizarTopico);
            var datosRespuesta = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                    topico.getFechaDeCreacion(), topico.getAutor().getNombre(), topico.getCurso());
            return ResponseEntity.ok(datosRespuesta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Borrar un topico por el ID pasado por el usuario
    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        var topicoEncontrado = topicoRepository.findById(id);
        if (topicoEncontrado.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok("Topico con id:"+ id+" eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
