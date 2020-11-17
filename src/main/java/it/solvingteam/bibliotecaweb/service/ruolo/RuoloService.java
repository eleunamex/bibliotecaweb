package it.solvingteam.bibliotecaweb.service.ruolo;

import java.util.List;

import it.solvingteam.bibliotecaweb.dao.ruolo.RuoloDAO;
import it.solvingteam.bibliotecaweb.model.Ruolo;

public interface RuoloService {
	
	public List<Ruolo> listAll() throws Exception;

	public Ruolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ruolo ruoloInstance) throws Exception;

	public void inserisciNuovo(Ruolo ruoloInstance) throws Exception;

	public void rimuovi(Ruolo ruoloInstance) throws Exception;
	
	public void setRuoloDAO(RuoloDAO ruoloDAO);
}
	