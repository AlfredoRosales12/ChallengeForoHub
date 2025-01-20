package com.alura.ForoHub.domain.topico;

import com.alura.ForoHub.domain.usuario.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long>{
    Page<Topico> findByStatus(Status status, Pageable paginacion);
}
