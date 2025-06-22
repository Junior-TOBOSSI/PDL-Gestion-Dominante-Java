package dao;

/**
 * Cette classe permet la gestion de la connection avec la table Dominante de la SGBD
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */


import model.*;

import java.sql.*;
import java.util.ArrayList;

public class DominanteDao extends ConnectionDao{
	
	/**
	 * Liste des dominantes
	 */
	private ArrayList<Dominante> listDominante;
	
	/**
	 * Liste des dominantes choisi
	 */
	private ArrayList<Dominante> listDominanteChoisi;
	
	/***
	 * Méthode d'instanciation d'une DominanteDao
	 */
	public DominanteDao() {
		super();
		listDominante = new ArrayList<>();
	}

	/**
	 * Cette méthode permet d'ajouter une dominante
	 * @param dom : La dominante à ajouter
	 * @return : 1 si la dominante est ajouter, 0 sinon
	 */
	public int add(Dominante dom) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO DOMINANTE(IDDOM, NOMLONG, SIGLE, PLACEMAX, PLACESAPPRENTIS) VALUES(?,?,?,?,?)");
			ps.setInt(1, dom.getId());
			ps.setString(2, dom.getNomLong());
			ps.setString(3, dom.getSigle());
			ps.setInt(4, dom.getPlaceMax());
			ps.setInt(5, dom.getPlacesApprentis());	
			
			returnValue = ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps != null) {
					ps = null;
				}	
			}catch(Exception ignore) {
			}
			try {
				if(con != null) {
					con = null;
				}
			}catch(Exception ignore) {
			}
		}
			
		return returnValue;
	}
	
	/**
	 * Cette méthode permet d'accéder à toutes les dominantes présentes dans la table Dominante de la BDD
	 * @return : la liste des dominantes
	 */
	public ArrayList<Dominante> getAll() {
		
		
		listDominante = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM DOMINANTE ORDER BY IDDOM");
			rs = ps.executeQuery();
			
			while(rs.next()){
				listDominante.add(new Dominante(rs.getInt("IDDOM"), rs.getString("NOMLONG"), rs.getString("SIGLE"),
													rs.getInt("PLACEMAX"), rs.getInt("PLACESAPPRENTIS")));
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
		
		return listDominante;
	}
	
	/**
	 * Cette méthode permet de modifier les informations d'une dominante
	 * @param dom : la dominante
	 * @return : 1 si la dominante est modifiée, 0 sinon
	 */
	public int update(Dominante dom) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE DOMINANTE SET NOMLONG = ?, SIGLE = ?, PLACEMAX = ?, PLACESAPPRENTIS = ? WHERE IDDOM = ?");
			ps.setString(1, dom.getNomLong());
			ps.setString(2, dom.getSigle());
			ps.setInt(3, dom.getPlaceMax());
			ps.setInt(4, dom.getPlacesApprentis());
			ps.setInt(5, dom.getId());
		
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
	
	/**
	 * Cette méthode permet d'accéder aux informations complètes des identifiants de dominantes choisies par un étudinant
	 * @param id : L'identifiant de l'étudiant
	 * @return : ArrayList<Dominante> la liste des dominantes
	 */
	public ArrayList<Dominante> getAllChoix(int id){
		listDominanteChoisi = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM DOMINANTE WHERE IDDOM IN ( SELECT IDDOM FROM CHOIX WHERE IDETUDIANT = ? )");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				listDominanteChoisi.add(new Dominante(rs.getInt("IDDOM"), rs.getString("NOMLONG"), rs.getString("SIGLE"),
													rs.getInt("PLACEMAX"), rs.getInt("PLACESAPPRENTIS")));
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
			
		return listDominanteChoisi;
	}
	
	/**
	 * Cette méthode permet d'accéder au nombre de place maximal d'une dominante
	 * @param id : l'identifiant de la dominante
	 * @return : le nombre de place maximal
	 */
	public int getPlaceMax(int id) {
		
		int placeMax = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT PLACEMAX FROM DOMINANTE WHERE IDDOM = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()){
				placeMax = rs.getInt("PLACEMAX");
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
			
		
		
		return placeMax;
	}
	
	
	/**
	 * Cette méthode permet d'accéder au nombre de place maximale des apprentis dans une dominante
	 * @param id : l'identifiant de la dominante
	 * @return : le nombre de place maximal pour les aprentis
	 */
    public int getPlaceApprentis(int id) {
		
		int placeApprentis = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT PLACESAPPRENTIS FROM DOMINANTE WHERE IDDOM = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()){
				placeApprentis = rs.getInt("PLACESAPPRENTIS");
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
			
		
		
		return placeApprentis;
	}
	
    /**
     * Cette méthode permet d'accéder aux informations d'une dominante
     * @param iddom : identifiant de la dominante
     * @return : Dominante
     */
    public Dominante get (int iddom) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Dominante returnValue = null;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM DOMINANTE WHERE IDDOM = ?");
			ps.setInt(1, iddom);
			
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				returnValue = new Dominante(rs.getInt("IDDOM"), rs.getString("NOMLONG"), rs.getString("SIGLE"),
						rs.getInt("PLACEMAX"), rs.getInt("PLACESAPPRENTIS"));
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs = null;
				}
			}catch(Exception ignore) {
			}
			try {
				if(ps != null) {
					ps = null;
				}	
			}catch(Exception ignore) {
			}
			try {
				if(con != null) {
					con = null;
				}
			}catch(Exception ignore) {
			}
		}
			
		return returnValue;
	}
    
    /**
     * Cette méthode supprime une dominante de la table dominante
     * @param idDominante l'id de la domiannte à supprimer
     * @return 0 si un élève à déjà fait de choix ou la suppression n'a pas abouti, 1 si tout s'est bien passé
     */
    public int delete(int idDominante) {
    	
    	int returnValue = 0;
    	Connection con = null;
    	PreparedStatement ps = null;
    	
    	// on vérifie que aucun élève n'a encore fait ses choix
    	ChoixDao connectDao = new ChoixDao();
    	int tailleListChoix = connectDao.count();
    	
    	if(tailleListChoix == 0) {
    		return 0;
    	}
    	
    	try {
    		con = DriverManager.getConnection(URL, LOGIN, PASS);
    		ps = con.prepareStatement("DELETE FROM DOMINANTE WHERE IDDOM = ?");
    		ps.setInt(1, idDominante);
    		
    		returnValue = ps.executeUpdate();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return returnValue;
    }
}
