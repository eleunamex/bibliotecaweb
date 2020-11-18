package it.solvingteam.bibliotecaweb.dao.utente;

import it.solvingteam.bibliotecaweb.dao.IBaseDAO;
import it.solvingteam.bibliotecaweb.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente>{
	public Utente authentication(String username, String password) throws Exception;
}
