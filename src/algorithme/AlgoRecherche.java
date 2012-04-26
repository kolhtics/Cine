package algorithme;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import cinemaPackage.Acteur;
import cinemaPackage.Film;
import cinemaPackage.Repertoire;

public class AlgoRecherche {
	
	public static int Distance(String chaine1, String chaine2){
		int[][] d = new int[chaine1.length()+1][chaine2.length()+1];
		int i, j, cout;
		
		for(i=0; i<chaine1.length(); i++){
			d[i][0] = i;
		}
		for(j=0; j<chaine2.length(); j++){
			d[0][j] = j;
		}
		for(i=1; i<=chaine1.length(); i++){
			for(j=1; j<=chaine2.length(); j++){
				if(((Character)chaine1.charAt(i-1)).equals((Character)chaine2.charAt(j-1))){
					cout = 0;
				}
				else{
					cout = 1;
				}
				d[i][j] = min(d[i-1][j]+1, min(d[i][j-1]+1, d[i-1][j]+cout));
			}
		}
		return d[chaine1.length()][chaine2.length()];
	}
	
	public static int levenshtein(String s0, String s1) {
		int len0 = s0.length()+1;
		int len1 = s1.length()+1;
		int[] cost = new int[len0];
		int[] newcost = new int[len0];
		for(int i=0;i<len0;i++) cost[i]=i;
		for(int j=1;j<len1;j++) {
			newcost[0]=j-1;
			for(int i=1;i<len0;i++) {
				int match = (s0.charAt(i-1)==s1.charAt(j-1))?0:1;
				int cost_replace = cost[i-1]+match;
				int cost_insert  = cost[i]+1;
				int cost_delete  = newcost[i-1]+1;

				newcost[i] = min(cost_insert,min(cost_delete,cost_replace));
			}
			int[] swap=cost; cost=newcost; newcost=swap;
		}
		return cost[len0-1];
	}
	
	public static int min(int a, int b){
		if(a>b){
			return b;
		}
		else{
			return a;
		}
	}
	
	// recherche catégorique mais qui marche bien :)
	
	public static String rechercher_liste(String s, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		try{
			Acteur a= lesActeurs.rechercher(s);
			String res = a.toString();
			return res;
		}catch(NullPointerException e){
			Film f = lesFilms.rechercher(s);
			String res = f.toString();
			return res;
		}	
	}
	
	public static Set<Acteur> recherche_acteur(String s, Repertoire<Acteur> lesActeurs){
		Acteur a= lesActeurs.rechercher(s);
		Set<Acteur> acteurs_potentiels=new TreeSet<Acteur>();
		if (a==null){
			Iterator<Acteur> it = lesActeurs.iterator();
			while (it.hasNext()){
				Acteur acteur_courant = it.next();
				String nom=acteur_courant.getNom();
				if (nom.length()<s.length()){
					if(levenshtein(nom,s) < 3 ){
						acteurs_potentiels.add(acteur_courant);
					}
				}
				else{
					if(levenshtein(nom,s) < 6 ){
						acteurs_potentiels.add(acteur_courant);
					}
				}
			}
			return acteurs_potentiels;
		}
		else {
			acteurs_potentiels.add(a);
			return acteurs_potentiels;
		}
	}
	
	public static Set<Film> recherche_film(String s, Repertoire<Film> lesFilms){
		Film f= lesFilms.rechercher(s);
		Set<Film> films_potentiels=new TreeSet<Film>();
		if (f==null){
			Iterator<Film> it = lesFilms.iterator();
			while (it.hasNext()){
				Film film_courant = it.next();
				String nom = film_courant.getId();
				if (nom.length()<s.length()){
					if(levenshtein(nom,s) < 3 ){
						films_potentiels.add(film_courant);
					}
				}
				else{
					if(levenshtein(nom,s) < 6 ){
						films_potentiels.add(film_courant);
					}
				}
			}
			return films_potentiels;
		}
		else {
			films_potentiels.add(f);
			return films_potentiels;
		}
	}
	
