/**
 * Cette classe permet la gestion de l'onglet AJOUTER un Etudiant
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */

package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerListModel;
import javax.swing.border.LineBorder;

import model.*;
import dao.*;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddEtudiantGui extends JPanel {

	private static final long serialVersionUID = 1L;


	/**
	 * Create the panel.
	 * @param string 
	 */
	public AddEtudiantGui() {

		setBackground(new Color(255, 255, 255));
		
		setVisible(true);
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(165, 54, 85));
		add(panel);
		
		JPanel zoneInfos = new JPanel();
		zoneInfos.setLayout(null);
		zoneInfos.setBackground(new Color(165, 54, 85));
		zoneInfos.setBounds(40, 85, 759, 380);
		panel.add(zoneInfos);
		
		/* en-tête */
		JLabel enteteEtudiant = new JLabel("AJOUTER UN ETUDIANT");
		enteteEtudiant.setBounds(40, 26, 757, 48);
		enteteEtudiant.setBorder(new LineBorder(new Color(0,0,0)));
		panel.add(enteteEtudiant);
		enteteEtudiant.setHorizontalAlignment(SwingConstants.CENTER);
		enteteEtudiant.setForeground(Color.WHITE);
		enteteEtudiant.setFont(new Font("Tahoma", Font.BOLD, 18));
		enteteEtudiant.setBackground(new Color(165, 54, 85));
		
		/* zone id */
		JLabel idLabel = new JLabel(" Id  ");
		idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		idLabel.setForeground(new Color(0, 0, 0));
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idLabel.setBounds(10, 11, 130, 33);
		zoneInfos.add(idLabel);
		
		JTextField caseId = new JTextField();
		caseId.setBounds(161, 10, 571, 38);
		zoneInfos.add(caseId);
		caseId.setColumns(10);
		
		/* zone nom */
		JLabel nomLabel = new JLabel(" Nom/Last Name ");
		nomLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nomLabel.setForeground(new Color(0, 0, 0));
		nomLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nomLabel.setBounds(20, 62, 130, 33);
		zoneInfos.add(nomLabel);
		
		JTextField caseNom = new JTextField();
		caseNom.setColumns(10);
		caseNom.setBounds(160, 61, 572, 38);
		zoneInfos.add(caseNom);
		
		/* zone prenom */
		JLabel prenomLabel = new JLabel("Prenom/First Name");
		prenomLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		prenomLabel.setForeground(new Color(0, 0, 0));
		prenomLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		prenomLabel.setBounds(10, 106, 130, 33);
		zoneInfos.add(prenomLabel);
		
		JTextField casePrenom = new JTextField();
		casePrenom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		casePrenom.setColumns(10);
		casePrenom.setBounds(161, 110, 571, 33);
		zoneInfos.add(casePrenom);
		
		/* zone date de naissance ddn */
		JLabel ddnLabel = new JLabel("Date de Naissance ");
		ddnLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		ddnLabel.setForeground(new Color(0, 0, 0));
		ddnLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ddnLabel.setBounds(10, 150, 130, 27);
		zoneInfos.add(ddnLabel);
		
		JTextField caseddn = new JTextField();
		caseddn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		caseddn.setColumns(10);
		caseddn.setBounds(161, 150, 571, 33);
		zoneInfos.add(caseddn);
		
		JLabel formatDate = new JLabel("(yyyy-MM-dd)");
		formatDate.setHorizontalAlignment(SwingConstants.RIGHT);
		formatDate.setForeground(Color.BLACK);
		formatDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		formatDate.setBounds(0, 171, 130, 19);
		zoneInfos.add(formatDate);
		
		/* Zone classement */
		JLabel classementLabel = new JLabel("Classement 1A");
		classementLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		classementLabel.setForeground(new Color(0, 0, 0));
		classementLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		classementLabel.setBounds(10, 194, 120, 33);
		zoneInfos.add(classementLabel);
	
		JSpinner caseClassement = new JSpinner();
		caseClassement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		caseClassement.setBounds(161, 194, 571, 33);
		zoneInfos.add(caseClassement);
		
		/* Zone promotion */
		JLabel promoLabel = new JLabel("Promotion");
		promoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		promoLabel.setForeground(new Color(0, 0, 0));
		promoLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		promoLabel.setBounds(20, 282, 120, 33);
		zoneInfos.add(promoLabel);
		
		JTextField casePromo = new JTextField();
		casePromo.setBounds(161, 282, 571, 33);
		zoneInfos.add(casePromo);
		casePromo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		casePromo.setColumns(10);
		
		/* Zone filiere */
		JLabel filiereLabel = new JLabel("Filiere ");
		filiereLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		filiereLabel.setForeground(new Color(0, 0, 0));
		filiereLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		filiereLabel.setBounds(20, 238, 120, 33);
		zoneInfos.add(filiereLabel);

		JComboBox caseFiliere = new JComboBox();
		caseFiliere.setFont(new Font("Tahoma", Font.PLAIN, 15));
		caseFiliere.setModel(new DefaultComboBoxModel(new String[] {"FISE", "FISA"}));
		caseFiliere.setBounds(160, 238, 572, 33);
		zoneInfos.add(caseFiliere);
	
		
		JLabel message = new JLabel("Données incorrectes !!!");
		message.setVisible(false);
		message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		message.setBounds(493, 335, 174, 25);
		zoneInfos.add(message);
		
		/* boutton de validation */
		JButton btnValider = new JButton(" Valider ");
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				message.setVisible(false);
				
				try {
					int idEnter = Integer.parseInt(caseId.getText());
					String nomEnter = caseNom.getText().strip();
					String prenomEnter = casePrenom.getText().strip();
					String ddnEnter = caseddn.getText().strip();
					int filiereEnter = caseFiliere.getSelectedIndex() + 1;
					int classsementEnter = Integer.parseInt(caseClassement.getModel().getValue().toString());
					int promotionEnter = Integer.parseInt(casePromo.getText().strip());
					
					
					
					
					if(nomEnter.length() < 30
							&& prenomEnter.length() < 30
							&& checkDateFormat(ddnEnter)){
						try {
							Date dateddn = sdf.parse(ddnEnter);	
							
							
							Etudiant etu = new Etudiant(idEnter, nomEnter, prenomEnter, dateddn, classsementEnter, promotionEnter, filiereEnter);

							EtudiantDao conEtu = new EtudiantDao();
							int state = conEtu.add(etu);
							
							if(state == 0) {
								message.setText("Echec de l'opération !!!");
								message.setVisible(true);
							}
							else {
								message.setText("Opération réussie !");
								message.setVisible(true);
							}
						}catch(ParseException e1) {
							
						}
				
						
					}
					else {
						message.setText("Données incorrectes !!!");
						message.setVisible(true);
					}
					
				}catch(NumberFormatException e1) {
					message.setText("Entrées invalides !!!");
					message.setVisible(true);
				}
			}
		});
		
		
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnValider.setBackground(new Color(162, 63, 99));
		btnValider.setBounds(347, 331, 115, 33);
		zoneInfos.add(btnValider);	
		
		JLabel note = new JLabel("<html>PS : Le mot de passe est automatiquement<br>\r\nmis à \"AAAAA\", ne vous s'en souciez pas :) </html>");
		note.setFont(new Font("Tahoma", Font.ITALIC, 13));
		note.setBounds(10, 328, 289, 32);
		zoneInfos.add(note);

	}
	
	/**
	 * Cette méthode permet de vérifier si une chaine de caractère est au bon format de date
	 * @param maChaineDate
	 * @return True si le format est correct, False sinon
	 */
	boolean checkDateFormat(String maChaineDate) {
		
		if(maChaineDate.split("-").length != 3) {
			return false;
		}
		else if(maChaineDate.split("-")[0].length() != 4) {
			return false;
			// on vérifie si le mois n'est pas compris entre 1 et 12 
		}else if(maChaineDate.split("-")[1].length() != 2 && Integer.parseInt(maChaineDate.split("-")[1]) > 13 
				&& Integer.parseInt(maChaineDate.split("-")[1]) < 0) {
			return false;
			// on vérifie si la date n'est pas compris entre 1 et 31 
		}else if(maChaineDate.split("-")[2].length() != 2 && Integer.parseInt(maChaineDate.split("-")[2]) > 32 
				&& Integer.parseInt(maChaineDate.split("-")[1]) < 0) {
			return false;
		}
		
		return true;
	}
	
}