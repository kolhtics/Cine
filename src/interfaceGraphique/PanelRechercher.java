package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

import cinemaPackage.Acteur;
import cinemaPackage.Film;
import cinemaPackage.Repertoire;
import ecouteurs.BoutonEcouteur;


public class PanelRechercher extends Panel{
	private static final long serialVersionUID = 1L;
	private TextArea textRes;

	public PanelRechercher(Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
	this.setLayout(new BorderLayout());
	this.setBackground(Color.LIGHT_GRAY);
	
	/* Creation du panel qui va contenir les bouttons et les textfields */
	//Panel 1
	Panel panel1 = new Panel(new GridLayout(1,3));
	Label LabelActeur1 = new Label("Acteur 1");
	TextField textActeur1 = new TextField("ok", 20);
	Button boutonRechercher = new Button ("Rechercher");

	panel1.add(LabelActeur1);
	panel1.add(textActeur1);
	panel1.add(boutonRechercher);



	// Creation du panel qui va contenir le resultat 
	Panel panel4 = new Panel(new BorderLayout());
	panel4.setBackground(Color.WHITE);
	textRes = new TextArea();
	textRes.setEditable(false);

	
	panel4.add(textRes);

	Panel panel0 = new Panel(new GridLayout(2,1));
	panel0.add(panel1);
	

	this.add(panel0, BorderLayout.NORTH);
	this.add(panel4, BorderLayout.CENTER);

	
	boutonRechercher.addActionListener(new BoutonEcouteur(this, textActeur1, lesActeurs, lesFilms));

	this.setVisible(true);

}

	public void setTexte(String s){
		textRes.setText(s);
		System.out.println(s);
		textRes.repaint();
	}
	
}
