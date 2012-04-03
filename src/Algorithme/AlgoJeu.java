package Algorithme;

import CinemaPackage.Acteur;
import CinemaPackage.Film;
import CinemaPackage.Repertoire;
import Interface.PanelJeu;

public class AlgoJeu {

	public static void jouer(PanelJeu j,String act1, String act2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		j.setLabelListe("Liste des films de "+act1);
		Acteur a1= lesActeurs.rechercher(act1);
		Acteur a2= lesActeurs.rechercher(act2);
		j.setListElement(a1);

	}

	public static void suivant(PanelJeu j, String s ,Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms) {
		Acteur a= lesActeurs.rechercher(s);
		if (a != null){
			j.setListReponse(a.getId());
		}
		else {
		Film f= lesFilms.rechercher(s);	
		j.setListReponse(f.getId());
		}
	}
}
