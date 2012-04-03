package Algorithme;

import CinemaPackage.Acteur;
import CinemaPackage.Film;
import CinemaPackage.Repertoire;
import Interface.PanelJeu;

public class AlgoJeu {
	private static int compteur=0;

	public static void jouer(PanelJeu j, String act1, String act2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		j.setLabelListe("Liste des films de "+act1);
		Acteur a1= lesActeurs.rechercher(act1);
		Acteur a2= lesActeurs.rechercher(act2);
		if (a1 == null && a2 == null){
			j.setLabelListe(act1+" n'existe pas");
			j.setLabelCompteur(act2+" n'existe pas");
		}
		else if(a1 == null){
			j.setLabelListe(act1+" n'existe pas");
			j.setLabelCompteur("<- Umad???");
		}
		else if(a2 == null){
			j.setLabelCompteur(act2+" n'existe pas");
			j.setLabelListe("Umad??? ->");
		}
		else{
		j.setListElement(a1);
		j.setListReponse(a1.getId());
		compteur=0;
		}

	}

	public static void suivant(PanelJeu j, String s, String cible ,Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms) {
		Acteur a= lesActeurs.rechercher(s);
		if (a != null){
			j.setListReponse(a.getId());
			j.setLabelListe("Liste des films de "+s);
			j.setListElement(a);
			
			if(a.getId().equals(cible)){
				j.setLabelListe("VOUS AVEZ GAGNE !!");
			}
		}
		else {
			Film f= lesFilms.rechercher(s);
			j.setListReponse(f.getId());
			j.setLabelListe("Liste des acteurs de "+s);
			j.setListElement(f);
			compteur++;
			j.setLabelCompteur("Nombre de coups : "+compteur);
		}
	}
	
	public static void back(PanelJeu j, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms) {
		String s = j.getListReponse().getItem(j.getListReponse().getItemCount()-2);
		j.getListReponse().remove(j.getListReponse().getItemCount()-1);
		Acteur a= lesActeurs.rechercher(s);
		if (a != null){
			j.setLabelListe("Liste des films de "+s);
			j.setListElement(a);
			compteur--;
		}
		else {
			Film f= lesFilms.rechercher(s);
			j.setLabelListe("Liste des acteurs de "+s);
			j.setListElement(f);
			j.setLabelCompteur("Nombre de coups : "+compteur);
		}
	}
}
