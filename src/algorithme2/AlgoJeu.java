package algorithme2;

import interfaceGraphique.BoiteDialog;
import interfaceGraphique.PanelJeu;
import cinemaPackage.Acteur;
import cinemaPackage.Film;
import cinemaPackage.Repertoire;

public class AlgoJeu {
	private static int compteur=0;

	public static void jouer(PanelJeu j, String act1, String act2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		Acteur a1= lesActeurs.rechercher(act1);
		Acteur a2= lesActeurs.rechercher(act2);
		if (a1 == null && a2 == null){ 	 			// Diverses verifications pour eviter les pointer null
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
		j.setLabelListe("Liste des films de "+act1); //initialisation du jeu avec 
		j.setListReponse(a1.getId());				// le premier acteur comme reponse
		j.setListElement(a1);						// et sa liste de film			
		compteur=0;
		}

	}

	public static void suivant(PanelJeu j, String s, String cible ,Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms) {
		Acteur a= lesActeurs.rechercher(s); // on regarde si s est un acteur
		if (a != null){
			j.setListReponse(a.getId());  //si oui on le place dans les reponses et on affiche sa liste de films
			j.setLabelListe("Liste des films de "+s);
			j.setListElement(a);
			
			if(a.getId().equals(cible)){ //si l'acteur choisit est l'acteur cible alors le jeu est finit
				
				j.setLabelListe("VOUS AVEZ GAGNE !!");
				new BoiteDialog(null ,"BRAVO!!!", "VOUS AVEZ GAGNE !! En "+compteur+" coups");
			}
		}
		else {
			Film f= lesFilms.rechercher(s);
			j.setListReponse(f.getId());
			j.setLabelListe("Liste des acteurs de "+s);
			j.setListElement(f, j.getListReponse().getItem(j.getListReponse().getItemCount()-2)); //le second parametre permet de recuperer l'acteur
			compteur++;																			// dont on affiche les films pour ne pas l'afficher
			j.setLabelCompteur("Nombre de coups : "+compteur);									// dans la liste des acteurs de ses propres films
		}
	}
	
	public static void back(PanelJeu j, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms) {
		String s = j.getListReponse().getItem(j.getListReponse().getItemCount()-2); // recupere le film ou l'acteur qui est juste avant l'element a supprimer
		j.getListReponse().remove(j.getListReponse().getItemCount()-1); // et on supprime le dernier element
		Acteur a= lesActeurs.rechercher(s);
		if (a != null){
			j.setLabelListe("Liste des films de "+s);
			j.setListElement(a);
			compteur--;
		}
		else {
			Film f= lesFilms.rechercher(s);
			j.setLabelListe("Liste des acteurs de "+s);
			j.setListElement(f, j.getListReponse().getItem(j.getListReponse().getItemCount()-2)); //le second parametre permet de recuperer l'acteur
			j.setLabelCompteur("Nombre de coups : "+compteur);									// dont on affiche les films
		}
	}
}
