package com.socialBooks.socialBooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialBooks.socialBooks.domain.Autor;
import com.socialBooks.socialBooks.repository.AutoresRepository;
import com.socialBooks.socialBooks.services.exceptions.AutorExistenteException;
import com.socialBooks.socialBooks.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {
	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar(){
		return autoresRepository.findAll();	
	}
	public Autor salvar(Autor autor) {
		if(autor.getId()!=null) {
			Autor aut = autoresRepository.getOne(autor.getId());		
			if(aut!=null){
				throw new AutorExistenteException("O autor já existe");
			}
		}
		return autoresRepository.save(autor);
	}
	public Autor buscar(Long id) {
		Autor autor = autoresRepository.getOne(id);
		if(autor==null) {
			throw new AutorNaoEncontradoException("O autor não pode ser encontrado");
		}
		return autor;
	}
}
