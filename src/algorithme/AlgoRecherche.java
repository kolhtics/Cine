package algorithme;

import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import cinemaPackage.Acteur;
import cinemaPackage.Film;
import cinemaPackage.Repertoire;

public class AlgoRecherche {
	
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
	
	// Methode qui recherche un acteur ou un film pour être affiché ( recherche stricte )
	
	public static String afficher_objet(String s, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
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
	
	
	public static String recherche_Acteur(String s,Repertoire<Acteur> lesActeurs,Repertoire<Film> lesFilms,boolean recherche_Acteur){
		String resultats="";
		String temp=s;
		s="";
		StringTokenizer st=new StringTokenizer(temp,",");
		while (st.hasMoreTokens()){
			s+=st.nextToken();
		}
		System.out.println(s);
		if(recherche_Acteur){
			if(s.length()>2){
				Repertoire<Acteur> filtrage = recherche_nom_Acteur(s,lesActeurs);
				Iterator<Acteur> it = filtrage.iterator();
				s=s.toLowerCase();
				while (it.hasNext()){
					Acteur acteur_courant = it.next();
					temp=acteur_courant.getNom().toLowerCase();
					String nom=acteur_courant.getNom().toLowerCase();
					if(levenshtein(nom,temp) < 10 ){
						resultats+=acteur_courant.getNom()+"\n";
					}
				}	
			}	
			else resultats="Veuillez entre au minimum 3 lettres" ;
		}
		else{ 
			
			// On fait la même chose avec des films
			
			if(s.length()>2){
				Repertoire<Film> filtrage = recherche_nom_Film(s,lesFilms);
				Iterator<Film> it = filtrage.iterator();
				s=s.toLowerCase();
				while (it.hasNext()){
					Film film_courant = it.next();
					temp=film_courant.getId().toLowerCase();
					String nom=film_courant.getId().toLowerCase();
					if(levenshtein(nom,temp) < 10 ){
						resultats+=film_courant.getId()+"\n";
					}
				}	
			}	
			else resultats="Veuillez entre au minimum 3 lettres" ;
		}
		return resultats;
	}

	// Methode qui retourne une chaine constituée de tous les acteurs/films qui ont soit une distance faible, 
	// soit qui contiennent dans leur nom la chaine passée en paramètre
	// ---> fusion assez basique de recherche_nom et recherche_levenshtein
	public static String recherche_test(String s, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms) {
		String resultats="";
		String temp=s;
		s="";
		StringTokenizer st=new StringTokenizer(temp,",");
		while (st.hasMoreTokens()){
			s+=st.nextToken();
		}
		if(s.length()>2){
			Repertoire<Acteur> filtre = recherche_nom_Acteur(s, lesActeurs);
			Iterator<Acteur> it2 = filtre.iterator();
			while (it2.hasNext()){
				resultats+=it2.next().getNom()+"\n";
			}
			Iterator<Acteur> it = lesActeurs.iterator();
			s=s.toLowerCase();
			while (it.hasNext()){
				Acteur acteur_courant = it.next();
				temp=acteur_courant.getNom().toLowerCase();
				if(levenshtein(s,temp) < 4  &&  temp.length() > 5){
					resultats+=acteur_courant.getNom()+"\n";
				}
			}
		}
		else resultats="Veuillez entre au minimum 3 lettres" ;
		return resultats;
		}
	
	// Methode qui vérifie si la chaîne passée en entrée est présente dans le nom d'un des Acteurs de notre répertoire 
	public static Repertoire<Acteur> recherche_nom_Acteur(String s,Repertoire<Acteur> lesActeurs) {
		Iterator<Acteur> it_acteur = lesActeurs.iterator();
		Repertoire<Acteur> res= new Repertoire<Acteur>();
		while (it_acteur.hasNext()){
			String nom="";
			Acteur acteurCourant = it_acteur.next();
			StringTokenizer st=new StringTokenizer(acteurCourant.getId(),",");
			while (st.hasMoreTokens()){
				nom += st.nextToken();
			}
			if (nom.toLowerCase().contains(s.toLowerCase())){
				res.ajouter(acteurCourant);
			}
		}
		return res;
	}

	// Methode qui vérifie si la chaîne passée en entrée est présente dans le nom d'un des Films de notre répertoire 
	public static Repertoire<Film> recherche_nom_Film(String s,Repertoire<Film> lesFilms) {
		Iterator<Film> it_film = lesFilms.iterator();
		Repertoire<Film> res= new Repertoire<Film>();
		while (it_film.hasNext()){
			String nom="";
			Film filmCourant = it_film.next();
			StringTokenizer st=new StringTokenizer(filmCourant.getId(),",");
			while (st.hasMoreTokens()){
				nom += st.nextToken();
			}
			if (nom.toLowerCase().contains(s.toLowerCase())){
				res.ajouter(filmCourant);
			}
		}
		return res;
	}

}


// TODO
// recherche par prénom : avec endwith
// essayer de pouvoir rechercher prenom+nom et pas forcément nom+prenom
// clean le code


