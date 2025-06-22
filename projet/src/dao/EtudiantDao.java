package dao;

/**
 * Cette classe permet la gestion de la connection avec la table Filiere de la SGBD
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */

import model.*;
import java.sql.Date;

import java.sql.*;
import java.util.ArrayList;

public class EtudiantDao extends ConnectionDao{
	
	/**
	 * Cette méthode permet l'instanciation de EtudiantDao
	 */
	public EtudiantDao() {
		super();
	}

	/**
	 * Cette méthode vérifie si l'utilisateur connecté est un étudiant
	 * @param etu : l'étudiant connecté
	 * @return : True si l'utilisateur est un étudiant, False sinon
	 */
	public boolean isEtudiant (Etudiant etu) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ETUDIANT WHERE IDETUDIANT = ?");
			ps.setInt(1, etu.getId());
			
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				returnValue = (rs.getString("MOTDEPASSE").equals(etu.getMotDePasse()));
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
	 * Cette méthode permet d'accéder à l'ensemble des informations de la table Etudiant
	 * @return la table Etudiant
	 */
	public ArrayList<Etudiant> getAll() {
			
			
		    ArrayList<Etudiant> listEtudiant = new ArrayList<>();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				con = DriverManager.getConnection(URL, LOGIN, PASS);
				ps = con.prepareStatement("SELECT * FROM ETUDIANT ORDER BY IDETUDIANT");
				rs = ps.executeQuery();
				
				while(rs.next()){
					listEtudiant.add(new Etudiant(rs.getInt("IDETUDIANT"), rs.getString("NOM"), rs.getString("PRENOM"),
														rs.getDate("DATENAISSANCE"), rs.getInt("CLASSEMENT"), null, rs.getInt("IDPROMOTION"), rs.getInt("IDFILIERE")));
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
				
			return listEtudiant;
		}
		
	
	/**
	 * Cette méthode permet de modifier les informations d'un étudiant
	 * @param etu : l'étudiant à modifier
	 * @return : 1 si la modification a eu lieu, 0 sinon
	 */
	public int update(Etudiant etu) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE ETUDIANT SET NOM = ?, PRENOM = ?, DATENAISSANCE = ?, CLASSEMENT = ? , IDPROMOTION = ? , IDFILIERE = ? WHERE IDETUDIANT = ?");
			ps.setString(1, etu.getNom());
			ps.setString(2, etu.getPrenom());
			
			
			java.sql.Date dateNaissance = new java.sql.Date(etu.getDateDeNaissance().getTime());
			ps.setDate(3, dateNaissance);
			ps.setInt(4, etu.getClassement ());
			ps.setInt(5, etu.getPromotion ());
			ps.setInt(6, etu.getfiliere());
			ps.setInt(7, etu.getId());
		
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
	 * Cette méthode permet de supprimer un étudiant de la table Etudiant
	 * @param id : l'identifiant de l'étudiant à supprimer
	 * @return : 1 si la suppression a eu lieu, 0 sinon
	 */
	public int delete(int id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE ETUDIANT WHERE IDETUDIANT = ?");
			ps.setInt(1, id);
		
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
		
	/***
	 * Cette méthode permet d'accéder aux informations d'un étudiant
	 * @param id : l'identifiant de l'étudiant à supprimer
	 * @return : l'étudiant 
	 */
	public Etudiant get (int id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Etudiant returnValue = null;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ETUDIANT WHERE IDETUDIANT = ?");
			ps.setInt(1, id);
			
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				returnValue = new Etudiant(rs.getInt("IDETUDIANT"), rs.getString("NOM"), rs.getString("PRENOM"),
						rs.getDate("DATENAISSANCE"), rs.getInt("CLASSEMENT"), rs.getString("MOTDEPASSE"), 
						rs.getInt("IDPROMOTION"), rs.getInt("IDFILIERE"), rs.getInt("IDDOMINANTE"));
				
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
	 * Cette méthode permet de modifier la dominante d'un étudiant
	 * @param idDom : l'identifiant de la dominante
	 * @param idEtud : l'identifiant de l'étudiant
	 * @return : 1 si la dominante a pu être modifier, 0 sinon
	 */
	public int updateDom(int idDom, int idEtud) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE ETUDIANT SET IDDOMINANTE = ? WHERE IDETUDIANT = ?");
			ps.setInt(1, idDom);
			ps.setInt(2, idEtud);

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
	 * Cette méthode permet d'accéder à tous les étudiants FISE
	 * @return : la liste des étudiants FISE
	 */
	public ArrayList<Etudiant> getAllFise(){
		
		ArrayList<Etudiant> listEtudiantFise = new ArrayList<>();
		

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ETUDIANT WHERE IDFILIERE = ?  ORDER BY CLASSEMENT");
			ps.setInt(1, 1);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				listEtudiantFise.add(new Etudiant(rs.getInt("IDETUDIANT"), rs.getString("NOM"), rs.getString("PRENOM"),
													rs.getDate("DATENAISSANCE"), rs.getInt("CLASSEMENT"), null, rs.getInt("IDPROMOTION"), rs.getInt("IDFILIERE")));
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
		
		return listEtudiantFise;
	}
		
	/**
	 * Cette méthode permet d'accéder à tous les étudiants apprentis
	 * @return : la liste des etudiants FISA
	 */
	public ArrayList<Etudiant> getAllFisa(){
		
		ArrayList<Etudiant> listEtudiantFisa = new ArrayList<>();
		
	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ETUDIANT WHERE IDFILIERE = ?  ORDER BY CLASSEMENT ASC");
			ps.setInt(1, 2);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				listEtudiantFisa.add(new Etudiant(rs.getInt("IDETUDIANT"), rs.getString("NOM"), rs.getString("PRENOM"),
													rs.getDate("DATENAISSANCE"), rs.getInt("CLASSEMENT"), null, rs.getInt("IDPROMOTION"), rs.getInt("IDFILIERE")));
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
			
		return listEtudiantFisa;
	}
	
	/***
	 * Cette méthode permet d'ajouter un étudiant
	 * @param etud : l'étudiant à ajouter
	 * @return : 1 si l'étudiant a pu être ajouter, 0 sinon
	 */
	public int add(Etudiant etud) {
		
		int returnValue = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO ETUDIANT (IDETUDIANT, NOM, PRENOM, CLASSEMENT, MOTDEPASSE, IDPROMOTION, IDFILIERE, DATENAISSANCE) VALUES(?,?,?,?,?,?,?,?)");
			ps.setInt(1, etud.getId());
			ps.setString(2, etud.getNom());
			ps.setString(3, etud.getPrenom());
			ps.setInt(4, etud.getClassement());
			ps.setString(5, "AAAAA");
			ps.setInt(6, etud.getPromotion());
			ps.setInt(7, etud.getfiliere());
			
	        java.sql.Date ddn = new java.sql.Date(etud.getDateDeNaissance().getTime());
			ps.setDate(8, ddn);
			
			returnValue = ps.executeUpdate();
			
		}catch(Exception e) {
			
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
	 * Cette méthode permet d'accéder pour une filière à tous les étudiants sans dominantes
	 * @param idFiliere  l'identifiant de la filière
	 * @return la liste des étudiants concernés
	 */
	public ArrayList<Etudiant> getAllWhithoutDom(int idFiliere) {
		
		
	    ArrayList<Etudiant> listEtudiant = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ETUDIANT WHERE IDDOMINANTE IS NULL AND IDFILIERE = ?");
			ps.setInt(1, idFiliere);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				listEtudiant.add(new Etudiant(rs.getInt("IDETUDIANT"), rs.getString("NOM"), rs.getString("PRENOM"),
													rs.getDate("DATENAISSANCE"), rs.getInt("CLASSEMENT"), null, rs.getInt("IDPROMOTION"), rs.getInt("IDFILIERE")));
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
			
		return listEtudiant;
	}
	
		
}