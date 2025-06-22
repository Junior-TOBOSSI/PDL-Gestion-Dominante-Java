package model;

import java.sql.Date;

/**
* Cette classe permet la gestion d'instance de type Etape
* 
* Author Hilary BOCO - Gilbert TOBOSSI
* 
* Version 1.0
* 
*/
public class Etape {

	/**
	 * id represent l'id de l'étape
	 */
	private int id;
	
	/**
	 * dateDeDebut represente la date de debut de l'etape
	 */
	private Date dateDeDebut;
	

	
	/**
	 * nom represente le nom de l'etape
	 */
	private String nom;
	
	/**
	 * Constructor
	 * @param id
	 * @param dateDeDebut
	 * @param nom
	 */
	public Etape(int id, Date dateDeDebut, String nom) {
		this.id = id;
		this.dateDeDebut = dateDeDebut;
		this.nom = nom;
	}
	
	/**
	 * Cette methode permet d'accéder au 
	 * @return id de l'étape
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Cette methode permet d'accéder au 
	 * @return dateDeDebut de l'étape
	 */
	public Date getDateDeDebut() {
		return dateDeDebut;
	}
	
	/**
	 * Cette methode permet d'accéder au 
	 * @return nom de l'étape
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette methode permet de modifier la
	 * @param dateDeDebut
	 */
	public void setDateDeDebut(Date dateDeDebut) {
		this.dateDeDebut = dateDeDebut;
	}
	
	
	/**
	 * Cette methode permet de modifier le
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	

}
