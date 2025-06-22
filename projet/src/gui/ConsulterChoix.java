/**
 * Cette classe permet la gestion de l'onglet consulter choix étudiants
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

import java.util.ArrayList;


import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;



public class ConsulterChoix extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private JTable tableEtudiantsDom;
	

	/**
	 * Create the panel.
	 */
	public ConsulterChoix(int iddom) {
		
		ChoixDao conChoix = new ChoixDao();
		
		// on recherche les id des étudiants ayant choisi cette dominante dans la table choix
		ArrayList<Integer> listIdEtudiants = conChoix.etudiantsParDominantes(iddom);
		
		// on initialise la connexion avec la base de données Etudiant pour récupérer pour chaque idEtudiant
		// l'étudiant concerné
		EtudiantDao conEtud = new EtudiantDao();
		
		// on initialise la connexion avec la base de données Dominante pour récupérer pour l'iddom
		// la dominante concernée
		DominanteDao domDao = new DominanteDao();
		
		Dominante dom = domDao.get(iddom);
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		add(panel);
		panel.setLayout(null);
		
		JLabel enteteChoix = new JLabel(dom.getNomLong() + " (" + dom.getSigle() + ")");
		enteteChoix.setForeground(new Color(255, 255, 255));
		
		enteteChoix.setFont(new Font("Tahoma", Font.PLAIN, 15));
		enteteChoix.setBounds(25, 11, 639, 37);
		panel.add(enteteChoix);
		
		JLabel labelEtat = new JLabel( listIdEtudiants.size() + " /" + dom.getPlaceMax());
		labelEtat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelEtat.setForeground(new Color(255, 255, 255));
		labelEtat.setBounds(708, 11, 139, 37);
		panel.add(labelEtat);
	
		tableEtudiantsDom = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IDETUDIANT", "NOM", "PRENOM", "A CHOISI EN ... POSITION"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
			
	   tableEtudiantsDom.setRowHeight(30);
		
		
		DefaultTableModel model = (DefaultTableModel) tableEtudiantsDom.getModel();
		
		for(int idEtud : listIdEtudiants) {
			
			Etudiant etud = conEtud.get(idEtud);
			
			// on récupère la position du choix de l'élève (1er, 2è, ... choix)
			
			int indexChoix = conChoix.indexChoixParEtudiant(idEtud, iddom);
			
			model.addRow(new Object[]  {etud.getId(), etud.getNom(), etud.getPrenom(), indexChoix});
			
		}
		
		
		JScrollPane scrollPane = new JScrollPane();
	
		scrollPane.setViewportView(tableEtudiantsDom);
		scrollPane.setBounds(10, 72, 853, 359);
		panel.add(scrollPane);	
		
		
		
	
		
	}

}
