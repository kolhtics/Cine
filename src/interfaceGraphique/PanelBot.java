package interfaceGraphique;

import java.awt.*;

import cinemaPackage.*;
import ecouteurs.*;



public class PanelBot extends Panel{
	private static final long serialVersionUID = 1L;
	private TextArea textRes;
	private Label labelRecherche;

	public PanelBot(Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.LIGHT_GRAY);
		
		/* Creation du panel qui va contenir les bouttons et les textfields */
		//Panel 1
		Panel panel1 = new Panel(new GridLayout(1,5,10, 5));
		Label LabelActeur1 = new Label("Acteur 1");
		TextField textActeur1 = new TextField("", 20);
		Label LabelActeur2 = new Label("Acteur 2");
		TextField textActeur2 = new TextField("", 20);
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
		
		
		labelRecherche = new Label("");

		this.add(panel0, BorderLayout.NORTH);
		this.add(panel4, BorderLayout.CENTER);
		this.add(labelRecherche, BorderLayout.SOUTH);
		
		textActeur1.addKeyListener(new ClavierEcouteur(textActeur1, lesActeurs));
		textActeur2.addKeyListener(new ClavierEcouteur(textActeur2, lesActeurs));
		
		bouttonJouer.addActionListener(new BoutonEcouteur(this, textActeur1, textActeur2, lesActeurs, lesFilms));

		this.setVisible(true);

	}
	
	public void setTexte(String s){
		textRes.setText(s);
		System.out.println(s);
		textRes.repaint();
	}
	
	public void setLabelRecherche(String s){
		labelRecherche.setText(s);
		System.out.println(s);
		labelRecherche.repaint();
	}

	

}