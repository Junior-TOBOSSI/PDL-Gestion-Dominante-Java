/**
 * Cette classe permet la gestion de l'interface Etudiant
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */

package gui;

import javax.swing.JPanel;
import java.awt.Color;

import java.awt.Font;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import dao.DominanteDao;
import dao.EtudiantDao;

import javax.swing.ImageIcon;
import model.*;

public class InterfaceEtudiant extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Cet attribut permet d'accéder au bouton de déconnexion  
	 */
	private JButton btnDeconnexionEtudiant;
	
	/**
	 * Cet attribut permet d'accéder au cadre qui contient les onglets
	 */
	private JPanel cadreContenuOnglet;
	
	/**
	 * Cet attribut permet d'accéder au panel du choix
	 */
	private ChoixEtudiant panelChoix;
	
	/**
	 * Cet attribut permet d'accéder au panel du profil
	 */
	private ProfilEtudiantGui panelProfil;
	
	/**
	 * Cet attribut permet de recevoir les informations de l'étudiant connecté
	 */
	private Etudiant etud;
	
	/**
	 * Cet attribut permet d'accéder au panel dominante finale
	 */
	private JPanel panelDominanteFinale;

	/**
	 * Create the panel.
	 */

	public InterfaceEtudiant(Etudiant etud) {
		
		this.etud = etud;

		setLayout(new GridLayout(0, 1, 0, 0));

		// Création du panel principal
		// panel mis en absolute layout
		// sert à éviter un affichage nulle
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		add(panel);
		
		
		// Création du menu Profil avec insertion des bouttons profil, deconnexion et quitter

		JPanel menuProfil = new JPanel();
		menuProfil.setBackground(panel.getBackground());
		menuProfil.setVisible(false);
		menuProfil.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuProfil.setBounds(30, 45, 122, 115);
		panel.add(menuProfil);
		menuProfil.setLayout(null);
		
		JButton btnProfil = new JButton("PROFIL");
		btnProfil.setBounds(10, 30, 107, 31);
		btnProfil.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnProfil.setBackground(new Color(162, 63, 99));
		menuProfil.add(btnProfil);
		
		btnProfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuProfil.setVisible(false);	
				setOngletProfil();
			}
		});
				
		JButton btnQuitter = new JButton("");
		btnQuitter.setIcon(new ImageIcon(InterfaceEtudiant.class.getResource("/data/quitterIcone.jpg")));
		btnQuitter.setBounds(95, 0, 27, 23);
		btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuitter.setBackground(new Color(162, 63, 99));
		menuProfil.add(btnQuitter);
		
		btnDeconnexionEtudiant = new JButton("DECONNEXION");
		btnDeconnexionEtudiant.setBounds(10, 72, 107, 31);
		btnDeconnexionEtudiant.setBackground(new Color(162, 63, 99));
		btnDeconnexionEtudiant.setFont(new Font("Tahoma", Font.BOLD, 11));
		menuProfil.add(btnDeconnexionEtudiant);
		
		
		btnQuitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuProfil.setVisible(false);	
			}
		});

		// Création de la barre des onglets Choix et DominanteFinale
		
		JPanel barreOnglets = new JPanel();
		barreOnglets.setBounds(169, 0, 852, 56);
		panel.add(barreOnglets);
		barreOnglets.setLayout(new GridLayout(0, 2, 0, 0));

		// Création de l'onglet DominanteFinale, avec un boutton cliquable
		
		JButton menuDomFinBtn = new JButton("Dominante Finale");
		menuDomFinBtn.setForeground(Color.WHITE);
		menuDomFinBtn.setBackground(new Color(192, 63, 99));
		barreOnglets.add(menuDomFinBtn);

		// Création de l'onglet Choix, avec un boutton cliquable
		
		JButton menuChoixBtn = new JButton("Choix");
		menuChoixBtn.setForeground(Color.WHITE);
		menuChoixBtn.setBackground(new Color(192, 63, 99));
		barreOnglets.add(menuChoixBtn);

		// Création du cadre servant à afficher le contenu de l'onglet des boutons du menu
		
		cadreContenuOnglet = new JPanel();
		cadreContenuOnglet.setBackground(new Color(192, 63, 99));
		cadreContenuOnglet.setBounds(106, 112, 869, 461);
		panel.add(cadreContenuOnglet);
		cadreContenuOnglet.setLayout(new GridLayout(1, 1, 0, 0));

		JButton iconeProfil = new JButton("");
		iconeProfil.setBackground(panel.getBackground());
		iconeProfil.setIcon(new ImageIcon(InterfaceEtudiant.class.getResource("/data/login_icon.png")));
		iconeProfil.setBounds(10, 11, 56, 56);
		panel.add(iconeProfil);
		cadreContenuOnglet.revalidate();

		iconeProfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuProfil.setVisible(true);	
			}
		});


		menuChoixBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setOngletChoix();
			}
		});

		menuDomFinBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setOngletDominanteFinale();
			}
		});
		}

	/**
	 * Cette méthode retourne l'étudiant connecté
	 * @return etud
	 */
	public Etudiant getEtud() {
		return etud;
	}


	/***
	 * Cette méthode permet de modifier l'étudiant connecté
	 * @param etud 
	 */
	public void setEtud(Etudiant etud) {
		this.etud = etud;
	}

	/**
	 * Cette méthode permet de retourner le bouton de déconnexion de l'interface
	 * @return le bouton
	 */
	public JButton getBtnDeconnexion() {
		return btnDeconnexionEtudiant;
	}

	/**
	 * Cette méthode permet de remplacer un onglet dans le cadre des onglets
	 * @param panelToAdd  
	 * @param panelContainer 
	 */
	public void reInitializeSubPanel(JPanel panelContainer, JPanel panelToAdd) {
		panelContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelContainer.setBackground(new Color(162, 63, 99));
		panelContainer.setForeground(new Color(255, 255, 255));
		panelContainer.removeAll();
		panelContainer.add(panelToAdd);	
		panelContainer.revalidate();
	}
	

	/**
	 * Cette méthode permet de remplacer l'onglet choix dans le cadre 
	 */
	public void setOngletChoix() {
	   if(panelChoix == null) {
		   panelChoix = new ChoixEtudiant(etud);
	   }
		reInitializeSubPanel(cadreContenuOnglet, panelChoix);
	}
   
	/**
	 * Cette méthode permet de remplacer l'onglet profil dans le cadre 
	 */
   public void setOngletProfil() {
		
	   if(panelProfil == null) {
		   panelProfil = new ProfilEtudiantGui(etud);
		   
	   }
		reInitializeSubPanel(cadreContenuOnglet, panelProfil);
	}
   
   /**
	 * Cette méthode permet de remplacer l'onglet dominante finale dans le cadre 
	 */
   public void setOngletDominanteFinale(){
	   if(panelDominanteFinale == null) {
		   
		  panelDominanteFinale = new JPanel();
		  panelDominanteFinale.setLayout( new GridLayout(12, 0, 0, 0));
		  panelDominanteFinale.add(new JLabel("  FELICITATIONS"));
		  panelDominanteFinale.add(new JLabel());
		  panelDominanteFinale.add(new JLabel("  Votre dominante finale est :"));
		  
		  JLabel labelDominante = new JLabel();
		  DominanteDao conDom = new DominanteDao();
		  
		  
		  
		  if(etud.getDominanteFinale() <= 1 || etud.getDominanteFinale() >= 15) {
			  labelDominante.setText("  Si vous avez déjà fait votre choix, patientez la délibération !");

			  
		  }else {
			  labelDominante.setText(" " + conDom.get(etud.getDominanteFinale()).getNomLong() +
					  " (" + conDom.get(etud.getDominanteFinale()).getSigle() + ")" );
		  }
		  
		   
		  panelDominanteFinale.add(labelDominante);
	   }
	   reInitializeSubPanel(cadreContenuOnglet, panelDominanteFinale);
   }
	
   
   
}