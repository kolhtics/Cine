package CinemaPackage;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;


public class CineFrame2 extends Frame {
	final static int HAUTEUR = 600;
	final static int LARGEUR = 564;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;

	public CineFrame2(Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		setTitle("Cinema");
		setSize(LARGEUR, HAUTEUR);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.LIGHT_GRAY);
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;

		/* Creation du panel qui va contenir les bouttons et les textfields */
		//Panel 1
		Panel panel1 = new Panel(new GridLayout(1,5)); //ajouter bouton back
		Label LabelActeur1 = new Label("Acteur 1");
		TextField TextActeur1 = new TextField(20);
		Label LabelActeur2 = new Label("Acteur 2");
		TextField TextActeur2 = new TextField(20);
		Button bouttonJouer = new Button ("JOUER");

		panel1.add(LabelActeur1);
		panel1.add(TextActeur1);
		panel1.add(LabelActeur2);
		panel1.add(TextActeur2);
		panel1.add(bouttonJouer);



		// Creation du panel qui va contenir le resultat 
		Panel panel4 = new Panel(new BorderLayout());
		panel4.setBackground(Color.WHITE);
		Label resultat = new Label("ici votre resultat");
		TextArea TextRes = new TextArea("");

		
		panel4.add(TextRes);

		Panel panel0 = new Panel(new GridLayout(3,1));
		panel0.add(panel1);
		
		Button boutonRetour = new Button ("Menu");

		this.add(panel0, BorderLayout.NORTH);
		this.add(panel4, BorderLayout.CENTER);
		this.add(boutonRetour, BorderLayout.SOUTH);

		bouttonJouer.addActionListener(new BoutonEcouteur(bouttonJouer, TextActeur1, TextActeur2, lesActeurs, lesFilms));
		this.addWindowListener(new FermerFenetreEcouteur(this));

		this.setVisible(true);

	}
	

}