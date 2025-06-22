package dao;

/**
 * Cette classe permet de lancer le traitement des étapes 
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */

import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class TraitementDao extends ConnectionDao{
	
	/***
	 * Instanciation de EtudiantDao
	 */
	private EtudiantDao conEtud = new EtudiantDao();
	
	/**
	 * Instanciation de ChoixDao
	 */
	private ChoixDao conChoix = new ChoixDao();
	
	/**
	 * Instanciation de DominanteDao
	 */
	private DominanteDao conDom = new DominanteDao();
	
	
	
	/***
	 * Constructeur
	 */
	public TraitementDao() {
		super();
	}
	
	/**
	 * Cette méthode permet de mettre à jour les dates des étapes
	 * @param etape : l'étape à mettre à jour
	 * @return 1 si l'étape à pu être mise à jour, 0 sinon
	 */
	public int updateDates(Etape etape) {
		
		int returnValue = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE ETAPE SET DATEDEDEBUT = ? WHERE IDETAPE = ?");
			
			java.sql.Date DATEDEDEBUT = new java.sql.Date(etape.getDateDeDebut().getTime());
			
			ps.setDate(1, DATEDEDEBUT);
			ps.setInt(2, etape.getId());
			
			returnValue = ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) {
					con.close();
				}
			}catch(Exception ignore) {
			}
			try {
				if(ps != null) {
					ps.close();
				}
			}catch(Exception ignore) {
			}
		}
		
		
		return returnValue;
	}
	
	/**
	 * Cette méthode permet d'accéder à toute les étapes de la table Etape de la base de données
	 * @return la liste des étapes
	 */
	public ArrayList<Etape> getAll(){
		
		ArrayList<Etape> listEtapes = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ETAPE");
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				listEtapes.add(new Etape(rs.getInt("IDETAPE"),
											rs.getDate("DATEDEDEBUT"),
											rs.getString("NOMETAPE")));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		
		return listEtapes;
	}
	
	/**
	 * Cette méthode permet de vérifier si l'étudiant peut avoir accès à la plateforme au vu de la date
	 * @param etud : l'étudiant connecté
	 * @return : True si l'étudiant à accès, 0 sinon
	 */
	public boolean isAccessible(Etudiant etud) {
		
		LocalDate today = LocalDate.now();
		ArrayList<Etape> listEtapes = getAll();

		if(etud.getfiliere() == 1) {
			// conversion de la date réceuillie de sql en local date 
			// on convertit la date sql en instant
			// puis on le convertie en localdate
			LocalDate dateDebut = listEtapes.get(4).getDateDeDebut().toLocalDate();
			LocalDate dateFin = listEtapes.get(5).getDateDeDebut().toLocalDate();
			
			if(today.isAfter(dateDebut) && today.isBefore(dateFin)){
				return true;
			}else if (today.equals(dateDebut) || today.equals(dateFin)) {
				return true;
			}
	
		}else if(etud.getfiliere() == 2) {
			
		// conversion de la date réceuillie de sql en local date 
		// on convertit la date sql en instant
		// puis on le convertie en localdate
			
			
		LocalDate dateDebut = listEtapes.get(1).getDateDeDebut().toLocalDate();
		LocalDate dateFin = listEtapes.get(2).getDateDeDebut().toLocalDate();

		
			if(today.isAfter(dateDebut) && today.isBefore(dateFin)){
				return true;
			}else if (today.equals(dateDebut) || today.equals(dateFin)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Cette méthode permet de lancer la répartition automatique des étudiants dans les dominantes
	 * @param idFiliere : la filiere pour laquelle on lance le traite
	 * @return 1 si le traitement est arrivé à terme, 0 sinon
	 */
	public boolean lancerTraitement(int idFiliere) {
		
		ArrayList<Etudiant> listEtudiant = new ArrayList<>(); 
		ArrayList<Integer> listIdDomChoisi;
		boolean isChoix = false;
		
		if(idFiliere == 1) {
			// on sélectione tous les étudiants FISE
			listEtudiant = conEtud.getAllFise(); 
		}
		else if (idFiliere == 2) {
			// on sélectione tous les étudiants FISA
			listEtudiant = conEtud.getAllFisa(); 
		}
		

		
		
		// On itère sur chaque étudiant
		for(Etudiant stu : listEtudiant) {
			isChoix = false;
			
			// on récupère les choix de l'étudiant
			listIdDomChoisi = conChoix.getChoix(stu.getId());
			
			System.out.print(stu.getNom() + " Rang : " + stu.getClassement() + " [Choix : " );
			
			for(int choix : listIdDomChoisi) {
				System.out.print(choix + " ");
			}
			System.out.print("] (");
					

			// on itère sur ses choix
			for(int idDomChoisi : listIdDomChoisi) {
				
				
				
				// on regarde si ce choix est valable sinon on passe au suivant (s'il reste de la place dans la dominante)
				if(getPlaceRestante(idFiliere, idDomChoisi) == 0 && !isChoix) {
					
					continue;
				}
				else if(!isChoix){
					// une fois qu'on a trouvé le choix valable, on l'attribut à l'étudiant
				
					isChoix = true;
					
					System.out.print(" Dominante Finale : " + idDomChoisi + " ");
					int confirm = conEtud.updateDom(idDomChoisi, stu.getId());
					if(confirm == 1) {
						continue;
					}else {
						return false;
					}
				}
				
				
			}	
			System.out.println(")");
			
		}
		
		
		return true;
	}
	
	/**
	 * Cette méthode permet de déterminer le nombre de places restantes dans une dominante pour une filiere
	 * @param idFiliere : filiere
	 * @param idDom : dominante
	 * @return le nombre de place restante
	 */
	public int getPlaceRestante(int idFiliere, int idDom) {
		int nbrePlaceRestant = 0;
		
		if(idFiliere == 1) {
			nbrePlaceRestant = conDom.getPlaceMax(idDom) - getPlacePrise(idDom);	
		}else if (idFiliere == 2) {
			nbrePlaceRestant = conDom.getPlaceApprentis(idDom) - getPlacePrise(idDom);
		}
		
		return nbrePlaceRestant;
	}
	
	/**
	 * Cette méthode permet de déterminer le nombre de places prises pour une filière
	 * @param idDom : la dominante
	 * @return : le nombre de places prises
	 */
	public int getPlacePrise(int idDom) {
		
		int placePrise = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT COUNT(*) FROM ETUDIANT WHERE IDDOMINANTE = ?");
			ps.setInt(1, idDom);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				placePrise = rs.getInt(1);
			}
			
			
		}
		catch(Exception e) {
			if(e.getMessage().contains("ORA-00001"))
				System.out.println("La dominante existe déjà dans la base de données");
			else
				e.printStackTrace();
		}
		finally {
			
			try {
				if(rs != null) {
					rs.close();
				}	
			}catch(Exception ignore) {
			}
	
			try {
				if(ps != null) {
					ps.close();
				}	
			}catch(Exception ignore) {
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(Exception ignore) {
			}
		}
			
		return placePrise;
	}

}
