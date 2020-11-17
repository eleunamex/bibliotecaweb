package it.solvingteam.bibliotecaweb.service.autore;

import java.util.List;

import it.solvingteam.bibliotecaweb.dao.autore.AutoreDAO;
import it.solvingteam.bibliotecaweb.model.Autore;

public interface AutoreService {

	public List<Autore> listAll() throws Exception;

	public Autore caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Autore autoreInstance) throws Exception;

	public void inserisciNuovo(Autore autoreInstance) throws Exception;

	public void rimuovi(Autore autoreInstance) throws Exception;
	
	public void setAutoreDAO(AutoreDAO autoreDAO);
}
