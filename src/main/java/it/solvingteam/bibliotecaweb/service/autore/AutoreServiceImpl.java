package it.solvingteam.bibliotecaweb.service.autore;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.EntityManager;

import it.solvingteam.bibliotecaweb.dao.EntityManagerUtil;
import it.solvingteam.bibliotecaweb.dao.autore.AutoreDAO;
import it.solvingteam.bibliotecaweb.model.Autore;

public class AutoreServiceImpl implements AutoreService {
	private AutoreDAO autoreDAO;

	@Override
	public void setAutoreDAO(AutoreDAO autoreDAO) {
		this.autoreDAO = autoreDAO;
	}

	@Override
	public List<Autore> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			autoreDAO.setEntityManager(entityManager);
			return autoreDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Autore caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			autoreDAO.setEntityManager(entityManager);
			return autoreDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Autore autoreInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			autoreDAO.setEntityManager(entityManager);
			autoreDAO.update(autoreInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void inserisciNuovo(Autore autoreInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			autoreDAO.setEntityManager(entityManager);

			// controllo
			List<Autore> lista = autoreDAO.list();
			for (Autore a : lista) {
				if (a.equals(autoreInstance)) {
					throw new Exception("Autore già presente");
				}
			}

			autoreDAO.insert(autoreInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void rimuovi(Autore autoreInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			autoreDAO.setEntityManager(entityManager);

			// controllo
			Autore autoreEsistente = autoreDAO.get(autoreInstance.getId());
			
			if (!autoreEsistente.getListaLibri().isEmpty()) {
				throw new SQLIntegrityConstraintViolationException("Non puoi eliminare un autore se ha dei libri");
			}

			autoreDAO.delete(autoreInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Autore> cercaAutore(Autore autoreInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			autoreDAO.setEntityManager(entityManager);
			return autoreDAO.searchAutore(autoreInstance);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

}
