/**
 * Cette classe permet la gestion de l'onglet Choix des étudiants
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */

package gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.util.ArrayList;

import dao.ChoixDao;
import dao.DominanteDao;
import dao.EtudiantDao;
import model.Etudiant;

public class ChoixEtudiant extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Cet attribut permet de compter le choix qu'un étudiant effectue
	 */
	private int compteur = 1;
	
	/**
	 * Cet attribut représente le cadre dans lequel les onglets sont affichés
	 */
	private JPanel cadrePanel;
	
	/**
	 * Cet attribut contient la liste des dominantes que l'étudiant a choisi
	 */
	private ArrayList<JLabel> listLabelsDom;
	
	/**
	 * Cet attribut permet de faire la connexion avec la table Dominante
	 */
	private DominanteDao conDom = new DominanteDao();
	
	/**
	 * Cet attribut permet de faire la connexion avec la table Dominante
	 */
	private ChoixDao conChoix = new ChoixDao();
	
	/**
	 * Cet attribut permet d'attribuer le nombre de choix maximal
	 * à un étudiant en fontion de sa flilière
	 * Initialement mis à 5
	 */
	private int nbrChoix = 5;
	
	/**
	 * Cet attribut stocke le choix des étudiants
	 */
	private int[] tableChoix;
	
	/**
	 * Cet attribut permet d'accéder à l'étudiant connecté
	 */
	private Etudiant etud;

	/**
	 * Create the panel.
	 * @param string 
	 */
	public ChoixEtudiant(Etudiant etud) {

		setEtud(etud);
		setBackground(new Color(255, 255, 255));
		
		setVisible(true);
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		cadrePanel = new JPanel();
		cadrePanel.setBounds(10, 21, 682, 433);
		panel.add(cadrePanel);
		cadrePanel.setLayout(new GridLayout(conDom.getAll().size(), 0, 0, 0));
		
		//JScrollPane scrollPane = new JScrollPane(cadrePanel);
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//panel.add(scrollPane);
		
		// on vérifie si l'étudiant à déjà effectuer des choix
		if(!isChoicesMade()) {
			
			// On instancie le bouton de validation
			JButton btnValider = new JButton("Valider");
			btnValider.setBounds(702, 51, 138, 37);
			
			panel.add(btnValider);
			btnValider.setBackground(new Color(192, 63, 99));
			btnValider.setFont(new Font("Tahoma", Font.PLAIN, 21));
			
			// on instancie le boutton pour permettre à un utilisateur de 
			// reprendre ses choix si besoins
			JButton btnReprendre = new JButton("Reprendre");
			btnReprendre.setBounds(702, 129, 138, 37);
			panel.add(btnReprendre);
			
			// on paramètre le bouton 
			btnReprendre.setBackground(new Color(192, 63, 99));
			btnReprendre.setFont(new Font("Tahoma", Font.PLAIN, 21));
			
			btnReprendre.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					listLabelsDom = new ArrayList<>();
					cadrePanel.removeAll();
					
					remplirPanel();
					
					panel.revalidate();
					panel.repaint();
					compteur = 1;
					
					afficherOption();
				}
			});
			
			// on paramètre le bouton de validation
			btnValider.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(compteur -1 != nbrChoix) {
						String textAfficher = "Il vous reste " + (5 - compteur +1) + " choix a effectuer";
						JOptionPane.showConfirmDialog(null, textAfficher, "Incomplet...", JOptionPane.DEFAULT_OPTION);
					}
					else {
						if(!isChoicesMade()) {
							int confirm = JOptionPane.showConfirmDialog(null,
									"Il n'aura pas de retour en arriere possible, Etes vous sur de vouloir valider votre choix ? ",
									"Envoi...", JOptionPane.DEFAULT_OPTION);
							if(confirm == 0) {
								
								
								int idChoix = conChoix.count() + 1;
								int valid = conChoix.add(tableChoix, etud.getId(), idChoix);
								
								
								if(valid == nbrChoix) {
									JOptionPane.showConfirmDialog(null,
											" Vos choix ont bien été pris en compte. :) ",
											"Validation", JOptionPane.DEFAULT_OPTION);
								}
								
								
							}	
						}else {
							JOptionPane.showConfirmDialog(null,
									" Vos choix ont déjà été pris en compte. :) ",
									"Validation", JOptionPane.DEFAULT_OPTION);
						}
					}
				}
			});
		
		
			
	
			listLabelsDom = new ArrayList<>();
			
			remplirPanel();
			
			afficherOption();
		}else {
			
			
			listLabelsDom = new ArrayList<>();
			
			JLabel labelDom = new JLabel();
			labelDom.setText("Vous avez choisi : ");
			cadrePanel.add(labelDom);
	
			int id = etud.getId();
			remplirPanelChoix(id);
		}
		

	}
		
	/**
	 * Cette méthode permet d'afficher les options de dominantes disponibles 
	 * Si une dominante est sélectionnné, la méthode permet d'afficher à coté l'ordre de ce jour
	 * Le format de l'affichage est idDominante : nomDominante - (ordre du choix)
	 */
	public void afficherOption() {
		for(JLabel labelDom : listLabelsDom) {
			
			labelDom.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if(compteur > nbrChoix) {
						JOptionPane.showConfirmDialog(null, "Nombres de choix maximum atteint", "Erreur", JOptionPane.DEFAULT_OPTION);
					}else {
						if(!labelDom.getText().contains("-")) {
							
							int idDom = Integer.parseInt(labelDom.getText().strip().split(":")[0].strip());
							
							tableChoix[compteur - 1] = idDom;
							
							labelDom.setText(labelDom.getText() + "  " + " -    " + String.valueOf(compteur));
							labelDom.setBorder(new LineBorder(new Color(34, 76, 245)));
							compteur++;
						}
					}
				}
			});
		}
	}
	
	
	/***
	 * Cette méthode permet de remplir le panel conteneur avec les dominantes récupérer le table Dominante
	 */
	public void remplirPanel() {
		
		for(int i = 0; i < conDom.getAll().size(); i++) {
			
			// un crée un label pour la dominante
			JLabel labelDom = new JLabel();
			labelDom.setText(" " + conDom.getAll().get(i).getId() + " : " + conDom.getAll().get(i).getNomLong() + " (" + conDom.getAll().get(i).getSigle() + " )");
			listLabelsDom.add(i, labelDom);
			cadrePanel.add(listLabelsDom.get(i));	
		}
	}
	
	/**
	 * Cette méthode permet de remplir le panel conteneur avec les dominantes que l'étudiant à choisir
	 * Dans le cas où le choix est déja effectif. 
	 * Il prend en paramètre l'étudiant et recherche ses dominantes
	 * @param id l'identifiant de l'étudiant 
	 */
	public void remplirPanelChoix(int id) {
		
		
		for(int i = 0; i < conDom.getAllChoix(id).size(); i++) {
			
			// un crée un label pour la dominante
			JLabel labelDom = new JLabel();
			labelDom.setText(" " + conDom.getAllChoix(id).get(i).getId() + " : " + conDom.getAllChoix(id).get(i).getNomLong() + " (" + conDom.getAllChoix(id).get(i).getSigle() + " )");
			listLabelsDom.add(i, labelDom);
			cadrePanel.add(listLabelsDom.get(i));	
		}
	}
	

	/***
	 * Cette méthode permet de savoir si un étudiant à déjà effectuer son choix ou non
	 * 
	 * @return True si l'étudiant à déjà effectuer un choix, false sinon
	 */
	public boolean isChoicesMade() {
		if(conChoix.isChoixMade(etud.getId())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Cette méthode permet d'accéder à l'étudiant qui s'est connecté et modifie son nombre de choix maximal en fonction de sa filière
	 * @param etud
	 */
	public void setEtud(Etudiant etud) {
		this.etud = etud;
		
		if(etud != null) {
			if(etud.getfiliere() == 1) {
				nbrChoix = 5;
				tableChoix = new int[nbrChoix];
			}
			else {
				nbrChoix = 2;
				tableChoix = new int[nbrChoix];
			}
		}
	}
	
	/**
	 * Cette méthode permet de retourner l'étudiant connecté
	 * @return etud
	 */
	public Etudiant getEtud() {
		 return etud;
	}
}