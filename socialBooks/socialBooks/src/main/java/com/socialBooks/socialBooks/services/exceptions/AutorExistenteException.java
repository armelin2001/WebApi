package com.socialBooks.socialBooks.services.exceptions;

public class AutorExistenteException extends RuntimeException{
	public AutorExistenteException(String mensagem) {
		super(mensagem);
	}
	public AutorExistenteException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
