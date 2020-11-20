package it.solvingteam.bibliotecaweb;

import it.solvingteam.bibliotecaweb.dao.EntityManagerUtil;

public class MainTest
 {

	public static void main(String[] args) {

/*		
  		AutoreService autoreService = MyServiceFactory.getAutoreServiceInstance();
		LibroService libroService = MyServiceFactory.getLibroServiceInstance();
		RuoloService ruoloService = MyServiceFactory.getRuoloServiceInstance();
		UtenteService utenteService = MyServiceFactory.getUtenteServiceInstance();
*/

		try {
/*
			Autore a = new Autore();
			Autore b = new Autore();
			Libro l = new Libro();
			Ruolo r = new Ruolo();
			Utente u = new Utente();
*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}

}
