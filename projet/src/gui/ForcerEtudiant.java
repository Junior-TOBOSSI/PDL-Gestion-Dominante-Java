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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import dao.DominanteDao;
import dao.EtudiantDao;
import dao.TraitementDao;
import model.Dominante;
import model.Etudiant;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ForcerEtudiant extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JTable tableEtudiants;
	private JTable tableDominantes;
	private int idFiliere = 2;
	private ArrayList<Etudiant> listEtudiant;
	
	
	/**
	 * Create the panel.
	 */
	public ForcerEtudiant() {

		
		// Création de la connexion à la table étudiant de la base de données
		EtudiantDao conEtu= new EtudiantDao();
		
		// on initialise le traitement DAO
		TraitementDao traitDao = new TraitementDao();
		
		
		
		listEtudiant = conEtu.getAllWhithoutDom(idFiliere);
		
		DominanteDao conDom = new DominanteDao();
		
		ArrayList<Dominante> listDominantes = conDom.getAll();
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		add(panel);
		panel.setLayout(null);
		
		
		// Ce bouton permet de valider la saisie des nouvelles incormatins utilsateur
		JButton btnValider = new JButton("Inserer l'étudiant\r\n");
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnValider.setIcon(null);
		btnValider.setBounds(666, 9, 155, 34);
		panel.add(btnValider);
		
		
		
		
		
		tableEtudiants = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "NOM", "PRENOM"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		tableEtudiants.setRowHeight(30);
		tableEtudiants.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableEtudiants.getColumnModel().getColumn(1).setPreferredWidth(125);
		tableEtudiants.getColumnModel().getColumn(2).setPreferredWidth(125);
	
		
		DefaultTableModel modelEtudiant = (DefaultTableModel) tableEtudiants.getModel();
				
		for(Etudiant etud : listEtudiant) {
		
			modelEtudiant.addRow(new Object[]  {etud.getId(), etud.getNom(), etud.getPrenom()});	
		}
		
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 74, 406, 369);
		scrollPane.setViewportView(tableEtudiants);
		panel.add(scrollPane);
		
		tableDominantes = new JTable(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "SIGLE", "PLACE RESTANT"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, true, true
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
				
	   tableDominantes.setRowHeight(30);
		
		
		DefaultTableModel modelDominante = (DefaultTableModel) tableDominantes.getModel();
		
		
		
		for(Dominante dom : listDominantes) {
			
			if(traitDao.getPlaceRestante(idFiliere, dom.getId()) != 0){
				modelDominante.addRow(new Object[]  {dom.getId(), dom.getSigle(), traitDao.getPlaceRestante(idFiliere, dom.getId())});	
			}
		}
		
		tableDominantes.getColumnModel().getColumn(0).setPreferredWidth(15);
		
		JScrollPane scrollPaneDominante = new JScrollPane();
	
		scrollPaneDominante.setViewportView(tableDominantes);
		scrollPaneDominante.setBounds(481, 74, 366, 289);
		panel.add(scrollPaneDominante);
		
		JComboBox caseChoixFiliere = new JComboBox();
		
		caseChoixFiliere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if(caseChoixFiliere.getSelectedItem().equals("FISE")) {
					idFiliere = 1;
				}else if(caseChoixFiliere.getSelectedItem().equals("FISA")) {
					idFiliere = 2;
				}
				
				if(idFiliere == 1 || idFiliere == 2) {
					
					
					for (int i = 0; i < 300; i++) {
						try {
							modelEtudiant.removeRow(0);
						}catch(ArrayIndexOutOfBoundsException e1) {
							break;
						}
						
					}
					
					listEtudiant = conEtu.getAllWhithoutDom(idFiliere);
			
							
					for(Etudiant etud : listEtudiant) {
						
						
						modelEtudiant.addRow(new Object[]  {etud.getId(), etud.getNom(), etud.getPrenom()});	
					}
					
					
					for (int i = 0; i < 300; i++) {
						try {
							modelDominante.removeRow(0);
						}catch(ArrayIndexOutOfBoundsException e1) {
							break;
						}
						
					}
					
					for(Dominante dom : listDominantes) {
						
						if(traitDao.getPlaceRestante(idFiliere, dom.getId()) != 0){
							modelDominante.addRow(new Object[]  {dom.getId(), dom.getSigle(), traitDao.getPlaceRestante(idFiliere, dom.getId())});	
						}
					}
					
	
				}
			}
		});
		caseChoixFiliere.setForeground(new Color(255, 0, 0));
		caseChoixFiliere.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		caseChoixFiliere.setModel(new DefaultComboBoxModel(new String[] {"FISA", "FISE"}));
		caseChoixFiliere.setBounds(324, 11, 192, 30);
		panel.add(caseChoixFiliere);
		
		JLabel labelChoixFiliere = new JLabel("CHOISSISSEZ UNE FILIERE");
		labelChoixFiliere.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelChoixFiliere.setBounds(40, 11, 229, 30);
		panel.add(labelChoixFiliere);
		
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				
				int rowSelectedEtudiant = tableEtudiants.getSelectedRow();
				int rowSelectedDominante = tableDominantes.getSelectedRow();
				
				
				// si la modification est confirmé 
				if(rowSelectedEtudiant >=0  && rowSelectedDominante >= 0) {
					
					// on demande une confirmation de modification
					int confirm = JOptionPane.showConfirmDialog(null, "Confirmez cette insertion ?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
					
					if(confirm == 0) {
						
						int nouveauEtudiantID = (int) tableEtudiants.getValueAt(rowSelectedEtudiant, 0);
						
						int nouveauDominanteID  = (int) tableDominantes.getValueAt(rowSelectedDominante, 0);
					

						
						int isModifier = conEtu.updateDom(nouveauDominanteID, nouveauEtudiantID);
						
						if(traitDao.getPlaceRestante(idFiliere, nouveauDominanteID) == 0) {
							modelDominante.removeRow(rowSelectedDominante);
						}else {
							modelDominante.setValueAt(traitDao.getPlaceRestante(idFiliere, nouveauDominanteID), rowSelectedDominante, 1);
						}
					
						if(isModifier == 1) {
							modelEtudiant.removeRow(rowSelectedEtudiant);
							JOptionPane.showConfirmDialog(null, "Modification effectuee avec succes :)", "Modification", JOptionPane.CLOSED_OPTION);
							
						}else {
							JOptionPane.showConfirmDialog(null, "Modification non effectuee, quelque chose d'etrange s'est passee :( ", "Modification", JOptionPane.CLOSED_OPTION);	
						}
					}
	
				}
		
			}
		});
			
		
	}
}