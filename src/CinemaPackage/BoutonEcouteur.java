package CinemaPackage;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonEcouteur implements ActionListener {
	private TextField t1;
	private TextField t2;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;
	private Frame f;
	
	public BoutonEcouteur(Frame f, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.f=f;
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;
	}
	
	public BoutonEcouteur(TextField t1, TextField t2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this(null, lesActeurs, lesFilms);
		this.t1=t1;
		this.t2=t2;
	}
	

	public void actionPerformed(ActionEvent e) {
		String bouton = e.getActionCommand();		
		if(bouton.equals("Mode Jeu")) { f.dispose(); new CineFrame3(lesActeurs, lesFilms);}
		if(bouton.equals("Mode Bot")) { f.dispose(); new CineFrame2(lesActeurs, lesFilms);}
		if(bouton.equals("Menu")) { f.dispose(); new CineFrame1(lesActeurs, lesFilms);}
		if(bouton.equals("Resoudre")) {
			if(t1.getText().equals("") | t2.getText().equals("")){
				new BoiteDialog(f, "Veuillez remplir tous les champs !!");
			}
			else{
				Test.testGraphique(t1.getText(), t2.getText(), lesActeurs, lesFilms);
			}
		}
		if(bouton.equals("JOUER")) {
			if(t1.getText().equals("") | t2.getText().equals("")){
				new BoiteDialog(f ,"Veuillez remplir tous les champs !!");
			}
			else{
				Test.testGraphique(t1.getText(), t2.getText(), lesActeurs, lesFilms);
			}
		}
		
	}

}
