package Ecouteurs;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Algorithme.*;
import CinemaPackage.*;
import Interface.*;

public class BoutonEcouteur implements ActionListener {
	private TextField t1;
	private TextField t2;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;
	private Panel p;
	private CineFrame f;
	private PanelBot b;
	
	public BoutonEcouteur(Panel p, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.p=p;
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;
	}
	
	public BoutonEcouteur(TextField t1, TextField t2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this(null, lesActeurs, lesFilms);
		this.t1=t1;
		this.t2=t2;
	}
	
	
	public BoutonEcouteur(PanelBot b, TextField t1, TextField t2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this(t1,t2,lesActeurs, lesFilms);
		this.b=b;
	}
	
	public BoutonEcouteur(CineFrame f){
		this.f=f;
	}

	

	public void actionPerformed(ActionEvent e) {
		String bouton = e.getActionCommand();	
		if(bouton.equals("Mode Jeu")) { f.setPanelJeu(); f.reconstruire(); }
		if(bouton.equals("Mode Bot")) { f.setPanelBot(); f.reconstruire(); }
		if(bouton.equals("Menu")) { f.setPanelImage(); f.reconstruire(); }
		if(bouton.equals("Resoudre")) {
			if(t1.getText().equals("") | t2.getText().equals("")){
				new BoiteDialog(f, "Veuillez remplir tous les champs !!");
			}
			else{
				String s = AlgoBot.testGraphique(t1.getText(), t2.getText(), lesActeurs, lesFilms);
				b.setTexte(s);
			}
		}
		if(bouton.equals("JOUER")) {
			if(t1.getText().equals("") | t2.getText().equals("")){
				new BoiteDialog(f ,"Veuillez remplir tous les champs !!");
			}
			else{
				String s = AlgoBot.testGraphique(t1.getText(), t2.getText(), lesActeurs, lesFilms);
				b.setTexte(s);
			}
		}
		
	}

}
