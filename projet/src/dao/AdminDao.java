package dao;

/**
 * Cette classe permet la gestion de la connection avec la table Admin de la BD
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */         



import model.*;

import java.sql.*;


public class AdminDao extends ConnectionDao{
	
	/**
	 * Méthode d'instanciation d'une AdminDao
	 */
	public AdminDao() {
		super();
	}

	/**
	 * Cette méthode permet de vérifier si l'utilisateur connecté est un administrateur
	 * @param ad : l'administrateur à vérifier
	 * @return True s'il s'agit d'un admin et False sinon
	 */
	public boolean isAdmin (Admin ad) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ADMIN WHERE IDADMIN = ?");
			ps.setInt(1, ad.getId());
			
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				returnValue = (rs.getString("MOTDEPASSE").equals(ad.getMotDePasse()));
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
	 * Cette méthode permet d'accéder aux informations d'un administrateur
	 * @param id : l'identifiant de l'administrateur
	 * @return : l'administrateur
	 */
	public Admin get (int id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Admin returnValue = new Admin();
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ADMIN WHERE IDADMIN = ?");
			ps.setInt(1, id);
			
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				returnValue = new Admin(rs.getInt("IDADMIN"), rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("MOTDEPASSE"));
			
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
	 * Cette méthode permet de modifier les informations d'un administrateur
	 * @param admin : l'administrateur dont on veut modifier les informations
	 * @return : 1 si les modifications sont effectuées 0 sinon
	 */
	public int update(Admin admin) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE ADMIN SET NOM = ?, PRENOM = ?, MOTDEPASSE = ? WHERE IDADMIN = ?");
			ps.setString(1, admin.getNom());
			ps.setString(2, admin.getPrenom());
			ps.setString(3, admin.getMotDePasse());
			ps.setInt(4, admin.getId());
		
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
