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

import dao.DominanteDao;
import model.*;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;


public class ConsulterDominante extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Cet attribut permet de renvoyer le boutton 
	 * qui renvoit vers l'onglet pour AJOUTER une Dominante
	 */
	private JButton btnAdd;
	
	private JTable tableDominantes;
	
	/**
	 * Cet attribut permet de renvoyer le boutton 
	 * qui renvoit vers l'onglet pour consulter infos par dominantes
	 */
	private JButton btnVoir;
	
	private int idDominanteSelected;
	

	/**
	 * Create the panel.
	 */
	public ConsulterDominante() {
		
		DominanteDao conDom = new DominanteDao();
		
		ArrayList<Dominante> listDominantes = conDom.getAll();
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		add(panel);
		panel.setLayout(null);
		
		
		
		JButton btnDelete = new JButton("");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int confirm = JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir faire cette suppression", "Suppression", JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.OK_OPTION) {
					int rowSelected = tableDominantes.getSelectedRow();
					int idDominante = (int) tableDominantes.getValueAt(rowSelected, 0);
					if(idDominante == -1) {
						JOptionPane.showConfirmDialog(null, "Veuillez selectionner une dominante svp", "Warning", JOptionPane.CLOSED_OPTION);
					}else {
						int delete = conDom.delete(idDominante);
						if( delete == 0) {
							JOptionPane.showConfirmDialog(null, "Vous ne pouvez pas supprimer cette dominante !", "ERREUR", JOptionPane.CLOSED_OPTION);
						}else if (delete == 1) {
							JOptionPane.showConfirmDialog(null, "Suppression effectuee", "Suppression", JOptionPane.CLOSED_OPTION);
							DefaultTableModel model = (DefaultTableModel) tableDominantes.getModel();
							
							model.removeRow(rowSelected);
						}else {
							JOptionPane.showConfirmDialog(null, "Une erreur est survenu lors de la suppression de cette dominante", "ERREUR", JOptionPane.CLOSED_OPTION);
						}	
					}
				}
				
			}
		});
		btnDelete.setIcon(new ImageIcon(ConsulterDominante.class.getResource("/data/iconeDelete.jpg")));
	
		btnDelete.setBounds(796, 0, 43, 34);
		panel.add(btnDelete);
		
		btnAdd = new JButton("");
		
		btnAdd.setIcon(new ImageIcon(ConsulterDominante.class.getResource("/data/addIcone.jpg")));
		btnAdd.setBounds(746, 0, 50, 34);
		panel.add(btnAdd);
		
		JButton btnValider = new JButton("");
		
	
		
		btnValider.setIcon(new ImageIcon(ConsulterDominante.class.getResource("/data/validerIcone.jpg")));
		btnValider.setBounds(695, 0, 51, 34);
		panel.add(btnValider);
		
		
		tableDominantes = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "NOM LONG", "SIGLE", "PLACE MAX", "PL. APPRENTIS"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
			
	   tableDominantes.setRowHeight(30);
		
		
		DefaultTableModel model = (DefaultTableModel) tableDominantes.getModel();
		
		for(Dominante dom : listDominantes) {
			
			model.addRow(new Object[]  {dom.getId(), dom.getNomLong(), dom.getSigle(), dom.getPlaceMax(), dom.getPlacesApprentis()});	
		}
		
		
		JScrollPane scrollPane = new JScrollPane();
	
		scrollPane.setViewportView(tableDominantes);
		scrollPane.setBounds(10, 45, 853, 386);
		panel.add(scrollPane);	
		
		btnVoir = new JButton("VOIR");
		btnVoir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tableDominantes.getSelectedRow();
				try {
					idDominanteSelected = (int) tableDominantes.getValueAt(rowSelected, 0);
				}catch(ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showConfirmDialog(null, "Vous n'avez pas effectue de choix !!", "Erreur", JOptionPane.CLOSED_OPTION);
				}
			}
		});
		
		btnVoir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoir.setBounds(620, 0, 65, 34);
		panel.add(btnVoir);
		
		JLabel lblNewLabel = new JLabel("Choisisez une dominante et cliquez sur le bouton pour voir le positionnement des étudiants\r\n");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(54, 11, 542, 23);
		panel.add(lblNewLabel);
		
		
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tableDominantes.getSelectedRow();
				
				try {
					int nouveauID = (int) tableDominantes.getValueAt(rowSelected, 0);
					String nouveauNomLong = (String) tableDominantes.getValueAt(rowSelected, 1);
					String nouveauSigle = (String) tableDominantes.getValueAt(rowSelected, 2);
					int nouveauPlaceMax = (int) tableDominantes.getValueAt(rowSelected, 3);
					int nouveauPlaceApprentis = (int) tableDominantes.getValueAt(rowSelected, 4);
					
					Dominante dom = new Dominante(nouveauID, nouveauNomLong, nouveauSigle, nouveauPlaceMax, nouveauPlaceApprentis);
				    int isModifier = conDom.update(dom);
				    if(isModifier == 1) {
						JOptionPane.showConfirmDialog(null, "Modification effectuee avec succes", "Modification", JOptionPane.CLOSED_OPTION);

				    }
					
				}catch(ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showConfirmDialog(null, "Vous n'avez pas effectue de choix !!", "Erreur", JOptionPane.CLOSED_OPTION);
				}
	
			}
		});
	}

	/**
	 * Cette méthode permet de retourner le bouton qui renvoit vers l'onglet qui permet d'ajouter une dominante
	 * @return le bouton 
	 */
	public JButton getBtnAdd() {
		return btnAdd;
	}
	
	/***
	 * Cette méthode permet de retourner le bouton qui renvoit vers l'onglet qui permet de voir une dominante
	 * @return le bouton
	 */
	public JButton getBtnVoir() {
		return btnVoir;
	}
	
	/**
	 * Cette méthode permet de retourner le bouton qui permet de connaître la dominante sélectionnée
	 * @return id, l'idenfiant de la dominante
	 */
	public int getDominanteSelected() {
		return idDominanteSelected;
	}

	public JTable getTableDominantes() {
		return tableDominantes;
	}

	public void setTableDominantes(JTable tableDominantes) {
		this.tableDominantes = tableDominantes;
	}
}
