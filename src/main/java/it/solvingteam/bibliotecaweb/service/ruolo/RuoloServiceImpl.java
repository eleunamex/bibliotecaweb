package it.solvingteam.bibliotecaweb.service.ruolo;

import java.util.List;

import javax.persistence.EntityManager;

import it.solvingteam.bibliotecaweb.dao.EntityManagerUtil;
import it.solvingteam.bibliotecaweb.dao.ruolo.RuoloDAO;
import it.solvingteam.bibliotecaweb.model.Ruolo;

public class RuoloServiceImpl implements RuoloService{
	private RuoloDAO ruoloDAO;

	@Override
	public void setRuoloDAO(RuoloDAO ruoloDAO) {
	this.ruoloDAO=ruoloDAO;
	}

	@Override
	public List<Ruolo> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			ruoloDAO.setEntityManager(entityManager);
			return ruoloDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Ruolo caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			ruoloDAO.setEntityManager(entityManager);
			return ruoloDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Ruolo ruoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			ruoloDAO.setEntityManager(entityManager);
			ruoloDAO.update(ruoloInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			ruoloDAO.setEntityManager(entityManager);

			// controllo
			List<Ruolo> lista = ruoloDAO.list();
			for (Ruolo r : lista) {
				if (r.equals(ruoloInstance)) {
					throw new Exception("Ruolo già presente");
				}
			}

			ruoloDAO.insert(ruoloInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void rimuovi(Ruolo ruoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			ruoloDAO.setEntityManager(entityManager);

			///////////////////////
			
			ruoloDAO.delete(ruoloInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

}