	public static String rechercher(String s,Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		String res="";
		Set<Acteur> resultat_acteur = recherche_acteur(s,lesActeurs);
		Set<Film> resultat_film = recherche_film(s,lesFilms);
		Iterator<Acteur> it_acteur = resultat_acteur.iterator();
		Iterator<Film> it_film = resultat_film.iterator();
		
		res += "Liste des Acteurs potentiels trouvés : \n";
		while (it_acteur.hasNext()){
			res+= it_acteur.next().getId()+"\n";
		}
		res+= "Liste des Films potentiels trouvés : \n";
		while (it_film.hasNext()){
			res+= it_film.next().getId()+"\n";
		}
		return res;
	}
	public static String recherche_prenom(String s,Repertoire<Acteur> lesActeurs){
		Iterator<Acteur> it_acteur = lesActeurs.iterator();
		String res="";
		while (it_acteur.hasNext()){
			Acteur acteurCourant = it_acteur.next();
			if (acteurCourant.getId().endsWith(s)){
				res+= acteurCourant.getId()+"\n";
			}
		}
		return res;
	}

	public static String recherche_nom(String s,Repertoire<Acteur> lesActeurs) {
		Iterator<Acteur> it_acteur = lesActeurs.iterator();
		String res="";
		while (it_acteur.hasNext()){
			Acteur acteurCourant = it_acteur.next();
			if (acteurCourant.getId().contains(s)){
				res+= acteurCourant.getId()+"\n";
			}
		}
		return res;
	}
	
	public static String recherche_test(String s,Repertoire<Acteur> lesActeurs){
		Acteur a= lesActeurs.rechercher(s);
		String result="mabite";
		Repertoire<Acteur> resultats=new Repertoire<Acteur>();
		if (a==null){
			Iterator<Acteur> it = lesActeurs.iterator();
			while (it.hasNext()){
				Acteur acteur_courant = it.next();
				String nom=acteur_courant.getNom().toLowerCase();
				String temp=s.toLowerCase();
				if(levenshtein(nom,temp) < 10 ){
						resultats.ajouter(acteur_courant);
						System.out.println(resultats);
					}
				}
			result = recherche_nom(s,resultats);
		}
		else {
			result = a.toString();
		}
		return result;
	}
	public static String recherche_test2(String s,Repertoire<Acteur> lesActeurs){
		String resultats="";
			if(s.length()>2){
			Repertoire<Acteur> filtrage = recherche_nom2(s,lesActeurs);
			Iterator<Acteur> it = filtrage.iterator();
			s=s.toLowerCase();
			while (it.hasNext()){
				Acteur acteur_courant = it.next();
				String temp=acteur_courant.getNom().toLowerCase();
				String nom=acteur_courant.getNom().toLowerCase();
				if(levenshtein(nom,temp) < 7 ){
					resultats+=acteur_courant.getNom()+"\n";
					System.out.println(resultats);
				}
			}	
		}	
		else resultats="Veuillez entre au minimum 3 lettres" ;
		return resultats;
	}
	public static Repertoire<Acteur> recherche_nom2(String s,Repertoire<Acteur> lesActeurs) {
		Iterator<Acteur> it_acteur = lesActeurs.iterator();
		Repertoire<Acteur> res= new Repertoire<Acteur>();
		while (it_acteur.hasNext()){
			Acteur acteurCourant = it_acteur.next();
			if (acteurCourant.getId().toLowerCase().contains(s.toLowerCase())){
				res.ajouter(acteurCourant);
			}
		}
		return res;
	}
}

// LA RECHERCHE PAR "NOM" est en fait une recherche d'une séquence de charactère contenue dans une chaine
// LA RECHERCHE PAR "PRENOM" est en revanche bien une recherche par prénom dans la mesure ou la fin de la chaîne est comparée
