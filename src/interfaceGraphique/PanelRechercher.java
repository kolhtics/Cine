package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.Vector;


import cinemaPackage.Acteur;
import cinemaPackage.Film;
import cinemaPackage.Repertoire;
import ecouteurs.BoutonEcouteur;
import ecouteurs.SourisEcouteur;


public class PanelRechercher extends Panel{
	private static final long serialVersionUID = 1L;
	private TextArea textRes;
	private List result;

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
	
	panel1.add(LabelActeur1);
	panel1.add(textActeur1);
	panel1.add(boutonRechercheListe);
	panel1.add(boutonRechercherA);
	panel1.add(boutonRechercherF);


	// Creation du panel qui va contenir le resultat 
	Panel panel4 = new Panel(new GridLayout(2,1));
	panel4.setBackground(Color.WHITE);
	
	textRes = new TextArea("Notes \n\n\nAfficher : affiche un acteur ou un film donné (recherchez d'abord le film grâce à la recherche) \n\n"+
			               "Rechercher un Acteur : recherche un acteur dont vous connaissez une partie du nom et/ou prénom \n\n"+
						   "Rechercher un film : recherche un film dont vous connaissez une partie du titre \n\n");
	textRes.setEditable(false);

	
	result = new List(20);
	
	panel4.add(result);
	panel4.add(textRes);
	Panel panel0 = new Panel(new GridLayout(2,1));
	panel0.add(panel1);
	

	this.add(panel0, BorderLayout.NORTH);
	this.add(panel4, BorderLayout.CENTER);

	result.addActionListener(new SourisEcouteur(result,textRes, lesActeurs, lesFilms));
	boutonRechercheListe.addActionListener(new BoutonEcouteur(this, textActeur1, lesActeurs, lesFilms, result));
	boutonRechercherA.addActionListener(new BoutonEcouteur(this, textActeur1, lesActeurs, lesFilms, result));
	boutonRechercherF.addActionListener(new BoutonEcouteur(this, textActeur1, lesActeurs, lesFilms, result));
	this.setVisible(true);

}

	public void setTexte(String s){
		textRes.setText(s);
		System.out.println(s);
		textRes.repaint();
	}
	
	public void setConsole(Acteur acteur) {
		textRes.setText(acteur.toString_full());
	}
	
	public void setListeA(Vector<Acteur> v){
		result.removeAll();
		for (int i=0 ; i< v.size() ; i++){
			result.add(v.elementAt(i).toString());
		}
	}
	public void setListeF(Vector<Film> v){
		result.removeAll();
		for (int i=0 ; i< v.size() ; i++){
			result.add(v.elementAt(i).toString());
		}
	}
	
}
