package it.solvingteam.bibliotecaweb.service.libro;

import java.util.List;

import javax.persistence.EntityManager;

import it.solvingteam.bibliotecaweb.dao.EntityManagerUtil;
import it.solvingteam.bibliotecaweb.dao.libro.LibroDAO;
import it.solvingteam.bibliotecaweb.model.Libro;

public class LibroServiceImpl implements LibroService {
	private LibroDAO libroDAO;

	@Override
	public void setLibroDAO(LibroDAO libroDAO) {
		this.libroDAO = libroDAO;
	}

	@Override
	public List<Libro> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			libroDAO.setEntityManager(entityManager);
			return libroDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Libro caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			libroDAO.setEntityManager(entityManager);
			return libroDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Libro libroInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			libroDAO.setEntityManager(entityManager);
			libroDAO.update(libroInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void inserisciNuovo(Libro libroInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			libroDAO.setEntityManager(entityManager);

			// controllo
			List<Libro> lista = libroDAO.list();
			for (Libro l : lista) {
				if (l.equals(libroInstance)) {
					throw new Exception("Libro già presente");
				}
			}

			libroDAO.insert(libroInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void rimuovi(Libro libroInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			libroDAO.setEntityManager(entityManager);
			libroDAO.delete(libroInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Libro> cercaLibri(Libro libroInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			libroDAO.setEntityManager(entityManager);
			return libroDAO.searchLibri(libroInstance);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}
}
