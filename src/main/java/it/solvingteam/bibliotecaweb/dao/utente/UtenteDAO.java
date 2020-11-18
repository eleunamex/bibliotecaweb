package it.solvingteam.bibliotecaweb.dao.utente;

import java.util.List;

import it.solvingteam.bibliotecaweb.dao.IBaseDAO;
import it.solvingteam.bibliotecaweb.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente>{
	
	public Utente authentication(String username, String password) throws Exception;
	
	public List<Utente> searchUtente(Utente utenteInstance) throws Exception;
}
