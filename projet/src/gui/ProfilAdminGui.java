/**
 * Cette classe permet la gestion de l'afficheur du profil Administrateur
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */

package gui;
import model.*;



import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import dao.*;

public class ProfilAdminGui extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Cet attribut permet de savoir si le bouton modifier a été activé ou non
	 */
	private boolean isModifier = false;
	
	/**
	 * Cet attribut permet de recevoir les données de l'admin après sa connexion
	 */
	private Admin admin;


	/**
	 * Create the panel.
	 */
	public ProfilAdminGui(Admin admin) {
		
		// On assigne l'admin
		setAdmin(admin);
		
		setLayout(new GridLayout(0, 1, 0, 0));
		JPanel panel = new JPanel();
		panel.setBounds(41, 48, 768, 468);
		
		panel.setLayout(null);
		
		JLabel iconeProfil = new JLabel("");
		iconeProfil.setIcon(new ImageIcon(ProfilAdminGui.class.getResource("/data/login_icon.png")));
		iconeProfil.setBounds(43, 21, 49, 45);
		panel.add(iconeProfil);
		
		JLabel labelNom = new JLabel("Nom:");
		labelNom.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelNom.setBounds(86, 85, 75, 18);
		panel.add(labelNom);
		
		JLabel labelPrenom = new JLabel("Prenom :");
		labelPrenom.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelPrenom.setBounds(86, 129, 75, 13);
		panel.add(labelPrenom);
		

		
		JLabel labeMdp = new JLabel("Mot de passe :");
		labeMdp.setFont(new Font("Tahoma", Font.BOLD, 14));
		labeMdp.setBounds(86, 177, 128, 16);
		panel.add(labeMdp);
		
		
		
		
		JButton btnValiderProfil = new JButton("Valider ");
		
		btnValiderProfil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnValiderProfil.setBounds(344, 293, 116, 23);
		panel.add(btnValiderProfil);
		
		JTextField caseNom = new JTextField(admin.getNom());
		caseNom.setEditable(false);
		caseNom.setBounds(261, 86, 245, 20);
		panel.add(caseNom);
		caseNom.setColumns(10);
		
		JTextField casePrenom = new JTextField(admin.getPrenom());
		casePrenom.setEditable(false);
		casePrenom.setColumns(10);
		casePrenom.setBounds(261, 127, 245, 20);
		panel.add(casePrenom);
		
		JTextField caseMdp = new JTextField(admin.getMotDePasse());
		caseMdp.setEditable(false);
		caseMdp.setColumns(10);
		caseMdp.setBounds(261, 177, 245, 20);
		panel.add(caseMdp);
		
		JButton btnModifier = new JButton("Modifier");
		
		
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnModifier.setBounds(210, 293, 116, 23);
		panel.add(btnModifier);
		
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				caseNom.setEditable(true);
				casePrenom.setEditable(true);
				caseMdp.setEditable(true);
				isModifier = true;
			}
		});
		
		btnValiderProfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(isModifier) {
					
					AdminDao adminDao = new AdminDao();
					
					String nouvNom = caseNom.getText().strip();
					String nouvPrenom = casePrenom.getText().strip();
					String nouvMdp = caseMdp.getText();
					
					Admin admin_ = new Admin(admin.getId(), nouvNom, nouvPrenom, nouvMdp);
					
					adminDao.update(admin_);
					
					
					caseNom.setEditable(false);
					casePrenom.setEditable(false);
					caseMdp.setEditable(false);
					
					panel.revalidate();
					panel.repaint();
				}
				
			}
		});
		
		add(panel);
	}

	/**
	 * Cette méthode permet de retourner l'admin connecté
	 * @return admin
	 */
	public Admin getAdmin() {
		return admin;
	}

	/**
	 * Cette méthode permet de modifier un admin
	 * @param admin
	 */
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
