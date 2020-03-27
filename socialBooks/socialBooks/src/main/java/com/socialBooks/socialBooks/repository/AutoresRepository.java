package com.socialBooks.socialBooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialBooks.socialBooks.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long>{
	
}
