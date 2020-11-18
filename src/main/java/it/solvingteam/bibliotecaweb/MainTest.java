package it.solvingteam.bibliotecaweb;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import it.solvingteam.bibliotecaweb.dao.EntityManagerUtil;
import it.solvingteam.bibliotecaweb.model.Autore;
import it.solvingteam.bibliotecaweb.model.Libro;
import it.solvingteam.bibliotecaweb.model.Ruolo;
import it.solvingteam.bibliotecaweb.model.Utente;
import it.solvingteam.bibliotecaweb.service.autore.AutoreService;
import it.solvingteam.bibliotecaweb.service.libro.LibroService;
import it.solvingteam.bibliotecaweb.service.ruolo.RuoloService;
import it.solvingteam.bibliotecaweb.service.utente.UtenteService;
import net.bytebuddy.asm.Advice.Local;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

public class MainTest
 {

	public static void main(String[] args) {

		AutoreService autoreService = MyServiceFactory.getAutoreServiceInstance();
		LibroService libroService = MyServiceFactory.getLibroServiceInstance();
		RuoloService ruoloService = MyServiceFactory.getRuoloServiceInstance();
		UtenteService utenteService = MyServiceFactory.getUtenteServiceInstance();

		try {

			Autore a = new Autore();
			Autore b = new Autore();
			Libro l = new Libro();
			Ruolo r = new Ruolo();
			Utente u = new Utente();

			
			a= autoreService.caricaSingoloElemento(12l);
			System.out.println(a);
//			autoreService.rimuovi(a);
			
			
			
			
//			LocalDate data = LocalDate.of(2020, 1, 10);
//			
//			a.setDataNascita(data);
//			a.setCognome("prova");
//			a.setNome("prova");
//
//		autoreService.inserisciNuovo(a);
		
//		l=libroService.caricaSingoloElemento(4l);
//		a=autoreService.caricaSingoloElemento(8l);
//		l.setAutore(a);
//		libroService.aggiorna(l);
			
		
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}

}
