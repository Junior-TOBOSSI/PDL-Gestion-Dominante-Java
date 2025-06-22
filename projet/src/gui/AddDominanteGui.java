/**
 * Cette classe permet la gestion de l'onglet AJOUTER une dominante
 * 
 * Author Hilary BOCO - Gilbert TOBOSSI
 * 
 * Version 1.0
 * 
 */


package gui;
import model.*;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;

import dao.DominanteDao;

import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddDominanteGui extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	private JTextField caseId;
	private JTextField caseNomLong;
	private JTextField caseSigle;
	private JButton btnValider;
	private boolean ajout = false;
	private Dominante dom;

	public Dominante getDom() {
		return dom;
	}

	public void setDom(Dominante dom) {
		this.dom = dom;
	}

	public boolean getAjout() {
		return ajout;
	}

	public void setAjout(boolean ajout) {
		this.ajout = ajout;
	}

	/**
	 * Create the panel.
	 */
	public AddDominanteGui() {
		initialize();
	}
	
	public void initialize() {
        setBackground(new Color(255, 255, 255));
		setVisible(true);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(165, 54, 85));
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(165, 54, 85));
		panel_1.setBounds(40, 24, 759, 59);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel enteteDominante = new JLabel("AJOUTER UNE DOMINANTE");
		
		
		
		enteteDominante.setHorizontalAlignment(SwingConstants.CENTER);
		enteteDominante.setForeground(Color.WHITE);
		enteteDominante.setFont(new Font("Tahoma", Font.BOLD, 18));
		enteteDominante.setBackground(new Color(240, 240, 240));
		panel_1.add(enteteDominante);
		
		JPanel zoneInfos = new JPanel();
		zoneInfos.setBorder(null);
		zoneInfos.setLayout(null);
		zoneInfos.setBackground(new Color(165, 54, 85));
		zoneInfos.setBounds(40, 91, 759, 338);
		panel.add(zoneInfos);
		
		JLabel lblNewLabel_1 = new JLabel(" Id  ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 39, 130, 33);
		zoneInfos.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel(" Nom Long ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 83, 130, 33);
		zoneInfos.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Sigle ");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 133, 130, 33);
		zoneInfos.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Places Max ");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(10, 175, 130, 33);
		zoneInfos.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Places Max Apprentis  ");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3_1.setBounds(10, 219, 130, 33);
		zoneInfos.add(lblNewLabel_1_3_1);
		
		caseId = new JTextField();
		caseId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		caseId.setColumns(10);
		caseId.setBounds(161, 39, 571, 33);
		zoneInfos.add(caseId);
		
		caseNomLong = new JTextField();
		caseNomLong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		caseNomLong.setColumns(10);
		caseNomLong.setBounds(161, 83, 571, 33);
		zoneInfos.add(caseNomLong);
		
		caseSigle = new JTextField();
		caseSigle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		caseSigle.setColumns(10);
		caseSigle.setBounds(161, 133, 571, 33);
		zoneInfos.add(caseSigle);
		
		btnValider = new JButton(" Valider ");
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnValider.setBackground(new Color(162, 63, 99));
		btnValider.setBounds(355, 289, 115, 33);
		zoneInfos.add(btnValider);
		
		JSpinner casePlacesMax = new JSpinner();
		casePlacesMax.setFont(new Font("Tahoma", Font.PLAIN, 15));
		casePlacesMax.setBounds(161, 177, 571, 33);
		zoneInfos.add(casePlacesMax);
		
		JSpinner casePlacesMaxApprentis = new JSpinner();
		casePlacesMaxApprentis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		casePlacesMaxApprentis.setBounds(161, 221, 571, 33);
		zoneInfos.add(casePlacesMaxApprentis);
		
		JLabel lblNewLabel_2 = new JLabel("Données incorrectes !!!");
		lblNewLabel_2.setVisible(false);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(480, 293, 174, 25);
		zoneInfos.add(lblNewLabel_2);
		
		
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel_2.setVisible(false);
				
				try {
					int idEnter = Integer.parseInt(caseId.getText());
					String nomLongEnter = caseNomLong.getText().strip();
					String sigleEnter = caseSigle.getText().strip();
					int placeMaxEnter = Integer.parseInt(casePlacesMax.getModel().getValue().toString());
					int placeApprentisEnter = Integer.parseInt(casePlacesMaxApprentis.getModel().getValue().toString());
					
					
					if(nomLongEnter.length() < 50
							&& sigleEnter.length() < 10
							&& placeMaxEnter  < 35
							&& placeApprentisEnter  < 12){
						dom = new Dominante(idEnter, nomLongEnter, sigleEnter, placeMaxEnter, placeApprentisEnter);
						
						DominanteDao domDao = new DominanteDao();
						int state = domDao.add(dom);
						if(state == 0) {
							lblNewLabel_2.setText("Erreur de connexion !!!");
							lblNewLabel_2.setVisible(true);
							JOptionPane.showMessageDialog(null, "Erreur de connexion !!!", "Ajout..", JOptionPane.OK_OPTION);

						}
						else {
							lblNewLabel_2.setText("Opération réussie !");
							lblNewLabel_2.setVisible(true);
							ajout = true;
							JOptionPane.showMessageDialog(null, "Opération réussie !", "Ajout..", JOptionPane.OK_OPTION);
						}
					}
					else {
						lblNewLabel_2.setText("Données incorrectes !!!");
						lblNewLabel_2.setVisible(true);
					}
					
				}catch(NumberFormatException e1) {
					lblNewLabel_2.setText("Entrées invalides !!!");
					lblNewLabel_2.setVisible(true);
					JOptionPane.showMessageDialog(null, "Entrées invalides !!!", "Ajout..", JOptionPane.OK_OPTION);
				}
			}
		});
	}

	public JButton getBtnValider() {
		return btnValider;
	}

	public void setBtnValider(JButton btnValider) {
		this.btnValider = btnValider;
	}
	
}
