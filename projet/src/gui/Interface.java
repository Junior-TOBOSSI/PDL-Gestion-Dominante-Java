/**
 * Cette classe permet la gestion de l'interface Princiale
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */

package gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.TraitementDao;
import model.*;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Interface {

	// Composants généraux
	private JFrame frmChoixDominantes;
	private int connection;
	
	// Déclaration des bouttons retournés par les panels intérieurs
	private JButton btnDeconnexionEtudiant;
	private JButton btnConnexion;
	private JButton btnDeconnAcceuilAdmin;
	private JButton btnDominateFrAcc;
	private JButton btnEtapeFrAcc;
	private JButton btnEtudiantFrAcc;
	private JButton btnDeconnAdminInterface;
	private JButton btnDeconnEtudInterface;
	private JButton btnChoix;
	private JButton btnDominanteFinale ;
	private JButton btnProfilEtudiant;
	private JButton btnProfilAdmin;
	
	// Déclarations des panels intérieurs
	private JPanel panel;
	private AcceuilAdminGui acceuilAdmin;
	private AuthentificationGui authen;
	private AcceuilEtudiantGui acceuilEtu;
	private InterfaceAdmin adminInterface;
	private InterfaceEtudiant etudInterface;
	
	// Déclaration des profils etudiant et Admin
	private Etudiant profilEtudiant;
	private Admin profilAdmin;
	
	// Déclaration de l'interface
	private static Interface window;
	
	// Déclaration de l'instance de vérification des accès
	TraitementDao traitDao;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Interface();
					window.frmChoixDominantes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmChoixDominantes = new JFrame();
		frmChoixDominantes.getContentPane().setForeground(new Color(255, 255, 255));
		frmChoixDominantes.getContentPane().setBackground(new Color(255, 255, 255));
		frmChoixDominantes.setBackground(new Color(192, 63, 99));
		frmChoixDominantes.setResizable(false);
		frmChoixDominantes.setTitle("Choix Dominantes");
		frmChoixDominantes.setBounds(100, 100, 1001, 634);
		frmChoixDominantes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChoixDominantes.setLocationRelativeTo(null);
		frmChoixDominantes.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		
		
		panel = new JPanel();
		
		frmChoixDominantes.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		frmChoixDominantes.setVisible(true);
		
		traitDao = new TraitementDao();
		
		
		// Instanciation du panel d'authentification et ajout comme panel de base
		
		if(authen == null) {
			authen = new AuthentificationGui();
			panel.add(authen);
			initButtonConnexion();
		}
		
	}
	
	/**
	 * Cette méthode permet de changer le contenu de
	 * @param panelContainer qui est le conteneur parent en elèvant 
	 * @param panelToRemove et en le remplaçant par 
	 * @param panelToAdd le panel à ajouter
	 */
	public void reInitializePanel(JPanel panelContainer, JPanel panelToRemove, JPanel panelToAdd) {
		
		
		checkRemove(panelContainer, panelToRemove);
		panelContainer.removeAll();
		panelContainer.add(panelToAdd);
		panelContainer.revalidate();
		panelContainer.repaint();
	}
	
	/**
	 * Cette méthode permet de verifier si le panel 
	 * @param panelToRemove à enlèver de 
	 * @param panelContainer est null
	 * et à l'enlèver sinon
	 */
	public void checkRemove(JPanel panelContainer, JPanel panelToRemove) {
		
		if(panelToRemove != null) {
			panelContainer.remove(panelToRemove);
		}
	}
	
	/**
	 * Cette méthode permet d'initialiser le panel AcceuilAdmin
	 */
	public void initAcceuilAdminGui() {
		
		if(acceuilAdmin == null) {
			// Création du panel
			acceuilAdmin = new AcceuilAdminGui();
			initButtonDeconnAcceuilAdmin();
			initBtnProfilAdmin();
			
			// Initialisation des différents boutons
			btnDominateFrAcc = acceuilAdmin.getBtnDominante();
			btnEtudiantFrAcc = acceuilAdmin.getBtnEtudiant();
			btnEtapeFrAcc = acceuilAdmin.getBtnEtape();
			
			// Paramétrage de boutons qui renvoit vers les informations Dominantes
			btnDominateFrAcc.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					initInterfaceAdmin();
					adminInterface.setOngletDominante();
				}
			});
			
			// Paramétrage de boutons qui renvoit vers les informations Etudiants
			btnEtudiantFrAcc.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					initInterfaceAdmin();
					adminInterface.setOngletEtudiant();
				}
			});
			
			// Paramétrage de boutons qui renvoit vers les informations Etapes
			btnEtapeFrAcc.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					initInterfaceAdmin();
					adminInterface.setOngletEtape();
				}
			});
		}
	}
	
	/**
	 * Cette méthode permet de changer le contenu du panel principal par le panel d'interface de l'admin
	 * Elle crée le panel elle ne l'est pas encore
	 */
	public void initInterfaceAdmin() {
		if(adminInterface == null) {
			adminInterface = new InterfaceAdmin();
			adminInterface.setAdmin(profilAdmin);
			initButtonDeconnAdminInterface();
		}
		reInitializePanel(panel, null, adminInterface);
		
	}
	
	/**
	 * Cette méthode permet d'initialiser le bouton
	 * qui renvoit vers l'onglet profil Etudiant
	 */
	public void initBtnProfilEtudiant() {
		if(acceuilEtu != null) {
			
			btnProfilEtudiant  = acceuilEtu.getBtnProfil();
				
			btnProfilEtudiant.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						initInterfaceEtudiant();
						etudInterface.setOngletProfil();
						reInitializePanel(panel, null, etudInterface);
					}
				});		
			}
	}
	
	/**
	 * Cette méthode permet d'initialiser le bouton
	 * qui renvoit vers l'onglet profil Administrateur
	 */
	public void initBtnProfilAdmin() {
		if(acceuilAdmin != null) {
			
			btnProfilAdmin  = acceuilAdmin.getBtnProfilAcc();
				
					btnProfilAdmin.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						initInterfaceAdmin();
						adminInterface.setOngletProfil();
						reInitializePanel(panel, null, adminInterface);
					}
				});		
			}
	}
	
	/**
	 * Cette méthode permet d'initialiser l'interface
	 * d'un étudiant
	 */
	public void initInterfaceEtudiant() {
		if(etudInterface == null) {
			etudInterface = new InterfaceEtudiant(profilEtudiant);
			etudInterface.setEtud(profilEtudiant);
			initButtonDeconnEtudInterface();
		}
		reInitializePanel(panel, null, etudInterface);
		
	}
	
	
	/**
	 * Cette méthode permet d'initialiser linterface d'acceuil 
	 * d'un étudiant
	 */
	public void initAcceuilEtudiantGui() {
		
		if(acceuilEtu == null) {
			// Création du panel
			acceuilEtu = new AcceuilEtudiantGui();
			
			// Initialisation  et Paramétrage de boutons des différents boutons
			initBouttonchoix();
			initButtonChoixFinal();
			initButtonDeconnAcceuilEtudiant();
			initBtnProfilEtudiant();
	
		}
		
	}

	/**
	 *Cette méhode permet le paramétrage du bouton de déconnexion de l'acceuil de l'Etudiant
	 */
	public void initButtonDeconnAcceuilEtudiant() {
		if(acceuilEtu != null) {
			btnDeconnexionEtudiant =  acceuilEtu.getBtnDeconnexionEtudiant();
			btnDeconnexionEtudiant.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					window.frmChoixDominantes.setVisible(false);
					window = new Interface();
					//reInitializePanel(panel, acceuilEtu, authen);
					
				}
			});	
		}
	}
	
	/**
	 * Cette méthode permet d'initialiser le boutton 
	 * qui renvoit vers l'onglet de choix final
	 */
	public void initButtonChoixFinal(){
	if(acceuilEtu != null) {
			
		btnDominanteFinale  = acceuilEtu.getBtnDominanteFinale();
			
		btnDominanteFinale.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					initInterfaceEtudiant();
					etudInterface.setOngletDominanteFinale();
					reInitializePanel(panel, null, etudInterface);
				}
			});		
		}
	}
	
	/**
	 *Cette méthode permet le paramétrage du bouton de déconnexion de l'acceuil de l'Admin
	 */
	public void initButtonDeconnAcceuilAdmin() {
		if(acceuilAdmin != null) {
			btnDeconnAcceuilAdmin = acceuilAdmin.getBtnDeconnexion();
			btnDeconnAcceuilAdmin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					window.frmChoixDominantes.setVisible(false);
					window = new Interface();
					//reInitializePanel(panel, acceuilAdmin, authen);
				}
			});
		}
	}
	
	
	/**
	 *Cette méthode permet le paramétrage du bouton de déconnexion de l'interface Admin
	 */
	public void initButtonDeconnAdminInterface() {
		if(adminInterface != null) {
			btnDeconnAdminInterface = adminInterface.getBtnDeconnexion();
			btnDeconnAdminInterface.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					window.frmChoixDominantes.setVisible(false);
					window = new Interface();
					//reInitializePanel(panel, adminInterface, authen);
				}
			});
		}
	}
	
	/**
	 * Cette méthode permet d'initialiser le boutton 
	 * qui renvoit vers l'interface d'authentification
	 */
	public void initButtonDeconnEtudInterface() {
		if(etudInterface != null) {
			btnDeconnEtudInterface = etudInterface.getBtnDeconnexion();
			btnDeconnEtudInterface.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					window.frmChoixDominantes.setVisible(false);
					window = new Interface();
					//reInitializePanel(panel, adminInterface, authen);
				}
			});
		}
	}
	
	
	/**
	 * Cette méthode permet d'initialiser le boutton 
	 * qui renvoit vers l'onglet de choix
	 */
	public void initBouttonchoix() {
		if(acceuilEtu != null) {
			
			btnChoix = acceuilEtu.getBtnChoix();
			
			btnChoix.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					initInterfaceEtudiant();
					etudInterface.setOngletChoix();
					reInitializePanel(panel, null, etudInterface);
	
				}
			});		
		}
	}
	

	
	/**
	 * Cette méthode permet de paramétrer le bouton de connexion du panel authentification
	 */
	public void initButtonConnexion() {
		if(authen != null) {
			
				btnConnexion = authen.getBtnConnexion();
				btnConnexion.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						connection = authen.getBalise();	
						
						if(connection == 1) {
							if(acceuilAdmin == null) {
								initAcceuilAdminGui();
							}
							
							profilAdmin = authen.getProfilAdmin();
							reInitializePanel(panel, authen, acceuilAdmin);
						}else {
							if(connection == 2) {
								
								profilEtudiant = authen.getProfilEtu();
								
								if(traitDao.isAccessible(profilEtudiant)) {
									if(acceuilEtu == null) {
										
										initAcceuilEtudiantGui();
									}
									
									reInitializePanel(panel, authen, acceuilEtu);
									
								}else {
									JOptionPane.showConfirmDialog(null, "Acces interdit, la plateforme est fermee à votre filiere  :(", "Connection", JOptionPane.CLOSED_OPTION);
								}
						
									
							}
						}
					}
				});	
			}
		}
	
}