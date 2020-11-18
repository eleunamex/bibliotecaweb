package it.solvingteam.bibliotecaweb.dao.autore;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solvingteam.bibliotecaweb.model.Autore;

public class AutoreDAOImpl implements AutoreDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Autore> list() throws Exception {
		return entityManager.createQuery("select distinct a from Autore a left JOIN FETCH a.listaLibri l", Autore.class).getResultList();
	}

	@Override
	public Autore get(Long id) throws Exception {
		TypedQuery<Autore> query = entityManager.createQuery("select a from Autore a left JOIN FETCH a.listaLibri l where a.id = ?1 ", Autore.class);
		return query.setParameter(1, id).getSingleResult();
	}

	@Override
	public void update(Autore autoreInstance) throws Exception {
		if (autoreInstance == null) {
			throw new Exception("Problema valore in input");
		}
		autoreInstance = entityManager.merge(autoreInstance);
	}

	@Override
	public void insert(Autore autoreInstance) throws Exception {
		if (autoreInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(autoreInstance);
	}

	@Override
	public void delete(Autore autoreInstance) throws Exception {
		if (autoreInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(autoreInstance));
	}

	@Override
	public List<Autore> searchAutore(Autore autoreInstance) throws Exception {
		if (autoreInstance == null) {
			throw new Exception("Problema valore in input");
		}
		TypedQuery<Autore> query = entityManager
				.createQuery("select distinct a from Autore a where a.nome like ?1 and a.cognome like ?2", Autore.class);
		String nome = autoreInstance.getNome();
		String cognome = autoreInstance.getCognome();
		query.setParameter(1, nome == null || nome == "" ? "%%" : "%" + nome + "%");
		query.setParameter(2, cognome == null || cognome == "" ? "%%" : "%" + cognome + "%");
		return query.getResultList();
	}

}
