package CinemaPackage;

import java.awt.*;


public class CineFrame2 extends Frame {
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;

	public CineFrame2(Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		setTitle("Cinema -> Frame 2");
		this.setSize(CineFrame1.LARGEUR, CineFrame1.HAUTEUR);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.LIGHT_GRAY);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
		        (screenSize.width-this.getWidth())/2,
		        (screenSize.height-this.getHeight())/2
		        );
		
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;

		/* Creation du panel qui va contenir les bouttons et les textfields */
		//Panel 1
		Panel panel1 = new Panel(new GridLayout(1,5)); //ajouter bouton back
		Label LabelActeur1 = new Label("Acteur 1");
		TextField TextActeur1 = new TextField("ok", 20);
		Label LabelActeur2 = new Label("Acteur 2");
		TextField TextActeur2 = new TextField("b", 20);
		Button bouttonJouer = new Button ("Resoudre");

		panel1.add(LabelActeur1);
		panel1.add(TextActeur1);
		panel1.add(LabelActeur2);
		panel1.add(TextActeur2);
		panel1.add(bouttonJouer);



		// Creation du panel qui va contenir le resultat 
		Panel panel4 = new Panel(new BorderLayout());
		panel4.setBackground(Color.WHITE);
		TextArea TextRes = new TextArea("");

		
		panel4.add(TextRes);

		Panel panel0 = new Panel(new GridLayout(3,1));
		panel0.add(panel1);
		
		Button menu = new Button ("Menu");

		this.add(panel0, BorderLayout.NORTH);
		this.add(panel4, BorderLayout.CENTER);
		this.add(menu, BorderLayout.SOUTH);

		
		this.addWindowListener(new FermerFenetreEcouteur(this));
		bouttonJouer.addActionListener(new BoutonEcouteur(TextActeur1, TextActeur2, lesActeurs, lesFilms));
		menu.addActionListener(new BoutonEcouteur(this, lesActeurs, lesFilms));

		this.setVisible(true);

	}
	

}