package CinemaPackage;

import java.awt.*;

public class CineFrame3 extends Frame{
	final static int HAUTEUR = 600;
	final static int LARGEUR = 564;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;

		public CineFrame3(){
			this.setTitle("Cinema");
			this.setSize(LARGEUR, HAUTEUR);
			this.setLayout(new BorderLayout());
			this.setBackground(Color.LIGHT_GRAY);
			this.lesActeurs=lesActeurs;
			this.lesFilms=lesFilms;
			

			Panel panel1 = new Panel(new GridLayout(1,6));
			Label LabelActeur1 = new Label("Acteur 1");
			TextField TextActeur1 = new TextField(20);
			Label LabelActeur2 = new Label("Acteur 2");
			TextField TextActeur2 = new TextField(20);
			Button boutonJouer = new Button ("JOUER");
			Button boutonBack = new Button ("Back");

			panel1.add(LabelActeur1);
			panel1.add(TextActeur1);
			panel1.add(LabelActeur2);
			panel1.add(TextActeur2);
			panel1.add(boutonJouer);
			panel1.add(boutonBack);
			
			Panel panel2 =new Panel(new GridLayout(2,1));
			
			Panel panelList = new Panel();
			List listElement = new List(10);
			listElement.add("film1");
			listElement.add("film1");
			listElement.add("film1");
			listElement.add("film1");
			listElement.add("film1");
			listElement.add("film1");
			
			panelList.add(listElement);
			
			
			Panel panelAffichage = new Panel(new GridLayout(2,1));
			Label LabelVictoire =new Label("");
			Button bloque= new Button("Je suis bloque");
			panelAffichage.add(LabelVictoire);
			panelAffichage.add(bloque);
			
			panel2.add(panelList);
			panel2.add(panelAffichage);
			
			
			this.add(panel1, BorderLayout.NORTH);
			this.add(panel2, BorderLayout.CENTER);
			
			this.addWindowListener(new FermerFenetreEcouteur(this));
			this.setVisible(true);
			
			
		}
}
