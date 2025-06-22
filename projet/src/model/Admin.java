package model;

/**
 * Cette classe permet la gestion d'instance de type Admin
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */
public class Admin {
	
	/**
	 * id de l'admin
	 */
	private int id;
	
	/**
	 * nom de l'admin
	 */
	private String nom;
	
	/**
	 * prenom de l'admin
	 */
	private String prenom;
	
	/**
	 * mot de passe de l'admin
	 */
	private String motDePasse;
	
	public Admin(int id, String motDePasse) {
		this.id = id;
		this.motDePasse = motDePasse;
	}
	
	public Admin(int id, String nom, String prenom, String motDePasse) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
	}
	
	public Admin() {
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
	 * @return nom de l'admin
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette methode 
	 * @return prenom de l'admin
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Cette methode 
	 * @return age de l'admin
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	
	

}
