package interfaceGraphique;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algorithme.AlgoJeu;

import cinemaPackage.Acteur;
import cinemaPackage.Film;
import cinemaPackage.Repertoire;



public class SourisEcouteur implements ActionListener {
	private TextField t2;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;
	private PanelJeu j;
	
	
	public SourisEcouteur(TextField t1, TextField t2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.t2=t2;
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;
	}
	
	public SourisEcouteur(PanelJeu j, TextField t1, TextField t2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this(t1, t2,lesActeurs, lesFilms);
		this.j=j;
	}
	

	public void actionPerformed(ActionEvent arg0) {
		if(j.getlistElement().getSelectedItem() != null){
			AlgoJeu.suivant(j, j.getlistElement().getSelectedItem(), t2.getText(), lesActeurs, lesFilms);
			j.validate();
		}
		else{
			new BoiteDialog(null , "Veuillez selectionner un item dans la liste de gauche");
		}

	}

}
