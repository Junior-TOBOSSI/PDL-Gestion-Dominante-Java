package dao;

/**
 * Cette classe permet la gestion de la connection avec la table Choix de la BD
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */

import java.sql.*;
import java.util.ArrayList;

import model.Dominante;
import model.Etudiant;



public class ChoixDao extends ConnectionDao {
	
	/**
	 * Méthode d'instanciation d'une ChoixDao
	 */
	public ChoixDao() {
		super();
	}
	
	/**
	 * Cette méthode permet d'ajouter le choix d'un étudiant
	 * @param idDominante : la liste id de dominantes choisies
	 * @param idEtudiant : l'id de l'étudiant ayant fait les choix
	 * @param idChoix : l'id du premier choix à insérer
	 * @return : le nombre de choix insérer dans la base de données
	 */
	public int add(int[] idDominante, int idEtudiant, int idChoix) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int insertion = 0;
		int returnValue = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			while(insertion != idDominante.length) {
				ps = con.prepareStatement("INSERT INTO CHOIX(NUMCHOIX, IDDOM, IDETUDIANT) VALUES(?, ?, ?)");
				
				ps.setInt(1, idChoix);
				ps.setInt(2, idDominante[insertion]);
				ps.setInt(3, idEtudiant);
				
				returnValue += ps.executeUpdate();
				insertion++;
				idChoix += 1;
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(con != null) {
					con.close();
				}
			}catch(Exception ignore){	
			}
			
			try {
				if(ps != null) {
					ps.close();
				}
			}catch(Exception ignore){	
			}
		}
		
		return returnValue;
	}

	/***
	 * Cette méthode permet de compter le nombre de choix présent dans la base de données
	 * @return : le nombre de choix présent dans la BDD
	 */
	public int count() {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM CHOIX ORDER BY NUMCHOIX DESC");
			
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("NUMCHOIX");
			}	
		}
		catch(Exception e) {
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
			
		return count;
	}
	
	/**
	 * Cette méthode permet de savoir si un étudiant a déjà fait ses choix
	 * @param id : l'identifiant de l'étudiant
	 * @return : True si l'étudiant est présent dans la table choix, 0 sinon
	 */
	public boolean isChoixMade(int id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM CHOIX WHERE IDETUDIANT = ?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				
				returnValue = true;
			}
		}
		catch(Exception e) {
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
			
		return returnValue;
	}
	
	/**
	 * Cette méthode permet de récupérer tout les choix effectués par un étudiant
	 * @param id : l'identifiant de l'étudiant dont on veut connaître le choix
	 * @return : la liste des identifiants de dominantes choisis
	 */
	public ArrayList<Integer> getChoix(int id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> listChoix = new ArrayList<>();

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM CHOIX WHERE IDETUDIANT = ? ORDER BY NUMCHOIX ASC");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				listChoix.add(rs.getInt("IDDOM"));	
			}
		}
		catch(Exception e) {
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
			
		return listChoix;
	}
	
	/**
	 * Cette méthode permet de récupérer les étudiants ayant choisi une dominante
	 * @param idDom : l'identification de la dominante 
	 * @return :  la liste des identifiants étudiants
	 */
	public ArrayList<Integer> etudiantsParDominantes(int idDom) {
		
		ArrayList<Integer> listIdEtudiant = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM CHOIX WHERE IDDOM = ?");
			ps.setInt(1, idDom);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				listIdEtudiant.add(rs.getInt("IDETUDIANT"));
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
			
		return listIdEtudiant;
	}
	
	/**
	 * Cette méthode permet de connaitre la position d'une dominante dans les choix d'un étudiant
	 * @param idEtudiant : l'identifiant de la dominante
	 * @param iddom : l'identifiant de l'étudinat
	 * @return :  la position de la dominante
	 */
	public int indexChoixParEtudiant(int idEtudiant, int iddom) {
		
		int index = 0;
	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		boolean isFound = false;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM CHOIX WHERE IDETUDIANT = ?");
			ps.setInt(1, idEtudiant);
			
			rs = ps.executeQuery();
			
			while(rs.next() && !isFound){
				if(rs.getInt("IDDOM") == iddom) {
					index++;
					isFound = true;
				}else {
					index++;
					continue;
				}
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
		
		return index;
	}
	
	/**
	 * Cette méthode permet de supprimer tous les choix présents dans la table choix
	 * @return : le nombre de choix supprimés
	 */
	public int deleteAll() {
		
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE CHOIX WHERE IDETUDIANT != ?");
			ps.setInt(1, 0);
		
			returnValue = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
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

}
