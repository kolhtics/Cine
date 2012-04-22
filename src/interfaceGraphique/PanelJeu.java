package interfaceGraphique;

import java.awt.*;
import java.util.Iterator;

import cinemaPackage.*;
import ecouteurs.*;


public class PanelJeu extends Panel{
	private static final long serialVersionUID = 1L;
	final static int TAILLE_LISTE = 15;
	private List listElement, listReponse;
	private Label LabelListe;
	Label LabelCompteur;
	private Repertoire<Acteur> lesActeurs;

		public PanelJeu(Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
			this.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 10));
			//this.setLayout(new GridLayout(4,1));
			this.setBackground(Color.LIGHT_GRAY);
			
			this.lesActeurs=lesActeurs;
			

			Panel panel1 = new Panel(new GridLayout(5,1, 200, 5));
			Label LabelActeur1 = new Label("Acteur 1");
			TextField textActeur1 = new TextField("ok", 20);
			Label LabelActeur2 = new Label("Acteur 2");
			TextField textActeur2 = new TextField("b", 20);
			Button boutonJouer = new Button ("JOUER");

			panel1.add(LabelActeur1);
			panel1.add(textActeur1);
			panel1.add(LabelActeur2);
			panel1.add(textActeur2);
			panel1.add(boutonJouer);
			
			Panel panel2 =new Panel(new GridLayout(1,2, 10, 5));
			LabelListe =new Label("Veuillez entrer des acteurs", Label.CENTER);
			LabelCompteur =new Label("Nombre de coups : 0", Label.CENTER);
			panel2.add(LabelListe);
			panel2.add(LabelCompteur);
			
			
			Panel panelList = new Panel(new GridLayout(1,2, 10, 5));
			listElement = new List(TAILLE_LISTE);
			listReponse = new List(TAILLE_LISTE);
			
			
			panelList.add(listElement);
			panelList.add(listReponse);
			Panel panel3 = new Panel(new GridLayout(1,3, 5, 5));
			Button ok = new Button ("Ok");
			Button boutonBack = new Button ("Back");
			Button bloque= new Button("Je suis bloque");
			panel3.add(ok);
			panel3.add(boutonBack);
			panel3.add(bloque);
					
			this.add(panel1);
			this.add(panel2);
			this.add(panelList);
			this.add(panel3);


			listElement.addActionListener(new SourisEcouteur(this,textActeur1, textActeur2, lesActeurs, lesFilms));//permet le double clique pour selectionner un item
			boutonJouer.addActionListener(new BoutonEcouteur(this, textActeur1, textActeur2, lesActeurs, lesFilms));
			bloque.addActionListener(new BoutonEcouteur(textActeur1, textActeur2, lesActeurs, lesFilms));
			ok.addActionListener(new BoutonEcouteur(this, textActeur1, textActeur2, lesActeurs, lesFilms));
			boutonBack.addActionListener(new BoutonEcouteur(this, textActeur1, textActeur2, lesActeurs, lesFilms));
			
			this.setVisible(true);
			
			
		}

		public void setListElement(Acteur acteur) {
			listElement.removeAll();
			Iterator<Film> it=acteur.iterator();
			while (it.hasNext()){
				listElement.add(it.next().getId());
			}
		}

		
		public void setListElement (Film f, String act){
			listElement.removeAll(); // on efface tout les elements de la list pou rapartir sur de bonnes bases
			Iterator<Acteur> it=f.iterator(); // recupere les acteurs du film f
			Acteur a= lesActeurs.rechercher(act); // a est l'acteur que l'on ne veut pas afficher dans la liste
			while (it.hasNext()){
				String acteurCourant = it.next().getId();
				if(!a.getId().equals(acteurCourant)){ // si l'acteur a afficher n'est pas le meme que l'acteur sur lequel on vient de cliquer
					listElement.add(acteurCourant); // alors on affiche cet acteur
				}									// cela nous permet de ne pas afficher l'acteur dans la liste des acteurs de ses propres films
			}
		}

		public void setListReponse(String s) {
			this.listReponse.add(s);
		}

		public void setLabelListe(String s) {
			LabelListe.setText(s);
			LabelListe.repaint();
		}
		
		public void setLabelCompteur(String s) {
			LabelCompteur.setText(s);
			LabelCompteur.repaint();
		}

		public List getlistElement() {
			return listElement;
		}
		
		public List getListReponse(){
			return listReponse;
		}

		public void remiseZero() {
			this.listElement.removeAll();
			this.listReponse.removeAll();
			
		}
		
		
}
