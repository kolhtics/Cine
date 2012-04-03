package Interface;

import java.awt.*;
import java.util.Queue;

import CinemaPackage.*;
import Interface.*;
import Algorithme.*;
import Ecouteurs.*;


public class PanelBot extends Panel{
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;
	private TextArea textRes;

	public PanelBot(Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.LIGHT_GRAY);
		
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;

		/* Creation du panel qui va contenir les bouttons et les textfields */
		//Panel 1
		Panel panel1 = new Panel(new GridLayout(1,5)); //ajouter bouton back
		Label LabelActeur1 = new Label("Acteur 1");
		TextField textActeur1 = new TextField("ok", 20);
		Label LabelActeur2 = new Label("Acteur 2");
		TextField textActeur2 = new TextField("b", 20);
		Button bouttonJouer = new Button ("Resoudre");

		panel1.add(LabelActeur1);
		panel1.add(textActeur1);
		panel1.add(LabelActeur2);
		panel1.add(textActeur2);
		panel1.add(bouttonJouer);



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

		
		bouttonJouer.addActionListener(new BoutonEcouteur(this, textActeur1, textActeur2, lesActeurs, lesFilms));

		this.setVisible(true);

	}
	
	public void setTexte(String s){
		textRes.setText(s);
		System.out.println(s);
		textRes.repaint();
	}

	

}