package algorithme;


import interfaceGraphique.*;
import cinemaPackage.*;

public class Main {
	
	public static void main( String[] args ) throws InterruptedException{
		// Initialisation des répertoires
		Repertoire<Acteur> lesActeurs = new Repertoire<Acteur>();
		Repertoire<Film> lesFilms = new Repertoire<Film>();
				
		WindowImage windowDemarrage = new WindowImage();
				
		LecteurBD.lireDonnees( new String("data/bidon.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("data/actresses.short"), lesActeurs, lesFilms );
		LecteurBD.lireDonnees( new String("data/actors.short"), lesActeurs, lesFilms );
		
		windowDemarrage.dispose();

		new CineFrame(lesActeurs, lesFilms);
		
		//System.out.print(AlgoRecherche.recherche_acteur("robert",lesActeurs).toString());
		//System.out.println("\n Distance : "+AlgoRecherche.levenshtein("Clooney, Gerge", "Sellon, Charles"));
	}
	
}


