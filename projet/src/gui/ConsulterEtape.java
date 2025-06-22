/**
 * Cette classe permet la gestion de l'onglet consulter Dominante
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */

package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import javax.swing.table.DefaultTableModel;

import dao.*;
import model.*;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;

public class ConsulterEtape extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Cet attribut permet de renvoyer le boutton 
	 * qui renvoit vers l'onglet pour AJOUTER une Dominante
	 */
	private JButton btnAdd;
	
	private JTable tableEtapes;
	

	/**
	 * Create the panel.
	 */
	public ConsulterEtape() {
		
		 TraitementDao traitDao = new TraitementDao();
		
		 ArrayList<Etape> listEtapes = traitDao.getAll();
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		add(panel);
		panel.setLayout(null);
		
		JLabel infoLabel = new JLabel("<html>Ici vous pouvez gérer les accès utilisateurs.<br>\r\nFaites attention car les retours en arrières peuvent être difficiles, Soyez donc sûr <br>\r\nSeules les étapes 4 et 7 nécessite un lancement, tous le reste est automatiquement lancé sur validation <br><br>\r\nMerci de votre attention.</html>");
		infoLabel.setForeground(new Color(255, 0, 0));
		infoLabel.setBackground(new Color(255, 255, 255));
		infoLabel.setOpaque(true);
		infoLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		infoLabel.setBounds(17, 11, 811, 115);
		panel.add(infoLabel);
		
		
		JButton btnValider = new JButton("");
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
	
		
		btnValider.setIcon(new ImageIcon(ConsulterDominante.class.getResource("/data/validerIcone.jpg")));
		btnValider.setBounds(688, 132, 41, 34);
		panel.add(btnValider);
		
		
		tableEtapes = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "DATE DEBUT", "NOM ETAPE"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true,false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
			
	   tableEtapes.setRowHeight(30);
		
		
		DefaultTableModel model = (DefaultTableModel) tableEtapes.getModel();
		
		for(Etape etape : listEtapes) {
			
			model.addRow(new Object[]  {etape.getId(), etape.getDateDeDebut(), etape.getNom()});	
		}
		
		
		
		JScrollPane scrollPane = new JScrollPane();
	
		scrollPane.setViewportView(tableEtapes);
		scrollPane.setBounds(26, 177, 802, 248);
		panel.add(scrollPane);	
		
		JButton btnLancer = new JButton("LANCER");
		btnLancer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                int rowSelected = tableEtapes.getSelectedRow();
				
				int idSelected = (int) tableEtapes.getValueAt(rowSelected, 0);
				
				if(idSelected == 4) {
					boolean isOkay = traitDao.lancerTraitement(2);
					if(isOkay) {
						JOptionPane.showConfirmDialog(null, "Fin du traitement FISA!!!", "Superbe", JOptionPane.CLOSED_OPTION);
					}else {
						JOptionPane.showConfirmDialog(null, "Nous n'avons rencontre une erreur", "Sorry", JOptionPane.CLOSED_OPTION);

					}
				}else if(idSelected == 6) {
					boolean isOkay = traitDao.lancerTraitement(1);
					if(isOkay) {
						JOptionPane.showConfirmDialog(null, "Fin du traitement FISA!!!", "Superbe", JOptionPane.CLOSED_OPTION);
					}else {
						JOptionPane.showConfirmDialog(null, "Nous n'avons rencontre une erreur", "Sorry", JOptionPane.CLOSED_OPTION);
					}
				}else {
					JOptionPane.showConfirmDialog(null, "Cette étape se lance avec le bouton Validé !", "Erreur", JOptionPane.CLOSED_OPTION);
				}
			}
		});
		btnLancer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLancer.setBounds(739, 132, 89, 34);
		panel.add(btnLancer);
		
		
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tableEtapes.getSelectedRow();
				
				int idSelected = (int) tableEtapes.getValueAt(rowSelected, 0);
				
				boolean isDateDebutFormatOK = false;
				
				try {
					isDateDebutFormatOK = checkDateFormat( (String) tableEtapes.getValueAt(rowSelected, 1));
				}catch(ClassCastException e2) {
					JOptionPane.showConfirmDialog(null, "Vous n'avez pas effectue de modification", "Modification", JOptionPane.CLOSED_OPTION);
				}
				if(isDateDebutFormatOK) {
					try {
						
						java.sql.Date nouveauDateDebut = new java.sql.Date(sdf.parse( (String) tableEtapes.getValueAt(rowSelected, 1)).getTime());
						
						Etape etape = new Etape(idSelected, nouveauDateDebut, "");
						
						int isModifier = traitDao.updateDates(etape);
						if(isModifier == 1) {
							JOptionPane.showConfirmDialog(null, "Modification effectuee avec succes :)", "Modification", JOptionPane.CLOSED_OPTION);
							
						}else {
							JOptionPane.showConfirmDialog(null, "Modification non effectuee, quelque chose d'etrange s'est passee :( ", "Modification", JOptionPane.CLOSED_OPTION);
							
						}
					}catch(ParseException e1) {
						JOptionPane.showConfirmDialog(null, "Format de Date incorrect ", "Modification", JOptionPane.CLOSED_OPTION);
						
					}catch(ClassCastException e3) {
						JOptionPane.showConfirmDialog(null, "Vous n'avez pas effectue de modification", "Modification", JOptionPane.CLOSED_OPTION);
						
					}	
				}
				else {
					JOptionPane.showConfirmDialog(null, "Format de Date incorrect", "Modification", JOptionPane.CLOSED_OPTION);

				}

			}
		});
	}

	/**
	 * Cette méthode permet de retourner le boutton qui renvoit vers l'onglet 
	 * qui permet d'ajouter une dominante
	 * @return le bouton
	 */
	public JButton getBtnAdd() {
		return btnAdd;
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
