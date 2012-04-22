package algorithme2;


import interfaceGraphique.*;
import cinemaPackage.*;

public class Main {
	
	public static void main( String[] args ) throws InterruptedException{
		// Initialisation des répertoires
		Repertoire<Acteur> lesActeurs = new Repertoire<Acteur>();
		Repertoire<Film> lesFilms = new Repertoire<Film>();
				
		WindowImage windowDemarrage = new WindowImage();
				
		LecteurBD.lireDonnees( new String("data/bidon.short"), lesActeurs, lesFilms );
		LecteurBD.lireDonnees( new String("data/actresses.short"), lesActeurs, lesFilms );
		LecteurBD.lireDonnees( new String("data/actors.short"), lesActeurs, lesFilms );
		
		windowDemarrage.dispose();
		//AlgoBot.menuConsole(lesActeurs,lesFilms);

		new CineFrame(lesActeurs, lesFilms);
		
	}
	
}


