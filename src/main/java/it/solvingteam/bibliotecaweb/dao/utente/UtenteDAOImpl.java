package it.solvingteam.bibliotecaweb.dao.utente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		TypedQuery<Utente> query = entityManager.createQuery("select distinct u from Utente u JOIN FETCH u.listaRuoli r where u.id = ?1 ", Utente.class);
		return query.setParameter(1, id).getSingleResult();
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

	@Override
	public Utente authentication(String username, String password) throws Exception {
		TypedQuery<Utente> query = entityManager.createQuery("select u from Utente u JOIN FETCH u.listaRuoli r where u.username = ?1 and u.password = ?2 and u.stato='ATTIVO' ", Utente.class);
		query.setParameter(1, username);
		query.setParameter(2, password);
		return query.getSingleResult();
	}

	@Override
	public List<Utente> searchUtente(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		TypedQuery<Utente> query = entityManager
				.createQuery("select distinct u from Utente u JOIN FETCH u.listaRuoli r where u.nome like ?1 and u.cognome like ?2 and u.username like ?3 ", Utente.class);
		String nome = utenteInstance.getNome();
		String cognome = utenteInstance.getCognome();
		String username = utenteInstance.getUsername();
		query.setParameter(1, nome == null || nome == "" ? "%%" : "%" + nome + "%");
		query.setParameter(2, cognome == null || cognome == "" ? "%%" : "%" + cognome + "%");
		query.setParameter(3, username == null || username == "" ? "%%" : "%" + username + "%");
		return query.getResultList();
	}

}
