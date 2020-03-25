package com.socialBooks.socialBooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialBooks.socialBooks.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{
	
}
