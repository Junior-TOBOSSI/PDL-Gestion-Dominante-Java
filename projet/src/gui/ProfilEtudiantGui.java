package gui;

/**
 * Cette classe permet la gestion de l'afficheur du profil
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import dao.*;
import model.Etudiant;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class ProfilEtudiantGui extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Cet attribut permet de détecter si l'utilisateur à cliquer sur le bouton modifier
	 */
	private boolean isModifier = false;
	
	/**
	 * Cet attribut permet d'accéder à l'étudiant qui s'est connecté
	 */
	private Etudiant etud;



	/**
	 * Create the panel.
	 */
	public ProfilEtudiantGui(Etudiant etud) {
		
		setEtud(etud);
		
		SimpleDateFormat sdf = new SimpleDateFormat("DD-MM-YYYY");
		
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
		
		JLabel labelddn = new JLabel("Date de naissance:");
		labelddn.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelddn.setBounds(86, 167, 144, 13);
		panel.add(labelddn);
		
		JLabel labelPromo = new JLabel("Promotion :");
		labelPromo.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelPromo.setBounds(86, 204, 99, 18);
		panel.add(labelPromo);
		
		JLabel labelRang = new JLabel("Rang :");
		labelRang.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelRang.setBounds(86, 239, 70, 18);
		panel.add(labelRang);
		
		JLabel labelStatut = new JLabel("Statut :");
		labelStatut.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelStatut.setBounds(86, 284, 70, 13);
		panel.add(labelStatut);
		
		JLabel labeMdp = new JLabel("Mot de passe :");
		labeMdp.setFont(new Font("Tahoma", Font.BOLD, 14));
		labeMdp.setBounds(87, 322, 128, 16);
		panel.add(labeMdp);
		
		
		
		
		JButton btnValiderProfil = new JButton("Valider ");
		
		btnValiderProfil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnValiderProfil.setBounds(342, 392, 116, 23);
		panel.add(btnValiderProfil);
		
		JTextField caseNom = new JTextField(etud.getNom());
		caseNom.setEditable(false);
		caseNom.setBounds(261, 86, 245, 20);
		panel.add(caseNom);
		caseNom.setColumns(10);
		
		JTextField casePrenom = new JTextField(etud.getPrenom());
		casePrenom.setEditable(false);
		casePrenom.setColumns(10);
		casePrenom.setBounds(261, 127, 245, 20);
		panel.add(casePrenom);
		
		JTextField caseDdn = new JTextField(String.valueOf(etud.getDateDeNaissance()));
		caseDdn.setEditable(false);
		caseDdn.setColumns(10);
		caseDdn.setBounds(261, 165, 245, 20);
		panel.add(caseDdn);
		
		JTextField casePromo = new JTextField(String.valueOf(etud.getPromotion()));
		casePromo.setEditable(false);
		casePromo.setColumns(10);
		casePromo.setBounds(261, 205, 245, 20);
		panel.add(casePromo);
		
		JTextField caseRang = new JTextField(String.valueOf(etud.getClassement()));
		caseRang.setEditable(false);
		caseRang.setColumns(10);
		caseRang.setBounds(261, 240, 245, 20);
		panel.add(caseRang);
		
		String filiere = "FISE";
		
		if(etud.getfiliere() == 1) {
			filiere = "FISE";
		}
		else if (etud.getfiliere() == 2) {
			filiere = "FISA";
		}
		JTextField caseStatut = new JTextField(filiere);
		caseStatut.setEditable(false);
		caseStatut.setColumns(10);
		caseStatut.setBounds(261, 282, 245, 20);
		panel.add(caseStatut);
		
		JTextField caseMdp = new JTextField(etud.getMotDePasse());
		caseMdp.setEditable(false);
		caseMdp.setColumns(10);
		caseMdp.setBounds(261, 322, 245, 20);
		panel.add(caseMdp);
		
		JButton btnModifier = new JButton("Modifier");
		
		
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnModifier.setBounds(207, 394, 116, 23);
		panel.add(btnModifier);
		
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				caseNom.setEditable(true);
				casePrenom.setEditable(true);
				caseDdn.setEditable(true);
				caseMdp.setEditable(true);
				isModifier = true;
			}
		});
		
		btnValiderProfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(isModifier) {
					
					EtudiantDao etudDao = new EtudiantDao();
					
					String nouvNom = caseNom.getText().strip();
					String nouvPrenom = casePrenom.getText().strip();
					int nouvClassement = Integer.parseInt(caseRang.getText().strip());
					String nouvMdp = caseMdp.getText();
					Date nouvDdn;
					try {
						nouvDdn = sdf.parse(caseDdn.getText().strip());	
						Etudiant stu = new Etudiant(nouvNom, nouvPrenom, nouvDdn, nouvClassement, nouvMdp);
						etudDao.update(stu);
					}catch(ParseException e1) {
						JOptionPane.showConfirmDialog(null, "Format de date incorrect" + " " + e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					
					caseNom.setEditable(false);
					casePrenom.setEditable(false);
					casePromo.setEditable(false);
					caseMdp.setEditable(false);
					caseDdn.setEditable(false);
					
					panel.revalidate();
					panel.repaint();
					isModifier = false;
				}
				
			}
		});
		
		add(panel);
	}
	

	/**
	 * Cette méthode permet de retourner l'étudiant connecté
	 * @return etud
	 */
	public Etudiant getEtud() {
		return etud;
	}

	/**
	 * Cette méthode permet de modifier l'étudiant connecté
	 * @param etud
	 */
	public void setEtud(Etudiant etud) {
		this.etud = etud;
	}

}
