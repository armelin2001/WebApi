package com.socialBooks.socialBooks.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.socialBooks.socialBooks.domain.Comentario;
import com.socialBooks.socialBooks.domain.Livro;
import com.socialBooks.socialBooks.services.LivrosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(tags="Livros")
@RestController
@RequestMapping("/livros")
public class LivrosResources {
	@Autowired
	private LivrosService livrosService;
	@ApiOperation("Lista todos os livros")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
	} 	
	@ApiOperation("Salva novos livros")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> salvar(@Valid @RequestBody Livro livro) {
		livro = livrosService.salvar(livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@ApiOperation("Busca um livro pelo nome")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@Valid @PathVariable("id") Long id) {
		Livro livro = livrosService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	@ApiOperation("Deleta um livro pelo id")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
		livrosService.deletar(id);
		return ResponseEntity.noContent().build(); 
	}
	@ApiOperation("Edita um livro por id")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosService.editar(livro);
		return ResponseEntity.noContent().build();
	}
	@ApiOperation("Pega um id de um livro e depois adiciona um comentario ao mesmo")
	@RequestMapping(value="/{id}/comentarios",method=RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId,@RequestBody Comentario comentario) {
		livrosService.salvarComentario(livroId, comentario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}
	@ApiOperation("Lista o comentario de um livro da api")
	@RequestMapping(value="/{id}/comentarios",method=RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarCometarios(@PathVariable("id")Long livroId){
		List<Comentario> comentarios = livrosService.listarComentarios(livroId);
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
}
