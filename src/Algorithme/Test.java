package Algorithme;


import java.awt.Frame;
import java.awt.Window;
import java.util.*;

import CinemaPackage.*;
import Interface.*;

public class Test {
	
	public static void main( String[] args )
	{
		// Initialisation des répertoires
		Repertoire<Acteur> lesActeurs = new Repertoire<Acteur>();
		Repertoire<Film> lesFilms = new Repertoire<Film>();
		
		//LecteurBD.lireDonnees( new String("C:/eclipse/Workspace/actors.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("C:/eclipse/Workspace/actresses.short"), lesActeurs, lesFilms );
		
		//LecteurBD.lireDonnees( new String("H:/workspace/Cinema/src/data/actors.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("H:/workspace/Cinema/src/data/actresses.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("H:/workspace/cinema/src/data/bidon.short"), lesActeurs, lesFilms );
		
		//LecteurBD.lireDonnees( new String("/home/baptiste/workspace/Cinema/src/data/bidon.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("/home/baptiste/workspace/Cinema/src/data/actors.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("/home/baptiste/workspace/Cinema/src/data/actresses.short"), lesActeurs, lesFilms );
		
		LecteurBD.lireDonnees( new String("D:/Programmation/Java/Cine/src/data/bidon.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("D:/Programmation/Java/Cine/src/data/actors.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("D:/Programmation/Java/Cine/src/data/actresses.short"), lesActeurs, lesFilms );
		
		//AlgoConsole.menuConsole(lesActeurs,lesFilms);

		CineFrame f = new CineFrame(lesActeurs, lesFilms);
	}
	
}


