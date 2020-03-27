package com.socialBooks.socialBooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.socialBooks.socialBooks.domain.DetalhesErro;
import com.socialBooks.socialBooks.services.exceptions.AutorExistenteException;
import com.socialBooks.socialBooks.services.exceptions.AutorNaoEncontradoException;
import com.socialBooks.socialBooks.services.exceptions.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException(LivroNaoEncontradoException e, HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O livro não pode ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalhesErro>handleAutorExistenteException(AutorExistenteException erro , HttpServletRequest request){
		DetalhesErro erros = new DetalhesErro();
		erros.setStatus(409l);
		erros.setTitulo("Autor já existente");
		erros.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		erros.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erros);
	}
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro>handleAutorNaoEncontradoException(AutorNaoEncontradoException erro, HttpServletRequest request){
		DetalhesErro erros = new DetalhesErro();
		erros.setStatus(404l);
		erros.setTitulo("O autor não pode ser encontrado");
		erros.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		erros.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erros);
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro>handleDataIntegrityViolationException(AutorExistenteException erro , HttpServletRequest request){
		DetalhesErro erros = new DetalhesErro();
		erros.setStatus(400l);
		erros.setTitulo("Requisição inválida");
		erros.setMensagemDesenvolvedor("http://erros.socialbooks.com/400");
		erros.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erros);
	}
	

}
