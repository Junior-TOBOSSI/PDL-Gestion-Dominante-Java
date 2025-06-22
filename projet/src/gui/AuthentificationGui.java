/**
 * Cette classe permet la gestion du panel d'authentification des utilisateur
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */

package gui;
import model.*;
import dao.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class AuthentificationGui extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Cet attribut permet d'accéder au type d'utilisateur connecté
	 * 1 correspond à un administrateur
	 * 2 correspond à un étudiant
	 */
	private int balise;
	
	/***
	 * Cet attribut permet de rediger
	 * l'utlisateur vers son interface si la connection est réussie
	 */
	private JButton btnConnexion;
	
	/**
	 * Cet attribut permet de stocker le profil de l'administrateur si c'est lui qui se connecte
	 */
	private Admin profilAdmin;
	
	/***
	 * Cet attribut permet de stocker le profil de l'étudiant si c'est lui qui se connecte
	 */
	private Etudiant profilEtu;
	
	
	/**
	 * Create the panel.
	 */
	public AuthentificationGui() {
		
	
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 63, 99));
		panel.setBounds(0, 40, 1015, 482);
		add(panel);
		panel.setLayout(null);
		
		// on créee les labels pour les différentes zones d'écritures
		
		JTextField caseId = new JTextField();
		caseId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		caseId.setBounds(370, 202, 303, 35);
		panel.add(caseId);
		caseId.setColumns(10);
		
		JPasswordField casePassword = new JPasswordField();
		casePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		casePassword.setBounds(370, 279, 303, 35);
		panel.add(casePassword);
		
		// on initialise le bouton de connexion
		btnConnexion = new JButton("Connexion");
		
		
		btnConnexion.setBackground(new Color(192, 63, 99));
		
		btnConnexion.setBounds(432, 347, 121, 35);
		panel.add(btnConnexion);
		
		
		JLabel labelIdentifiant = new JLabel("Identifiant");
		labelIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelIdentifiant.setBounds(370, 171, 262, 20);
		panel.add(labelIdentifiant);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMotDePasse.setBounds(370, 248, 262, 20);
		panel.add(lblMotDePasse);
		
		JLabel labelEnTete = new JLabel("          Authentification");
		labelEnTete.setBounds(272, 63, 437, 51);
		labelEnTete.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(labelEnTete);
		labelEnTete.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		// on instancie le pied de panel de l'interface
		JPanel piedPanel = new JPanel();
		piedPanel.setBackground(new Color(0, 0, 0));
		piedPanel.setBounds(0, 513, 1015, 92);
		add(piedPanel);
		piedPanel.setLayout(null);
		
		
		JLabel labelPied = new JLabel("Ecole Supérieure d’Ingénieurs en  Génie Electrique ESIGELEC ");
		labelPied.setBounds(74, 36, 764, 27);
		labelPied.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelPied.setForeground(new Color(255, 128, 192));
		piedPanel.add(labelPied);
		
		JLabel conteneurLogo = new JLabel("New label");
		conteneurLogo.setIcon(new ImageIcon(AuthentificationGui.class.getResource("/data/logo_esigelec.png")));
		conteneurLogo.setBounds(790, 23, 104, 60);
		piedPanel.add(conteneurLogo);
		
		// on instancie l'entête de panel de l'interface
		JPanel tetePanel = new JPanel();
		tetePanel.setBounds(0, 0, 1015, 56);
		add(tetePanel);
		tetePanel.setLayout(null);
		tetePanel.setBackground(Color.BLACK);
		
		// on paramètre le bouton de connexion de renvoyer les utilisateurs vers leurs correspondantes interfaces
		btnConnexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				try {
					int utilisateurEnter = Integer.parseInt(caseId.getText().strip());
					char[] motDePasseEnter = casePassword.getPassword();
					
					
					String motDePasseEnter_ = String.valueOf(motDePasseEnter, 0, motDePasseEnter.length);
					
					Admin ad = new Admin(utilisateurEnter, motDePasseEnter_);
					AdminDao adao = new AdminDao();
					
					// on vérifie si l'utilisateur est un administrateur
					if(adao.isAdmin(ad)) {
						balise = 1;
						profilAdmin = adao.get(utilisateurEnter);
					}else {
						
						Etudiant etu = new Etudiant(utilisateurEnter, motDePasseEnter_);
						EtudiantDao etudao = new EtudiantDao();
						
						// on vérifie si l'utilisateur est un étudiant
						if(etudao.isEtudiant(etu)) {
							balise = 2;
							profilEtu = etudao.get(utilisateurEnter);
						}
					}
					
					if(balise == 0) {
						JOptionPane.showMessageDialog(null, "L'identifiant ou le mot de passe est invalide :(", "Authentification", JOptionPane.OK_OPTION);
					}
				}catch(NumberFormatException ee) {
					JOptionPane.showMessageDialog(null, "L'identifiant ou le mot de passe est invalide :(", "Authentification", JOptionPane.OK_OPTION);
				}
			
			}
		});
	}
	
	/**
	 * Cette méthode permet de retourner 
	 * @return balise, qui indique le type d'utilisateur connecté
	 */
	public int getBalise() {
		return balise;
	}
	
	/**
	 * Cette méthode renvoie 
	 * @return btnConnexion, qui permet d'excécuter la connection depuis l'interface Principale (Interface)
	 */
	public JButton getBtnConnexion() {
		return btnConnexion;
	}


	/***
	 * Cette méthode permet de renvoyer
	 * @return profilAdmin, le profil de l'administrateur
	 * dans le cas de la connexion d'un administrateur
	 */
	public Admin getProfilAdmin() {
		return profilAdmin;
	}

	/***
	 * Cette méthode permet de renvoyer
	 * @return profilAdmin, le profil de l'étudiant
	 * dans le cas de la connexion d'un étudiant
	 */
	public Etudiant getProfilEtu() {
		return profilEtu;
	}
}