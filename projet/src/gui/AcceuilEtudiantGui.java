/**
 * Cette classe permet la gestion de l'interface d'acceuil de l'Etudiant
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */


package gui;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;


import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AcceuilEtudiantGui extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Bouton d'accès à la déconnexion d'un étudiant
	 */
	private JButton btnDeconnexionEtudiant;
	
	/**
	 * Bouton d'accès au choix d'un étudiant
	 */
	private JButton btnChoix;
	
	/**
	 * Bouton d'accès au profil d'un étudiant
	 */
	private JButton btnProfilAcc;
	
	/***
	 * bouton d'accès à la dominante finale d'un étudiant
	 */
	private JButton btnDominanteFinale;



	/**
	 * Create the panel.
	 */
	public AcceuilEtudiantGui() {
		setBackground(new Color(192, 63, 99));
		setLayout(null);
		
		JPanel panelPied = new JPanel();
		panelPied.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelPied.setBackground(new Color(0, 0, 0));
		panelPied.setBounds(0, 518, 1021, 81);
		add(panelPied);
		panelPied.setLayout(null);
		
		JLabel labelPiedPanel = new JLabel("       Ecole Supérieure d’Ingénieurs en  Génie Electrique ESIGELEC ");
		labelPiedPanel.setBounds(41, 17, 821, 36);
		labelPiedPanel.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelPiedPanel.setForeground(new Color(255, 128, 192));
		panelPied.add(labelPiedPanel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(AcceuilEtudiantGui.class.getResource("/data/logo_esigelec.png")));
		lblNewLabel_2.setBounds(814, 21, 93, 32);
		panelPied.add(lblNewLabel_2);
		
		btnDominanteFinale = new JButton("Dominante Finale ");
		btnDominanteFinale.setIcon(new ImageIcon(AcceuilEtudiantGui.class.getResource("/data/rideaux.jpg")));
		btnDominanteFinale.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDominanteFinale.setBackground(new Color(255, 128, 192));
		btnDominanteFinale.setActionCommand("Choix");

		btnDominanteFinale.setBounds(678, 139, 245, 164);
		add(btnDominanteFinale);
		
		btnChoix = new JButton("Choix");
		
		btnChoix.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnChoix.setBackground(new Color(255, 128, 192));
		btnChoix.setActionCommand("Choix ");

		btnChoix.setBounds(99, 325, 245, 159);
		add(btnChoix);
		
		JPanel panel = new JPanel();
		panel.setBounds(244, 11, 500, 38);
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblBienvenueMonTalentueux = new JLabel(" Bienvenue mon talentueux et brillant ingénieur\r\n");
		panel.add(lblBienvenueMonTalentueux);
		lblBienvenueMonTalentueux.setForeground(new Color(255, 128, 192));
		lblBienvenueMonTalentueux.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(757, 14, 166, 35);
		add(panel_2);
		
		JLabel labelBienvenuSuite = new JLabel("Your a Winner");
		panel_2.add(labelBienvenuSuite);
		labelBienvenuSuite.setForeground(new Color(255, 128, 192));
		labelBienvenuSuite.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_3 = new JLabel("DOMINANTE FINALE");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(705, 106, 192, 38);
		add(lblNewLabel_3);
	
		
		JButton btnMenu = new JButton("");
		btnMenu.setIcon(new ImageIcon(AcceuilEtudiantGui.class.getResource("/data/login_icon.png")));
		btnMenu.setBackground(new Color(192, 63, 99));
		btnMenu.setBounds(0, 8, 56, 56);
		add(btnMenu);

		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(48, 11, 159, 93);
		panelMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMenu.setBackground(new Color(192, 63, 99));
		panelMenu.setVisible(false);
		add(panelMenu);
		panelMenu.setLayout(null);
		
	
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setIcon(new ImageIcon(AcceuilAdminGui.class.getResource("/data/quitterIcone.jpg")));
		btnQuitter.setBounds(142, 0, 30, 23);
		panelMenu.add(btnQuitter);
		
		btnDeconnexionEtudiant = new JButton("Deconnexion");

		btnDeconnexionEtudiant.setBounds(10, 59, 132, 23);
		panelMenu.add(btnDeconnexionEtudiant);
		
		btnProfilAcc = new JButton("Profil");
		btnProfilAcc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		btnProfilAcc.setBounds(10, 25, 132, 23);
		panelMenu.add(btnProfilAcc);
		
		btnMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelMenu.setVisible(true);	
			}
		});
		
		
		btnQuitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelMenu.setVisible(false);	
			}
		});
	}
	
	
	/**
	 * Cette méthode donne accès au bouton qui permet à l'étudiant de faire ses choix
	 * @return le bouton
	 */
	public JButton getBtnChoix() {
		return btnChoix;
	}

	/**
	 * Cette méthode donne accès au bouton qui permet la déconnexion de l'étudiant depuis l'acceuil
	 * @return le bouton
	 */
	public JButton getBtnDeconnexionEtudiant() {
		return btnDeconnexionEtudiant;
	}
	
	/**
	 * Cette méthode donne accès au bouton qui permet à l'étudiant de voir son profil
	 * @return le bouton
	 */
	public JButton getBtnProfil() {
		return btnProfilAcc;
	}
	
	/**
	 * Cette méthode donne accès au bouton qui permet à l'étudiant de voir sa dominante finale
	 * @return le bouton
	 */
	public JButton getBtnDominanteFinale() {
		return btnDominanteFinale;
	}

	/**
	 * Cette méthode permet d'instancier le bouton qui permet de voir la dominante finale
	 * @param btnDominanteFinale
	 */
	public void setBtnDominanteFinale(JButton btnDominanteFinale) {
		this.btnDominanteFinale = btnDominanteFinale;
	}
}