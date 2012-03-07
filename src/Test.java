import java.util.Scanner; 
import java.util.NoSuchElementException; 
import java.util.Iterator;

/**
 * Exemples de programme qui construit une base de données d'acteurs et de
 * films à partir de fichiers de données.
 *
 * @author Xavier Provençal
 */

public class Test
{


	public static void main( String[] args )
	{
		// Initialisation des répertoires
		Repertoire<Acteur> lesActeurs = new Repertoire<Acteur>();
		Repertoire<Film> lesFilms = new Repertoire<Film>();

		// Lecture des fichiers de données
		// On pout lire ainsi un nombre arbitraire de fichiers de données.
		
		/*
		for (String fichier : args ) 
		{
			LecteurBD.lireDonnees( fichier, lesActeurs, lesFilms );
		}
		*/
		
		LecteurBD.lireDonnees( new String("H:/workspace/cinema/actors.short"), lesActeurs, lesFilms );
		LecteurBD.lireDonnees( new String("H:/workspace/cinema/actresses.short"), lesActeurs, lesFilms );
		
		// Tentative de recherche. Elle échoue car la recherche d'un auteur par
		// son nom requiert d'avoir une chaine de caractères rigoureusement
		// identique à celle entrée dans la base de données.
		
		/*String s = new String("Lino Ventura");
		Acteur A = lesActeurs.rechercher ( s );
		if ( A == null )
		{
			System.out.println( "Aucun \"" + s + "\" n'est présent dans la base de données.");
		}*/

		// Une recherche fructueuse retourne un objet de la classe Acteur.
		// On peut alors l'afficher, consulter sa liste de films 
		
		/* s = new String("Ventura, Lino");
		A = lesActeurs.rechercher ( s );
		if ( A != null )
		{
			System.out.println( "On a trouvé : " + A.getNom() );
			System.out.println( "Voici sa liste de films : ");
			Iterator<Film> it = A.iterator();
			int i = 1;

			while ( it.hasNext() )
			{
				Film f = it.next();
				System.out.println( " " + i + ". " + f.getTitre() );
				++i;
			}

			// On peut aussi, appeler directement 'println' sur l'objet Acteur.
			System.out.println( A );
		}*/
		
		/*Acteur lol= lesActeurs.rechercher("Clooney, George");
		Acteur lol2= lesActeurs.rechercher("Pitt, Brad");
		Film film=lol.filmEnCommun(lol2);
		Film film2=lol2.filmEnCommun(lol);
		System.out.println(film);
		System.out.println(film2);*/
		
		Film film1= lesFilms.rechercher("And They Are Off (1982)");
		Film film3= lesFilms.rechercher("A River Runs Through It (1992)");
		Acteur acteurlol = film1.acteurEnCommun(film3);
		System.out.println(acteurlol);
	}
	
}


