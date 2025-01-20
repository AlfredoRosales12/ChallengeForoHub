package com.alura.ForoHub.controller;


import com.alura.ForoHub.domain.topico.DatosRegistroTopico;
import com.alura.ForoHub.domain.topico.DatosRespuestaTopico;
import com.alura.ForoHub.domain.topico.Topico;
import com.alura.ForoHub.domain.topico.TopicoRepository;
import com.alura.ForoHub.domain.usuario.Usuario;
import com.alura.ForoHub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<Page<DatosRespuestaTopico>> listarTopicos(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findByStatusActivo(paginacion).map(DatosRespuestaTopico::new));
    }
}
