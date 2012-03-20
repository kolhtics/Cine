package CinemaPackage;

import java.awt.Button;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonEcouteur implements ActionListener {
	private TextField t1;
	private TextField t2;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;
	private Button b;
	private Panel p;
	
	public BoutonEcouteur(Button b, Panel p){
		this.b=b;
		this.p=p;
	}
	
	public BoutonEcouteur(Button b, TextField t1, TextField t2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.b=b;
		this.t1=t1;
		this.t2=t2;
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(b.getLabel().equals("JOUER")) Test.testGraphique(t1.getText(), t2.getText(), lesActeurs, lesFilms);	
		
	}

}
