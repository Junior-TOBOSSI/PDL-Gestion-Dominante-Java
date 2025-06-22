/**
 * Cette classe permet la gestion de l'interface d'acceuil de l'administrateur
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */


package gui;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

public class AcceuilAdminGui extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnDeconnAcceuilAdmin;
	private JButton btnDominante;
	private JButton btnEtape;
	private JButton btnEtudiant;
	private JButton btnProfilAcc;
	
	/**
	 *
	 * @return le bouton d'accès au profil Administrateur 
	 */
	public JButton getBtnProfilAcc() {
		return btnProfilAcc;
	}
	
	/**
	 * 
	 * @param btnProfilAcc
	 */
	public void setBtnProfilAcc(JButton btnProfilAcc) {
		this.btnProfilAcc = btnProfilAcc;
	}

	/**
	 * Create the panel.
	 */
	public AcceuilAdminGui() {
		setBackground(new Color(192, 63, 99));
		setLayout(null);
		
		btnEtudiant = new JButton("Etudiants ");
		btnEtudiant.setIcon(new ImageIcon(AcceuilAdminGui.class.getResource("/data/Etudiants.jpg")));
		btnEtudiant.setBounds(527, 35, 389, 190);
		add(btnEtudiant);
		
		btnEtape = new JButton("");
		
		btnEtape.setIcon(new ImageIcon(AcceuilAdminGui.class.getResource("/data/Etapes.jpg")));
		btnEtape.setBounds(527, 299, 389, 190);
		add(btnEtape);
		
		btnDominante = new JButton("");
		
		btnDominante.setIcon(new ImageIcon(AcceuilAdminGui.class.getResource("/data/Dominantes.jpg")));
		btnDominante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDominante.setBounds(31, 237, 348, 205);
		add(btnDominante);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(0, 536, 1023, 65);
		add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblEcoleSuprieurDingnieurs_1 = new JLabel("Ecole Supérieure d’Ingénieurs en Génie Electrique ");
		lblEcoleSuprieurDingnieurs_1.setForeground(new Color(255, 128, 192));
		lblEcoleSuprieurDingnieurs_1.setFont(new Font("Tahoma", Font.ITALIC, 25));
		panel_1.add(lblEcoleSuprieurDingnieurs_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AcceuilAdminGui.class.getResource("/data/logo_esigelec.png")));
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(230, 243, 49, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Dominante ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_2.setBounds(125, 453, 147, 25);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Etudiants ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_2_1.setBounds(646, 234, 147, 25);
		add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Etapes");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_2_1_1.setBounds(634, 500, 147, 25);
		add(lblNewLabel_2_1_1);
		
		JButton btnMenu = new JButton("New button");
		
		btnMenu.setIcon(new ImageIcon(AcceuilAdminGui.class.getResource("/data/login_icon.png")));
		btnMenu.setBackground(new Color(192, 63, 99));
		btnMenu.setBounds(20, 11, 56, 56);
		add(btnMenu);
		
		JPanel panel = new JPanel();
		panel.setBounds(69, 11, 159, 93);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(192, 63, 99));
		panel.setVisible(false);
		add(panel);
		panel.setLayout(null);
		
	
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setIcon(new ImageIcon(AcceuilAdminGui.class.getResource("/data/quitterIcone.jpg")));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnQuitter.setBounds(142, 0, 30, 23);
		panel.add(btnQuitter);
		
		btnDeconnAcceuilAdmin = new JButton("Deconnexion");

		btnDeconnAcceuilAdmin.setBounds(10, 59, 132, 23);
		panel.add(btnDeconnAcceuilAdmin);
		
		btnProfilAcc = new JButton("Profil");
		btnProfilAcc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnProfilAcc.setBounds(10, 25, 132, 23);
		panel.add(btnProfilAcc);
		
		btnMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(true);	
			}
		});
		
		
		btnQuitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);	
			}
		});

	}
	
	/**
	 * Cette méthode donne accès au bouton pour la déconnexion de l'admin depuis l'interface d'acceuil
	 * @return le bouton
	 */
	public JButton getBtnDeconnexion() {
		return btnDeconnAcceuilAdmin;
	}
	
	/**
	 * Cette méthode donne accès au bouton pour consulter les dominantes depuis l'interface acceuil
	 * @return le bouton
	 */
	public JButton getBtnDominante(){
		return btnDominante;
	}
	
	/**
	 * Cette méthode donne accès au bouton pour consulter les étapes de traitement depuis l'interface acceuil
	 * @return le bouton
	 */
	public JButton getBtnEtape(){
		return btnEtape;
	}
	
	
	/**
	 * Cette méthode donne accès au bouton pour consulter les étudiants depuis l'interface acceuil
	 * @return
	 */
	public JButton getBtnEtudiant(){
		return btnEtudiant;
	}
}
