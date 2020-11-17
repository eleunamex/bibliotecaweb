package it.solvingteam.bibliotecaweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_libro")
	private Long id;
	
	@Column(name = "titolo")
	private String titolo;
	
	@Column(name = "genere")
	private String genere;
	
	@Column(name = "trama")
	private String trama;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autore_fk")
	private Autore autore;
	
	public Libro() {}
	
	public Libro(String titolo, String genere, String trama, Autore autore) {
		this.titolo = titolo;
		this.genere = genere;
		this.trama = trama;
		this.autore = autore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getTrama() {
		return trama;
	}

	public void setTrama(String trama) {
		this.trama = trama;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	@Override
	public String toString() {
		return "Libro [id: " + id + ", titolo: " + titolo + ", genere: " + genere + ", trama: " + trama + ", autore: "
				+autore + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autore == null) ? 0 : autore.hashCode());
		result = prime * result + ((genere == null) ? 0 : genere.hashCode());
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
		result = prime * result + ((trama == null) ? 0 : trama.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (autore == null) {
			if (other.autore != null)
				return false;
		} else if (!autore.equals(other.autore))
			return false;
		if (genere == null) {
			if (other.genere != null)
				return false;
		} else if (!genere.equals(other.genere))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		if (trama == null) {
			if (other.trama != null)
				return false;
		} else if (!trama.equals(other.trama))
			return false;
		return true;
	}
	
}
