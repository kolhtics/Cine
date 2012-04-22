package algorithme2;

import cinemaPackage.Acteur;
import cinemaPackage.Film;
import cinemaPackage.Repertoire;

public class AlgoRecherche {
	public static String rechercher(String s, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
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
}
