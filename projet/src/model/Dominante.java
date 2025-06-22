package model;
/**
 * Cette classe permet la gestion d'instance de type dominante
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */
public class Dominante {

	/**
	 * id represente l'id de la dominante
	 */
	private int id;
	
	/**
	 * nomLong represente le nom complet de la dominante
	 */
	private String nomLong;
	
	/**
	 * sigle represente le sigle associe au nomLong de la dominante
	 */
	private String sigle;
	
	/**
	 * placeMax represente le nombre de places disponible dans la dominante
	 */
	private int placeMax;
	
	/**
	 * placesApprentis represente le nombre de places reserves aux apprentis
	 */
	private int placesApprentis;
	
	
	/**
	 * Constructor
	 * @param id
	 * @param nomLong
	 * @param sigle
	 * @param placeMax
	 * @param placesApprentis
	 */
	public Dominante(int id, String nomLong, String sigle, int placeMax, int placesApprentis) {
		this.id = id;
		this.nomLong = nomLong;
		this.sigle = sigle;
		this.placeMax = placeMax;
		this.placesApprentis = placesApprentis;	
	}
	
	/**
	 * Cette methode 
	 * @return l'id de la dominante
	 */
	public int getId() {
		return id;
	}
	
	
	/**
	 * Cette methode
	 * @return nomLong le nom complet de la dominante
	 */
	public String getNomLong() {
		return nomLong;
	}
	
	/**
	 * Cette methode 
	 * @return sigle le sigle associe au nom complet
	 */
	public String getSigle() {
		return sigle;
	}
	
	/**
	 * Cette methode 
	 * @return placeMax la capacite maximal de la dominante
	 */
	public int getPlaceMax() {
		return placeMax;
	}
	
	/**
	 * Cette methode 
	 * @return placesApprentis le nombre de places reserves aux apprentis dans la dominante
	 */
	public int getPlacesApprentis() {
		return placesApprentis;
	}
	
	/**
	 * Cette methode permet de modifier le nom complet d'une dominante
	 * @param aNomLong
	 */
	public void setNomLong(String aNomLong) {
		this.nomLong = aNomLong;
	}
	
	/**
	 * Cette methode permet de modifier le sigle associe à un nom long
	 * @param aSigle
	 */
	public void setSigle(String aSigle) {
		this.sigle = aSigle;
	}
	
	/**
	 * Cette methode permet de modifier le nombre de place dans une dominante
	 * @param aPlaceMax
	 */
	public void setPlaceMax(int aPlaceMax) {
		this.placeMax = aPlaceMax;
	}
	
	/**
	 * Cette methode permet de modifier le nombre de places reserves aux apprentis dans une dominante
	 * @param aPlacesApprentis
	 */
	public void setPlacesApprentis(int aPlacesApprentis) {
		this.placesApprentis = aPlacesApprentis;
	}

	@Override
	public String toString() {
		return "Dominante [id=" + id + ", nomLong=" + nomLong + ", sigle=" + sigle + ", placeMax=" + placeMax
				+ ", placesApprentis=" + placesApprentis + "]";
	}
	
}
