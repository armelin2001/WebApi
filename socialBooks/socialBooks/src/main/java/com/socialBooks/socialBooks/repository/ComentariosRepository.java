package com.socialBooks.socialBooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialBooks.socialBooks.domain.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long>{

}
