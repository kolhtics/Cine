package CinemaPackage;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class CineFrame extends Frame {
	final static int HAUTEUR = 200;
	final static int LARGEUR = 400;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;

	public CineFrame(Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		setTitle("Cinema");
		setSize(LARGEUR, HAUTEUR);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;

		/* Creation du panel qui va contenir les bouttons et les textfields */
		//Panel 1
		Panel panel1 = new Panel(new FlowLayout(FlowLayout.LEFT));
		Label LabelActeur1 = new Label("Acteur 1");
		TextField TextActeur1 = new TextField(20);


		panel1.add(LabelActeur1);
		panel1.add(TextActeur1);

		//Panel 2
		Panel panel2 = new Panel(new FlowLayout(FlowLayout.LEFT));
		Label LabelActeur2 = new Label("Acteur 2");
		TextField TextActeur2 = new TextField(20);

		panel2.add(LabelActeur2);
		panel2.add(TextActeur2);

		//Panel 3
		Panel panel3 = new Panel(new FlowLayout(FlowLayout.LEFT));
		Button bouttonJouer = new Button ("JOUER");

		panel3.add(bouttonJouer);


		/* Creation du panel qui va contenir le resultat 
		Panel panel4 = new Panel(new FlowLayout(FlowLayout.LEFT));
		panel4.setBackground(Color.WHITE);
		Label LabelRes = new Label("Votre resultat ici lol");

		
		panel4.add(LabelRes);*/

		Panel panel0 = new Panel(new GridLayout(3,1));
		panel0.add(panel1);
		panel0.add(panel2);
		panel0.add(panel3);

		this.add(panel0, BorderLayout.NORTH);
		//this.add(panel4, BorderLayout.CENTER);


		this.addWindowListener(new FermerFenetreEcouteur(this));
		bouttonJouer.addActionListener(new BoutonEcouteur(bouttonJouer, TextActeur1, TextActeur2, lesActeurs, lesFilms));

		this.setVisible(true);


	}



}