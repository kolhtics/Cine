package Algorithme;

import CinemaPackage.*;
import Interface.*;

public class Main {
	
	public static void main( String[] args ){
		// Initialisation des répertoires
		Repertoire<Acteur> lesActeurs = new Repertoire<Acteur>();
		Repertoire<Film> lesFilms = new Repertoire<Film>();
		
		LecteurBD.lireDonnees( new String("data/bidon.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("data/actresses.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("data/actors.short"), lesActeurs, lesFilms );
		
		//AlgoBot.menuConsole(lesActeurs,lesFilms);

		new CineFrame(lesActeurs, lesFilms);
	}
	
}


