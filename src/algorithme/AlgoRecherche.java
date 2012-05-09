package algorithme;

import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

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
			String res = a.toString_full();
			return res;
		}catch(NullPointerException e){
			Film f = lesFilms.rechercher(s);
			String res = f.toString_full();
			return res;
		}
	}
	
	public static String nom_compact(String s){
		String temp=s;
		s="";
		StringTokenizer st=new StringTokenizer(temp,",");
		while (st.hasMoreTokens()){
			s += st.nextToken();
		}
		temp=s;
		s="";
		StringTokenizer st2 = new StringTokenizer(temp," ");
		while (st2.hasMoreTokens()){
			s += st2.nextToken();
		}
		temp=s;
		s="";
		StringTokenizer st3 = new StringTokenizer(temp,"(");
		s=st3.nextToken();
		return s.toLowerCase();
	}
	
	public static Vector<Acteur> recherche_acteur(String s,Repertoire<Acteur> lesActeurs){
		String nom = nom_compact(s);
		Vector<Acteur> solution = new Vector<Acteur>();
		Iterator<Acteur> it_acteur = lesActeurs.iterator();
		
		while (it_acteur.hasNext()){
			Acteur acteur_courant = it_acteur.next();
			String nom_courant = nom_compact(acteur_courant.getId());
			int distance = levenshtein(nom_courant,nom);
			
			if (distance < nom_courant.length()/2.5 ){
				solution.add(acteur_courant);
			}
			else if(nom_courant.contains(nom)){
				solution.add(acteur_courant);
			}
		}
		return solution;
	}
	
	
	
	public static Vector<Film> recherche_film(String s,Repertoire<Film> lesFilms){
		String nom = nom_compact(s);
		Vector<Film> solution = new Vector<Film>();
		Iterator<Film> it_film = lesFilms.iterator();
		
		while (it_film.hasNext()){
			Film film_courant = it_film.next();
			String nom_courant = nom_compact(film_courant.getId());
			int distance = levenshtein(nom_courant,nom);
			
			if (distance < nom_courant.length()/2.5 ){
				solution.add(film_courant);
			}
			else if(nom_courant.contains(nom)){
				solution.add(film_courant);
			}
		}
		return solution;
	}

}


