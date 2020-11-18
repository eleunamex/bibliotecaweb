package it.solvingteam.bibliotecaweb.dao.autore;

import java.util.List;

import it.solvingteam.bibliotecaweb.dao.IBaseDAO;
import it.solvingteam.bibliotecaweb.model.Autore;

public interface AutoreDAO extends IBaseDAO<Autore>{
	public List<Autore> searchAutore(Autore autoreInstance) throws Exception;
}
