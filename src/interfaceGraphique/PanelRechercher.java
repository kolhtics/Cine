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
	Panel panel1 = new Panel(new GridLayout(1,7));
	Label LabelActeur1 = new Label("Acteur");
	TextField textActeur1 = new TextField("", 20);
	Button boutonRechercheListe = new Button ("Afficher");
	Button boutonRechercherA = new Button ("Rechercher un Acteur");
	Button boutonRechercherF = new Button ("Rechercher un Film");
	Button test = new Button ("Test");
	
	panel1.add(LabelActeur1);
	panel1.add(textActeur1);
	panel1.add(boutonRechercheListe);
	panel1.add(boutonRechercherA);
	panel1.add(boutonRechercherF);
	panel1.add(test);


	// Creation du panel qui va contenir le resultat 
	Panel panel4 = new Panel(new BorderLayout());
	panel4.setBackground(Color.WHITE);
	textRes = new TextArea(" Notes \n\n\n Afficher : affiche un acteur ou un film donnés (recherchez d'abord le film grâce à la recherche) \n\n"+
			               "Rechercher un Acteur : recherche un acteur dont vous connaissez une partie du nom et/ou prénom \n\n"+
						   "Rechercher un film : recherche un film dont vous connaissez une partie du titre \n\n"+
						   "Recherche complete : cf ci-dessus , et affiche également quelques suggestions \n");
	textRes.setEditable(false);

	
	panel4.add(textRes);

	Panel panel0 = new Panel(new GridLayout(2,1));
	panel0.add(panel1);
	

	this.add(panel0, BorderLayout.NORTH);
	this.add(panel4, BorderLayout.CENTER);

	boutonRechercheListe.addActionListener(new BoutonEcouteur(this, textActeur1, lesActeurs, lesFilms));
	boutonRechercherA.addActionListener(new BoutonEcouteur(this, textActeur1, lesActeurs, lesFilms));
	boutonRechercherF.addActionListener(new BoutonEcouteur(this, textActeur1, lesActeurs, lesFilms));
	test.addActionListener(new BoutonEcouteur(this, textActeur1, lesActeurs, lesFilms));
	
	this.setVisible(true);

}

	public void setTexte(String s){
		textRes.setText(s);
		System.out.println(s);
		textRes.repaint();
	}
	
}
