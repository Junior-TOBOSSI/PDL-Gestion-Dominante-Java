package model;

import java.util.Date;

/**
 * Cette classe permet la gestion d'instance de type Admin
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */
public class Etudiant {
	
	/**
	 * id de l'étudiant
	 */
	private int id;
	
	/**
	 * nom de l'étudiant
	 */
	private String nom;
	
	/**
	 * prenom de l'admin
	 */
	private String prenom;
	

	/**
	 * classement de l'étudiant
	 */
	private int classement;

	/**
	 * date de naissance de l'étudiant
	 */
	private Date dateDeNaissance;

	/**
	 * promotion de l'étudiant
	 */
	private int promotion;
	

	/**
	 * filiere de l'étudiant
	 */
	private int filiere;
	
	/**
	 * mot de passe de l'étudiant
	 */
	private String motDePasse;
	
	private int dominanteFinale;
	
	
	public int getDominanteFinale() {
		return dominanteFinale;
	}

	public void setDominanteFinale(int dominanteFinale) {
		this.dominanteFinale = dominanteFinale;
	}

	public Etudiant(int id, String motDePasse) {
		this.id = id;
		this.motDePasse = motDePasse;
	}
	
	public Etudiant(int id, String nom, String prenom, Date dateDeNaissance,  int classement, String motDePasse, int promotion, int filiere, int dominanteFinale) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.motDePasse = motDePasse;
		this.promotion = promotion;
		this.classement = classement;
		this.filiere = filiere;
		this.dominanteFinale = dominanteFinale;
	}
	public Etudiant(int id, String nom, String prenom, Date dateDeNaissance,  int classement, String motDePasse, int promotion, int filiere) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.motDePasse = motDePasse;
		this.promotion = promotion;
		this.classement = classement;
		this.filiere = filiere;
	}
	
	public Etudiant(int id, String nom, String prenom, Date dateDeNaissance,  int classement, int promotion, int filiere) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.promotion = promotion;
		this.classement = classement;
		this.filiere = filiere;
	}
	
	public Etudiant(int id, String nom, String prenom, Date dateDeNaissance,  int classement) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.classement = classement;
	}
	
	
	public Etudiant(String nouvNom, String nouvPrenom, Date dateDeNaissance, int nouvClassement, String nouvMdp) {
		// TODO Auto-generated constructor stub
		this.nom = nouvNom;
		this.prenom = nouvPrenom;
		this.dateDeNaissance = dateDeNaissance;
		this.motDePasse = nouvMdp;
		this.classement = nouvClassement;

	}

	public Etudiant() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Cette methode 
	 * @return id de l'admin
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Cette methode 
	 * @return nom de l'étudiant
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette methode 
	 * @return prenom de l'étudiant
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Cette methode 
	 * @return age de l'étudiant
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	
	/**
	 * Cette méthode retourne 
	 * @return le classement de l'étudiant
	 */
	public int getClassement() {
		return classement;
	}
	
	
	/**
	 * Cete méthode retourne
	 * @return la promotion de l'étudiant
	 */
	public int getPromotion() {
		return promotion;
	}
	
	/**
	 * Cette méthode permet de retourner
	 * @return la date de naissance de l'étudiant
	 */
	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}
	
	/**
	 * Cette méthode permet de retourner 
	 * @return la filère de l'étudiant
	 */
	public int getfiliere() {
		return filiere;
	}

	
	/**
	 * Cette méthode permet de modifier 
	 * @param nom de l'étudiant
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Cette méthode permet de modifier
	 * @param prenom d'un étudiant
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * Cette méthode permet de modifier
	 * @param date de naissance d'un étudiant
	 */
	public void setDateDeNaissance(Date date) {
		this.dateDeNaissance = date;
	}
	
	/**
	 * Cette méthode permet de modifier
	 * @param classement d'un étudiant
	 */
	public void setClassement(int classement) {
		this.classement = classement;
	}
	
	/**
	 * Cette méthode permet de modifier
	 * @param promotion d'un étudiant
	 */
	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}
	
	/**
	 * Cette méthode permet de modifier 
	 * @param filiere d'un étudiant
	 */
	public void setFiliere(int filiere) {
		this.filiere = filiere;
	}
}
