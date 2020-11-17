package it.solvingteam.bibliotecaweb.dao.utente;

import java.util.List;

import javax.persistence.EntityManager;

import it.solvingteam.bibliotecaweb.model.Utente;

public class UtenteDAOImpl implements UtenteDAO{

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Utente> list() throws Exception {
		return entityManager.createQuery("from Utente",Utente.class).getResultList();
	}

	@Override
	public Utente get(Long id) throws Exception {
		return entityManager.find(Utente.class, id);
	}

	@Override
	public void update(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		utenteInstance = entityManager.merge(utenteInstance);
	}

	@Override
	public void insert(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(utenteInstance);
	}

	@Override
	public void delete(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(utenteInstance));
	}

}
