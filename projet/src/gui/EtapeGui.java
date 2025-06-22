/**
 * Cette classe permet la gestion de l'onglet Etape
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
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
import javax.swing.JSlider;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EtapeGui extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public EtapeGui() {
		
		
		setVisible(true);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(162, 63, 99));
		add(panel);
		
		// on crée le cadre pour acceuillir l'onglet AJOUTER une étape
		JPanel cadreInfosEtape = new JPanel();
		cadreInfosEtape.setBorder(null);
		cadreInfosEtape.setLayout(null);
		cadreInfosEtape.setBackground(new Color(162, 63, 99));
		cadreInfosEtape.setBounds(40, 91, 759, 338);
		panel.add(cadreInfosEtape);
		
		
		// On crée les labels pour renseigner l'utilisateur de quoi faire
		
		JLabel labelID = new JLabel(" Id  ");
		labelID.setHorizontalAlignment(SwingConstants.RIGHT);
		labelID.setForeground(new Color(0, 0, 0));
		labelID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelID.setBounds(10, 39, 130, 33);
		cadreInfosEtape.add(labelID);
		
		JLabel labelNom = new JLabel(" Nom ");
		labelNom.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNom.setForeground(new Color(0, 0, 0));
		labelNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelNom.setBounds(10, 83, 130, 33);
		cadreInfosEtape.add(labelNom);
		
		JLabel labelDdb = new JLabel(" Date de Debut ");
		labelDdb.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDdb.setForeground(new Color(0, 0, 0));
		labelDdb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelDdb.setBounds(10, 133, 130, 33);
		cadreInfosEtape.add(labelDdb);
		
		JLabel labelDdf = new JLabel(" Date de Fin ");
		labelDdf.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDdf.setForeground(new Color(0, 0, 0));
		labelDdf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelDdf.setBounds(10, 175, 130, 33);
		cadreInfosEtape.add(labelDdf);
		
		JLabel labelFilere = new JLabel(" Filiere  ");
		labelFilere.setHorizontalAlignment(SwingConstants.RIGHT);
		labelFilere.setForeground(new Color(0, 0, 0));
		labelFilere.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelFilere.setBounds(10, 219, 130, 33);
		cadreInfosEtape.add(labelFilere);
		
		// On crée des TextField pour receuillir les informations que l'utilisateur renseignent
		JTextField caseID = new JTextField();
		caseID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		caseID.setColumns(10);
		caseID.setBounds(161, 39, 571, 33);
		cadreInfosEtape.add(caseID);
		
		JTextField caseNom = new JTextField();
		caseNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		caseNom.setColumns(10);
		caseNom.setBounds(161, 83, 571, 33);
		cadreInfosEtape.add(caseNom);
		
		
		JSpinner caseDdb = new JSpinner();
		caseDdb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		caseDdb.setModel(new SpinnerDateModel(new Date(1741647600000L), new Date(1741647600000L), new Date(2120857200000L), Calendar.DAY_OF_YEAR));
		caseDdb.setBounds(161, 135, 571, 33);
		cadreInfosEtape.add(caseDdb);
		
		JSpinner caseDdf = new JSpinner();
		caseDdf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		caseDdf.setModel(new SpinnerDateModel(new Date(1741647600000L), new Date(1741647600000L), new Date(1963090800000L), Calendar.DAY_OF_YEAR));
		caseDdf.setBounds(161, 183, 571, 33);
		cadreInfosEtape.add(caseDdf);
		
		JSpinner caseFiliere = new JSpinner();
		caseFiliere.setFont(new Font("Tahoma", Font.PLAIN, 15));
		caseFiliere.setModel(new SpinnerListModel(new String[] {"FISE", "FISA"}));
		caseFiliere.setBounds(161, 227, 571, 33);
		cadreInfosEtape.add(caseFiliere);
		JLabel labelEntete = new JLabel("AJOUTER UNE ETAPE");
		labelEntete.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelEntete.setBounds(40, 23, 757, 57);
		panel.add(labelEntete);
		
		
		labelEntete.setHorizontalAlignment(SwingConstants.CENTER);
		labelEntete.setForeground(Color.WHITE);
		labelEntete.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelEntete.setBackground(new Color(240, 240, 240));
		
	
		// on crée le bouton valider pour l'envoi des informations
		JButton btnValider = new JButton(" Valider ");
		
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnValider.setBackground(new Color(162, 63, 99));
		btnValider.setBounds(355, 289, 115, 33);
		cadreInfosEtape.add(btnValider);
	}
}
