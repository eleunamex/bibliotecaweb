package it.solvingteam.bibliotecaweb.service.utente;

import java.util.List;

import javax.persistence.EntityManager;

import it.solvingteam.bibliotecaweb.dao.EntityManagerUtil;
import it.solvingteam.bibliotecaweb.dao.utente.UtenteDAO;
import it.solvingteam.bibliotecaweb.model.Utente;

public class UtenteServiceImpl implements UtenteService {
	private UtenteDAO utenteDAO;

	@Override
	public void setUtenteDAO(UtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}

	@Override
	public List<Utente> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			utenteDAO.setEntityManager(entityManager);
			return utenteDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Utente caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			utenteDAO.setEntityManager(entityManager);
			return utenteDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Utente utenteInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			utenteDAO.setEntityManager(entityManager);
			utenteDAO.update(utenteInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void inserisciNuovo(Utente utenteInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			utenteDAO.setEntityManager(entityManager);

			// controllo
			List<Utente> lista = utenteDAO.list();
			for (Utente u : lista) {
				if (u.equals(utenteInstance)) {
					throw new Exception("Autore già presente");
				}
			}

			utenteDAO.insert(utenteInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void rimuovi(Utente utenteInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			utenteDAO.setEntityManager(entityManager);
			utenteDAO.delete(utenteInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Utente autenticazione(String username, String password) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			utenteDAO.setEntityManager(entityManager);
			return utenteDAO.authentication(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}
}
