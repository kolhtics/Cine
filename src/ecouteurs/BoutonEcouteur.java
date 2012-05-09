package ecouteurs;

import interfaceGraphique.*;

import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import algorithme.*;

import cinemaPackage.*;



public class BoutonEcouteur implements ActionListener {
	private TextField t1;
	private TextField t2;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;
	private CineFrame f;
	private PanelBot b;
	private PanelJeu j;
	private PanelRechercher r;
	private List resultat;
	
	
	public BoutonEcouteur(TextField t1, TextField t2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;
		this.t1=t1;
		this.t2=t2;
	}
	
	
	public BoutonEcouteur(PanelBot b, TextField t1, TextField t2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this(t1,t2,lesActeurs, lesFilms);
		this.b=b;
	}
	
	public BoutonEcouteur(PanelJeu j, TextField t1, TextField t2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this(t1,t2,lesActeurs, lesFilms);
		this.j=j;
	}
	
	public BoutonEcouteur(CineFrame f){
		this.f=f;
	}

	

	public BoutonEcouteur(PanelRechercher r, TextField t1, Repertoire<Acteur> lesActeurs, 
			Repertoire<Film> lesFilms, List resultat) {
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;
		this.t1=t1;
		this.r=r;
		this.resultat=resultat;
	}


	public void actionPerformed(ActionEvent a) {
		String bouton = a.getActionCommand();
		if(bouton.equals("Resoudre")) {
			if(t1.getText().equals("") | t2.getText().equals("")){
				new BoiteDialog(f, "Veuillez remplir tous les champs !!");
			}
			else{
				new Point(b);
				String s = AlgoBot.jouer(t1.getText(), t2.getText(), lesActeurs, lesFilms);
				b.setTexte(s);
			}
		}
		else if(bouton.equals("JOUER")) {
			j.remiseZero();
			j.setPanelInvisible();
			if(t1.getText().equals("") | t2.getText().equals("")){
				new BoiteDialog(f, "Veuillez remplir tous les champs !!");
			}
			else{
				AlgoJeu.jouer(j,t1.getText(), t2.getText(), lesActeurs, lesFilms);
				j.validate();
			}
			j.setPanelVisible();
		}
		else if(bouton.equals("Je suis bloque")){
			if(t1.getText().equals("") | t2.getText().equals("")){
				new BoiteDialog(f, "Veuillez remplir tous les champs !!");
			}
			else{
				new SolutionFrame(AlgoBot.jouer(t1.getText(), t2.getText(), lesActeurs, lesFilms));
			}
		}
		else if(bouton.equals("Ok")){
			try{
				AlgoJeu.suivant(j, j.getlistElement().getSelectedItem(), t2.getText(), lesActeurs, lesFilms);
				j.validate();
			}
			catch(NullPointerException e){
				new BoiteDialog(f , "Veuillez selectionner un objet dans la liste de gauche");
			}
		}
		else if(bouton.equals("Back")){ 
			try{
				AlgoJeu.back(j, lesActeurs, lesFilms); 
				j.validate();
			}
			catch(ArrayIndexOutOfBoundsException e1){
				new BoiteDialog(f , "Il n'y a plus rien a supprimer !");
			}
		}
		else if(bouton.equals("Afficher")){
			try{
				r.setTexte(AlgoRecherche.afficher_objet(t1.getText(), lesActeurs, lesFilms));
			}
			catch(NullPointerException e){
				new BoiteDialog(f , "Ce film/acteur n'existe pas");
			}
		}
		else if(bouton.equals("Rechercher un Acteur")){
			if (t1.getText().length() > 2){
				resultat.setVisible(false);
				r.setListeA(AlgoRecherche.recherche_acteur(t1.getText(),lesActeurs));
				resultat.setVisible(true);
			}else {
				new BoiteDialog(f, "Veuillez saisir 3 lettre ou plus");
			}
		}
		else if(bouton.equals("Rechercher un Film")){
			if (t1.getText().length() > 2){
				resultat.setVisible(false);
				r.setListeF(AlgoRecherche.recherche_film(t1.getText(),lesFilms));
				resultat.setVisible(true);
			}else {
				new BoiteDialog(f, "Veuillez saisir 3 lettre ou plus");
			}
		}
	}
}
