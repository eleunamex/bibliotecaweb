package it.solvingteam.bibliotecaweb.service.utente;

import java.util.List;

import it.solvingteam.bibliotecaweb.dao.utente.UtenteDAO;
import it.solvingteam.bibliotecaweb.model.Utente;

public interface UtenteService {
	
	public List<Utente> listAll() throws Exception;

	public Utente caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Utente utenteInstance) throws Exception;

	public void inserisciNuovo(Utente utenteInstance) throws Exception;

	public void rimuovi(Utente utenteInstance) throws Exception;
	
	public Utente autenticazione(String username, String password) throws Exception;
	
	public List<Utente> cercaUtente(Utente utenteInstance) throws Exception;

	public void setUtenteDAO(UtenteDAO utenteDAO);
}
