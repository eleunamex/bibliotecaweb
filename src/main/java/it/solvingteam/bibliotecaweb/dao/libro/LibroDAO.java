package it.solvingteam.bibliotecaweb.dao.libro;

import java.util.List;

import it.solvingteam.bibliotecaweb.dao.IBaseDAO;
import it.solvingteam.bibliotecaweb.model.Libro;

public interface LibroDAO extends IBaseDAO<Libro>{
	
	public List<Libro> searchLibri(Libro libroInstance) throws Exception;
	
}
