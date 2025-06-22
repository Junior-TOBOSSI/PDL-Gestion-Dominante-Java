/**
 * Cette classe permet la gestion de l'onglet Consulter Dominante
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */


package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dao.DominanteDao;
import dao.EtudiantDao;
import model.Dominante;
import model.Etudiant;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;

public class ConsulterEtudiant extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Cet attribut permet de renvoyer le boutton 
	 * qui renvoit vers l'onglet pour AJOUTER un étudiant
	 */
	private JButton btnAdd;
	private JTable tableEtudiants;
	private JButton btnForcerInscription;
	
	/**
	 * Cette méthode permet de retourner le boutton
	 * @return btnForcerInscription, qui renvoit vers l'onglet 
	 * Forcer l'inscription d'un étudiant
	 */
	public JButton getBtnForcerInscription() {
		return btnForcerInscription;
	}

	/**
	 * Cette méthode permet de retourner le boutton
	 * @return btnAdd, qui renvoit vers l'onglet 
	 * AJOUTER un Etudiant
	 */
	public JButton getBtnAdd() {
		return btnAdd;
	}
	
	/**
	 * Create the panel.
	 */
	public ConsulterEtudiant() {

		// Création de la connexion à la table étudiant de la base de données
		EtudiantDao conEtu= new EtudiantDao();
		
		ArrayList<Etudiant> listEtudiant = conEtu.getAll();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		add(panel);
		panel.setLayout(null);
		
		
		
		// Ce bouton permet de supprimer l'étudiant sélectionnné
		JButton btnDelete = new JButton("");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int confirm = JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir faire cette suppression", "Suppression", JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.OK_OPTION) {
					int rowSelected = tableEtudiants.getSelectedRow();
					int idEtudiant = (int) tableEtudiants.getValueAt(rowSelected, 0);
					
					
					if(idEtudiant == -1) {
						JOptionPane.showConfirmDialog(null, "Veuillez selectionner un étudiant svp", "Warning", JOptionPane.CLOSED_OPTION);
					}else {
						int delete = conEtu.delete(idEtudiant);
						if( delete == 0) {
							JOptionPane.showConfirmDialog(null, "Vous ne pouvez pas supprimer cet étudiant !", "ERREUR", JOptionPane.CLOSED_OPTION);
						}else if (delete == 1) {
							JOptionPane.showConfirmDialog(null, "Suppression effectuee", "Suppression", JOptionPane.CLOSED_OPTION);
							tableEtudiants.remove(rowSelected);
						}else {
							JOptionPane.showConfirmDialog(null, "Une erreur est survenu lors de la suppression de cet étudiant", "ERREUR", JOptionPane.CLOSED_OPTION);
						}	
					}	
				}
				
				
			}
		});
		btnDelete.setIcon(new ImageIcon(ConsulterEtudiant.class.getResource("/data/iconeDelete.jpg")));

		btnDelete.setBounds(762, 0, 50, 34);
		panel.add(btnDelete);
		
		
		btnAdd = new JButton("");
		
		btnAdd.setIcon(new ImageIcon(ConsulterEtudiant.class.getResource("/data/addIcone.jpg")));
		btnAdd.setBounds(714, 0, 50, 34);
		panel.add(btnAdd);
		
		
		// Ce bouton permet de valider la saisie des nouvelles incormatins utilsateur
		JButton btnValider = new JButton("");
		
		btnValider.setIcon(new ImageIcon(ConsulterEtudiant.class.getResource("/data/validerIcone.jpg")));
		btnValider.setBounds(663, 0, 51, 34);
		panel.add(btnValider);
		
		
		
		
		
		tableEtudiants = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "NOM", "PRENOM", "FILIERE", "RANG", "DATENAISSANCE", "PROMO"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		tableEtudiants.setRowHeight(30);
		tableEtudiants.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableEtudiants.getColumnModel().getColumn(1).setPreferredWidth(125);
		tableEtudiants.getColumnModel().getColumn(2).setPreferredWidth(125);
		tableEtudiants.getColumnModel().getColumn(3).setPreferredWidth(30);
		tableEtudiants.getColumnModel().getColumn(4).setPreferredWidth(50);
		tableEtudiants.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableEtudiants.getColumnModel().getColumn(6).setPreferredWidth(50);
		
		
		DefaultTableModel model = (DefaultTableModel) tableEtudiants.getModel();
				
		for(Etudiant etud : listEtudiant) {
			String filiere;
			if(etud.getfiliere() == 1) {
				filiere = "FISE";
			}
			else {
				filiere = "FISA";
			}
			model.addRow(new Object[]  {etud.getId(), etud.getNom(), etud.getPrenom(), filiere, etud.getClassement(), etud.getDateDeNaissance(), etud.getPromotion()});	
		}
		
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 853, 386);
		scrollPane.setViewportView(tableEtudiants);
		panel.add(scrollPane);
		
		btnForcerInscription = new JButton("FORCER INSCRIPTION");
	
		btnForcerInscription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnForcerInscription.setBounds(10, 0, 160, 34);
		panel.add(btnForcerInscription);
		
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				
				int rowSelected = tableEtudiants.getSelectedRow();
				
				// si la modification est confirmé 
				if(rowSelected >= 0) {
					
					// on demande une confirmation de modification
					int confirm = JOptionPane.showConfirmDialog(null, "Confirmez vous la modification de ces informations ?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
					
					if(confirm == 0) {
						
						int nouveauID = (int) tableEtudiants.getValueAt(rowSelected, 0);
						String nouveauNom = (String) tableEtudiants.getValueAt(rowSelected, 1);
						String nouveauPrenom = (String) tableEtudiants.getValueAt(rowSelected, 2);
						int nouveauClassement = (int) tableEtudiants.getValueAt(rowSelected, 4);
						String nouveauFiliere = (String) tableEtudiants.getValueAt(rowSelected, 3);
						int nouveauPromotion = (int) tableEtudiants.getValueAt(rowSelected, 6);
						
						int nouveauFiliereInt = 0;
						
						if(nouveauFiliere.equals("FISA")) {
							nouveauFiliereInt = 2;
						}else if(nouveauFiliere.equalsIgnoreCase("FISE")) {
							nouveauFiliereInt = 1;
						}else {
							JOptionPane.showConfirmDialog(null, "La valeur de Filiere doit etre 'FISE' ou 'FISA'", "Erreur", JOptionPane.INFORMATION_MESSAGE);
						}
						
						if(nouveauFiliereInt == 2 || nouveauFiliereInt == 1) {
							boolean isDateFormatOK = false;
							
							try {
								isDateFormatOK = checkDateFormat( (String) tableEtudiants.getValueAt(rowSelected, 5).toString());
							}catch(ClassCastException e2) {
								JOptionPane.showConfirmDialog(null, "Mauvais format de date", "Modification", JOptionPane.CLOSED_OPTION);
							}
							
							if(isDateFormatOK) {
								try {
									
									Date nouveauDate = sdf.parse( (String) tableEtudiants.getValueAt(rowSelected, 5).toString());
									Etudiant etud = new Etudiant(nouveauID, nouveauNom, nouveauPrenom, nouveauDate, nouveauClassement, nouveauPromotion, nouveauFiliereInt);
									int isModifier = conEtu.update(etud);
									if(isModifier == 1) {
										JOptionPane.showConfirmDialog(null, "Modification effectuee avec succes :)", "Modification", JOptionPane.CLOSED_OPTION);
										
									}else {
										JOptionPane.showConfirmDialog(null, "Modification non effectuee, quelque chose d'etrange s'est passee :( ", "Modification", JOptionPane.CLOSED_OPTION);
										
									}
								}catch(ParseException e1) {
									JOptionPane.showConfirmDialog(null, "Format de Date incorrect ", "Modification", JOptionPane.CLOSED_OPTION);
									
								}catch(ClassCastException e2) {
									JOptionPane.showConfirmDialog(null, "Vous n'avez pas effectue de modification", "Modification", JOptionPane.CLOSED_OPTION);
									
								}	
							}
							else {
								JOptionPane.showConfirmDialog(null, "Format de Date incorrect", "Modification", JOptionPane.CLOSED_OPTION);
								
							}
							
						}
					}
					
		
				}
		
			}
		});
			
		
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