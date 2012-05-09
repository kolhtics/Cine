package ecouteurs;

import interfaceGraphique.BoiteDialog;
import interfaceGraphique.PanelJeu;
import java.awt.List;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algorithme.AlgoJeu;
import algorithme.AlgoRecherche;

import cinemaPackage.Acteur;
import cinemaPackage.Film;
import cinemaPackage.Repertoire;



public class SourisEcouteur implements ActionListener {
	private TextField t2;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;
	private PanelJeu j;
	private List gauche;
	private TextArea droite;
	
	
	public SourisEcouteur(TextField t1, TextField t2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.t2=t2;
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;
	}
	
	public SourisEcouteur(PanelJeu j, TextField t1, TextField t2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this(t1, t2,lesActeurs, lesFilms);
		this.j=j;
	}
	
	public SourisEcouteur(List result, TextArea droite ,Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.gauche=result;
		this.droite=droite;
		this.lesActeurs=lesActeurs;
		this.lesFilms=lesFilms;
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		if (j != null){	
			if(j.getlistElement().getSelectedItem() != null){
				j.setPanelInvisible();
				AlgoJeu.suivant(j, j.getlistElement().getSelectedItem(), t2.getText(), lesActeurs, lesFilms);
				j.setPanelVisible();
				j.validate();
			}
			else{
				new BoiteDialog(null , "Veuillez selectionner un item dans la liste de gauche");
			}
		}
		else {
			droite.setText(AlgoRecherche.afficher_objet(gauche.getSelectedItem(), lesActeurs, lesFilms));
		}
	}

}
