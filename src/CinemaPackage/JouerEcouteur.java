package CinemaPackage;



import java.awt.*;
import java.awt.event.*;



public class JouerEcouteur implements ActionListener {
	private TextField t1;
	private TextField t2;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;
	
	public JouerEcouteur(TextField t1, TextField t2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.t1=t1;
		this.t2=t2;
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Test.testGraphique(t1.getText(), t2.getText(), lesActeurs, lesFilms);	
		
	}
}
