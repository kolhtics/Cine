package ecouteurs;

import interfaceGraphique.*;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cinemaPackage.*;

import algorithme.*;


public class BoutonEcouteur implements ActionListener {
	private TextField t1;
	private TextField t2;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;
	private CineFrame f;
	private PanelBot b;
	private PanelJeu j;
	private PanelRechercher r;
	
	
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

	

	public BoutonEcouteur(PanelRechercher r, TextField t1, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms) {
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;
		this.t1=t1;
		this.r=r;
	}


	public void actionPerformed(ActionEvent a) {
		String bouton = a.getActionCommand();	
		/*if(bouton.equals("Mode Jeu")) { f.setPanelJeu(); f.reconstruire(); }
		if(bouton.equals("Mode Bot")) { f.setPanelBot(); f.reconstruire(); }
		if(bouton.equals("Mode Recherche")) { f.setPanelImage(); f.reconstruire(); }*/
		if(bouton.equals("Resoudre")) {
			if(t1.getText().equals("") | t2.getText().equals("")){
				new BoiteDialog(f, "Veuillez remplir tous les champs !!");
			}
			else{
				String s = AlgoBot.jouer(t1.getText(), t2.getText(), lesActeurs, lesFilms);
				b.setTexte(s);
			}
		}
		
		if(bouton.equals("JOUER")) {
			j.remiseZero();
			if(t1.getText().equals("") | t2.getText().equals("")){
				new BoiteDialog(f, "Veuillez remplir tous les champs !!");
			}
			else{
				AlgoJeu.jouer(j,t1.getText(), t2.getText(), lesActeurs, lesFilms);
			}
		}
		
		if(bouton.equals("Je suis bloque")){
			if(t1.getText().equals("") | t2.getText().equals("")){
				new BoiteDialog(f, "Veuillez remplir tous les champs !!");
			}
			else{
				new SolutionFrame(AlgoBot.jouer(t1.getText(), t2.getText(), lesActeurs, lesFilms));
			}
		}
		
		if(bouton.equals("Ok")){
			try{
				new BoiteDialog(f , "Veuillez selectionner un item dans la liste de gauche");
			}
			catch(NullPointerException e){
				AlgoJeu.suivant(j, j.getlistElement().getSelectedItem(), t2.getText(), lesActeurs, lesFilms);
			}
		}
		
		if(bouton.equals("Back")){ 
			try{
				AlgoJeu.back(j, lesActeurs, lesFilms); 
			}
			catch(ArrayIndexOutOfBoundsException e1){
				new BoiteDialog(f , "Aucun element a enlever dans la liste de droite pignouf!!");
			}
		
		}
		
		if(bouton.equals("Rechercher")){
			try{
				String s = AlgoRecherche.rechercher(t1.getText(), lesActeurs, lesFilms);
				r.setTexte(s);
			}
			catch(NullPointerException e){
				new BoiteDialog(f , "Veuillez remplir tous les champs !!");
			}
		}

	}
}
