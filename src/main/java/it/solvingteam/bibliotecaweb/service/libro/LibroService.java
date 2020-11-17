package it.solvingteam.bibliotecaweb.service.libro;

import java.util.List;

import it.solvingteam.bibliotecaweb.dao.libro.LibroDAO;
import it.solvingteam.bibliotecaweb.model.Libro;

public interface LibroService {
	
	public List<Libro> listAll() throws Exception;

	public Libro caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Libro libroInstance) throws Exception;

	public void inserisciNuovo(Libro libroInstance) throws Exception;

	public void rimuovi(Libro libroInstance) throws Exception;
	
	public List<Libro> cercaLibri(Libro libroInstance) throws Exception;
	
	public void setLibroDAO(LibroDAO libroDAO);
}
