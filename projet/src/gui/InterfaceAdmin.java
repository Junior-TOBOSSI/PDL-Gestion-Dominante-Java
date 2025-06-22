/**
 * Cette classe permet la gestion de l'interface Administrateur
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */

package gui;
import model.*;

import javax.swing.JPanel;
import java.awt.Color;

import java.awt.Font;

import java.awt.GridLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class InterfaceAdmin extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Cet attribut permet d'accéder le bouton de déconnexion de l'admin
	 */
	private JButton btnDeconnexionAdmin;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet AJOUTER une Dominante
	 */
	private JButton btnAddPanelDominante;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet Forcer l'inscription d'un etudiant
	 */
	private JButton btnForcerEtudiant;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet AJOUTER un Etudiant
	 */
	private JButton btnAddPanelEtudiant;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet consulterChoix
	 */
	private JButton btnVoirInfoDominantes;
	
	/**
	 * Cet attribut permet d'ajouter une ligne à la table dominante
	 */
	private JButton btnValiderDominante;
	
	/**
	 * Cet attribut permet d'accéder au cadre qui contient les onglets
	 */
	private JPanel cadreContenuOnglet;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet Etudiant
	 */
	private ConsulterEtudiant panelEtudiant;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet Dominante 
	 */
	private ConsulterDominante panelDominante;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet Etape
	 */
	private ConsulterEtape panelEtape;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet AJOUTER une Dominante
	 */
	private AddDominanteGui addDominante;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet AJOUTER un Etudiant
	 */
	private AddEtudiantGui addEtudiant;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet profil de l'admin
	 */
	private ProfilAdminGui panelProfil;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet consulterChoix
	 */
	private ConsulterChoix panelChoixDominante;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet Forcer le choix d'un Etudiant
	 */
	private ForcerEtudiant forcerEtudiant;
	
	/**
	 * Cet attribut permet d'accéder à l'onglet Profil de l'admin
	 */
	private Admin admin;

	/**
	 * Cette méthode permet de retourner
	 * @return admin, l'administrateur qui est connecté
	 */
	public Admin getAdmin() {
		return admin;
	}

	/**
	 * Cette méthode permet de modifier 
	 * @param admin, par l'administrateur qui est connecté
	 */
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	/**
	 * Create the panel.
	 */

	public InterfaceAdmin() {


		setLayout(new GridLayout(0, 1, 0, 0));

		// Création du panel principal
		// panel mis en absolute layout
		// sert à éviter un affichage nulle
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		add(panel);
		
		
		// Création du menu Profil avec insertion des bouttons profil, deconnexion et quitter

		JPanel menuProfil = new JPanel();
		menuProfil.setBackground(panel.getBackground());
		menuProfil.setVisible(false);
		menuProfil.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuProfil.setBounds(30, 45, 122, 115);
		panel.add(menuProfil);
		menuProfil.setLayout(null);
		
		JButton btnProfil = new JButton("PROFIL");
		btnProfil.setBounds(10, 30, 107, 31);
		btnProfil.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnProfil.setBackground(new Color(162, 63, 99));
		menuProfil.add(btnProfil);
		btnProfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuProfil.setVisible(false);	
				setOngletProfil();
			}
		});
						
		JButton btnQuitter = new JButton("");
		btnQuitter.setIcon(new ImageIcon(InterfaceAdmin.class.getResource("/data/quitterIcone.jpg")));
		btnQuitter.setBounds(95, 0, 27, 23);
		btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuitter.setBackground(new Color(162, 63, 99));
		menuProfil.add(btnQuitter);
		
				btnDeconnexionAdmin = new JButton("DECONNEXION");
				btnDeconnexionAdmin.setBounds(10, 72, 107, 31);
				btnDeconnexionAdmin.setBackground(new Color(162, 63, 99));
				btnDeconnexionAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
				menuProfil.add(btnDeconnexionAdmin);
				
				
						btnQuitter.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								menuProfil.setVisible(false);	
							}
						});
		
		// Création de la barre des onglets Etape, Dominante, Etudiant
		
		JPanel barreOnglets = new JPanel();
		barreOnglets.setBounds(169, 0, 852, 56);
		panel.add(barreOnglets);
		barreOnglets.setLayout(new GridLayout(0, 3, 0, 0));
		
		// Création de l'onglet Dominante, avec un boutton cliquable
		
		JButton menuDomBtn = new JButton("Dominante ");
		menuDomBtn.setForeground(Color.WHITE);
		menuDomBtn.setBackground(new Color(192, 63, 99));
		barreOnglets.add(menuDomBtn);

		// Création de l'onglet Etudiant, avec un boutton cliquable
		
		JButton menuEtdBtn = new JButton("Etudiant ");
		menuEtdBtn.setForeground(Color.WHITE);
		menuEtdBtn.setBackground(new Color(192, 63, 99));
		barreOnglets.add(menuEtdBtn);

		// Création de l'onglet Etape, avec un boutton cliquable
		
		JButton menuEtpBtn = new JButton("Etape ");
		menuEtpBtn.setForeground(Color.WHITE);
		menuEtpBtn.setBackground(new Color(192, 63, 99));
		barreOnglets.add(menuEtpBtn);

		// Création du cadre servant à afficher le contenu de l'onglet des boutons du menu
		
		cadreContenuOnglet = new JPanel();
		cadreContenuOnglet.setBackground(new Color(192, 63, 99));
		cadreContenuOnglet.setBounds(106, 112, 869, 461);
		panel.add(cadreContenuOnglet);
		cadreContenuOnglet.setLayout(new GridLayout(1, 1, 0, 0));

		JButton iconeProfil = new JButton("");
		iconeProfil.setBackground(panel.getBackground());
		iconeProfil.setIcon(new ImageIcon(InterfaceAdmin.class.getResource("/data/login_icon.png")));
		iconeProfil.setBounds(10, 11, 56, 56);
		panel.add(iconeProfil);
		cadreContenuOnglet.revalidate();

		iconeProfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuProfil.setVisible(true);	
			}
		});


		menuEtpBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setOngletEtape();
			}
		});

		menuDomBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setOngletDominante();
			}
		});

		menuEtdBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setOngletEtudiant();
			}
		});
		}

	/**
	 * Cette méthode permet de retourner le bouton de déconnexion de l'administrateur
	 * @return btnDeconnexionAdmin
	 */
	public JButton getBtnDeconnexion() {
		return btnDeconnexionAdmin;
	}

	/**
	 * Cette méthode permet de remplacer dans le panel parent le nouveau panel enfant
	 * @param panelContainer  
	 * @param panelToAdd 
	 */
	public void reInitializeSubPanel(JPanel panelContainer, JPanel panelToAdd) {
		panelContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelContainer.setBackground(new Color(162, 63, 99));
		panelContainer.setForeground(new Color(255, 255, 255));
		panelContainer.removeAll();
		panelContainer.add(panelToAdd);	
		panelContainer.revalidate();
	}
		
	/**
	 * Cette méthode permet de remplacer le contenu du cadre par l'onglet Dominante
	 */
	public void setOngletDominante() {
		
		if(panelDominante == null) {
			   panelDominante = new ConsulterDominante();
			   initBtnAddDominante();
			   initBtnVoirInfoDominante();
		 }
		reInitializeSubPanel(cadreContenuOnglet, panelDominante);
	}
	
	/**
	 * Cette méthode permet d'instancier le bouton qui permet de voir les informations sur une dominante
	 */
	public void initBtnVoirInfoDominante() {
		if(panelDominante != null) {
			btnVoirInfoDominantes = panelDominante.getBtnVoir();
			btnVoirInfoDominantes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					panelChoixDominante = new ConsulterChoix(panelDominante.getDominanteSelected());
					
					reInitializeSubPanel(cadreContenuOnglet, panelChoixDominante);
				}
			});
		}
	}
	
	/***
	 * Cette méthode permet l'instanciation du bouton qui renvoit vers l'onglet ajouter une domiante
	 */
	public void initBtnAddDominante() {
		if(panelDominante != null) {
			btnAddPanelDominante = panelDominante.getBtnAdd();
			btnAddPanelDominante.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(addDominante == null) {
						addDominante = new AddDominanteGui();
					}
					reInitializeSubPanel(cadreContenuOnglet, addDominante);
					
					btnValiderDominante = addDominante.getBtnValider();
					
					   btnValiderDominante.addMouseListener(new MouseAdapter() {
						   @Override
						   public void mouseClicked(MouseEvent e) {
							   
							  if(addDominante.getAjout()) {
								  DefaultTableModel model = (DefaultTableModel) panelDominante.getTableDominantes().getModel();
								  
								  Dominante dom = addDominante.getDom();
								
								  model.insertRow(addDominante.getDom().getId() -1, new Object[]  {dom.getId(), dom.getNomLong(), dom.getSigle(), dom.getPlaceMax(), dom.getPlacesApprentis()});	
							  }
							   
						   }
					   });  
				}
			});
		}
	}
	
	/**
	 * Cette méthode permet de remplacer le contenu du cadre par l'onglet Etudiant
	 */
	public void setOngletEtudiant() {
	   if(panelEtudiant == null) {
		   panelEtudiant = new ConsulterEtudiant();
		   initBtnAddEtudiant();
		   initBtnForcerEtudiant();
	   }
		reInitializeSubPanel(cadreContenuOnglet, panelEtudiant);
		
	}
   
	/**
	 * Cette méthode permet de remplacer le contenu du cadre par l'onglet Etape
	 */
   public void setOngletEtape() {
		
	   if(panelEtape == null) {
		   panelEtape = new ConsulterEtape();
	   }
		reInitializeSubPanel(cadreContenuOnglet, panelEtape);
	}
   
   /***
	 * Cette méthode permet l'instanciation du bouton qui renvoit vers l'onglet ajouter un Etudiant
	 */
   public void initBtnAddEtudiant() {
	   if(panelEtudiant !=null) {
		   btnAddPanelEtudiant = panelEtudiant.getBtnAdd();
		   
		   btnAddPanelEtudiant.addMouseListener(new MouseAdapter() {
			   @Override
			   public void mouseClicked(MouseEvent e) {
				   if(addEtudiant == null) {
					   addEtudiant = new AddEtudiantGui(); 
				   }
				   reInitializeSubPanel(cadreContenuOnglet, addEtudiant);
			   }
		   });   
	   }
   }
   
   /**
    * Cette permet permet d'instancier le bouton qui permet d'accéder au panel ForcerEtudiant
    */
   public void initBtnForcerEtudiant() {
	   if(panelEtudiant !=null) {
		   btnForcerEtudiant = panelEtudiant.getBtnForcerInscription();
		   
		   btnForcerEtudiant.addMouseListener(new MouseAdapter() {
			   @Override
			   public void mouseClicked(MouseEvent e) {
				   if(forcerEtudiant == null) {
					   forcerEtudiant = new ForcerEtudiant(); 
				   }
				   reInitializeSubPanel(cadreContenuOnglet, forcerEtudiant);
			   }
		   });   
	   }
   }
  
   /**
	 * Cette méthode permet de remplacer le contenu du cadre par l'onglet Profil
	 */
   public void setOngletProfil() {
		
	   if(panelProfil == null) {
		   panelProfil = new ProfilAdminGui(admin);
	   }
		reInitializeSubPanel(cadreContenuOnglet, panelProfil);
	}

	
 
   
}