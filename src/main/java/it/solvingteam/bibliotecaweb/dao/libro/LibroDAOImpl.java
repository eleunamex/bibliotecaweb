package it.solvingteam.bibliotecaweb.dao.libro;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solvingteam.bibliotecaweb.model.Libro;

public class LibroDAOImpl implements LibroDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Libro> list() throws Exception {
		return entityManager.createQuery("select distinct l from Libro l JOIN FETCH l.autore a",Libro.class).getResultList();
	}

	@Override
	public Libro get(Long id) throws Exception {
//		return entityManager.find(Libro.class, id);
		TypedQuery<Libro> query = entityManager.createQuery("select l from Libro l JOIN FETCH l.autore a where l.id = ?1", Libro.class);
		return query.setParameter(1, id).getSingleResult();
	}

	@Override
	public void update(Libro libroInstance) throws Exception {
		if (libroInstance == null) {
			throw new Exception("Problema valore in input");
		}
		libroInstance = entityManager.merge(libroInstance);
	}

	@Override
	public void insert(Libro libroInstance) throws Exception {
		if (libroInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(libroInstance);
	}

	@Override
	public void delete(Libro libroInstance) throws Exception {
		if (libroInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(libroInstance));
	}

	@Override
	public List<Libro> searchLibri(Libro libroInstance) throws Exception {
		if (libroInstance == null) {
			throw new Exception("Problema valore in input");
		}
		TypedQuery<Libro> query = entityManager.createQuery("select l from Libro l JOIN FETCH l.autore a where l.genere like ?1"
				+ " and l.titolo like ?2 and l.trama like ?3 and a.nome like ?4 and a.cognome like ?5 ", Libro.class);
		String genere=libroInstance.getGenere();
		String titolo=libroInstance.getTitolo();
		String trama=libroInstance.getTrama();
		String nome=libroInstance.getAutore().getNome();
		String cognome=libroInstance.getAutore().getCognome();
		 query.setParameter(1, genere == null || genere == "" ?  "%%" : "%"+genere+"%");
		 query.setParameter(2, titolo == null || titolo == "" ?  "%%" : "%"+titolo+"%" );
		 query.setParameter(3, trama  == null || trama == ""  ?  "%%" : "%"+trama+"%");
		 query.setParameter(4, nome   == null || nome == ""   ?  "%%" : "%"+nome+"%");
		 query.setParameter(5, cognome== null || cognome == ""?  "%%" : "%"+cognome+"%");
		 return query.getResultList();
	}

}
