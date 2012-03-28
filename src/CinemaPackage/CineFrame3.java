package CinemaPackage;

import java.awt.*;

public class CineFrame3 extends Frame{
	final static int TAILLE_LISTE = 20;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;

		public CineFrame3(Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
			this.setTitle("Cinema -> Frame 3");
			this.setSize(CineFrame1.LARGEUR, CineFrame1.HAUTEUR);
			this.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 10));
			//this.setLayout(new GridLayout(4,1));
			this.setBackground(Color.LIGHT_GRAY);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(
			        (screenSize.width-this.getWidth())/2,
			        (screenSize.height-this.getHeight())/2
			        );
			
			this.lesActeurs=lesActeurs;
			this.lesFilms=lesFilms;
			

			Panel panel1 = new Panel(new GridLayout(5,1, 200, 5));
			Label LabelActeur1 = new Label("Acteur 1");
			TextField TextActeur1 = new TextField(20);
			Label LabelActeur2 = new Label("Acteur 2");
			TextField TextActeur2 = new TextField(20);
			Button boutonJouer = new Button ("JOUER");

			panel1.add(LabelActeur1);
			panel1.add(TextActeur1);
			panel1.add(LabelActeur2);
			panel1.add(TextActeur2);
			panel1.add(boutonJouer);
			
			Panel panel2 =new Panel(new GridLayout(1,2, 10, 5));
			Label LabelListe =new Label("Liste de film de acteur", Label.CENTER);
			Label LabelVictoire =new Label("nombres de coups : 2", Label.CENTER);
			panel2.add(LabelListe);
			panel2.add(LabelVictoire);
			
			
			Panel panelList = new Panel(new GridLayout(1,2, 10, 5));
			List listElement = new List(TAILLE_LISTE);
			listElement.add("film1 blobd^sdfhbdjmqfsdfsqdfqsd");
			listElement.add("film1");
			listElement.add("fsqfsqdfsdqdfsqdfsqdilm1");
			listElement.add("fifsqdflm1");
			listElement.add("fsqdfsqdfilm1");
			listElement.add("film1dsfsdfsqdfqsdfqsdf");
			listElement.add("fidfsqdfsqddfsqdfsqdffilmsdqfqsdf1");
			listElement.add("film1");
			listElement.add("fsiqsdfqsdflm1");
			listElement.add("fsdfsdfilm1");
			listElement.add("fiffsqdfssdlmsd");
			listElement.add("ffsdfsqdfsqdidflm1");
			listElement.add("filsqdfsqm1");
			listElement.add("film1");
			listElement.add("fildfsm1");
			listElement.add("filqdfsqdm1");
			listElement.add("film1");
			listElement.add("fifqsdfslm1");
			listElement.add("fiqdfsqdfsdlm1");
			
			List listResponce = new List(TAILLE_LISTE);
			panelList.add(listElement);
			panelList.add(listResponce);
			
			
			Panel panel3 = new Panel(new GridLayout(1,4, 5, 5));
			Button ok = new Button ("Ok");
			Button boutonBack = new Button ("Back");
			Button bloque= new Button("Je suis bloque");
			Button menu= new Button("Menu");
			panel3.add(ok);
			panel3.add(boutonBack);
			panel3.add(bloque);
			panel3.add(menu);
					
			this.add(panel1);
			this.add(panel2);
			this.add(panelList);
			this.add(panel3);


			
			this.addWindowListener(new FermerFenetreEcouteur(this));
			menu.addActionListener(new BoutonEcouteur(this, lesActeurs, lesFilms));
			boutonJouer.addActionListener(new BoutonEcouteur(TextActeur1, TextActeur2, lesActeurs, lesFilms));
			
			this.setVisible(true);
			
			
		}
}
