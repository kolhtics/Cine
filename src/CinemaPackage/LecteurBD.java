package cinemaPackage;

//ne sert a rien (servait pour la barre de progression)
/*import java.lang.Process;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;*/


/**
 * Classe pour construire les répertoires d'acteurs et de films à partir de
 * fichiers de données.
 *
 * @author Xavier Provençal
 *
 */


public class LecteurBD
{

	///**
	// * Retourne le nombre de lignes contenues dans le fichier
	// * identifié par la chaine 'fichier'
	// */
	
	//ne sert a rien (servait pour la barre de progression)
	
	/*private static int getNombreDeLignes( String fichier ) 
	{
		String s = null;
		String commande = new String("wc -l ") + fichier;
		Integer nb_lignes = new Integer(-1);
		try 
		{
			Process process= Runtime.getRuntime().exec( commande );
			InputStreamReader isr = new InputStreamReader(process.getInputStream()); 
			BufferedReader b= new BufferedReader( isr );

			s = b.readLine();
			nb_lignes = new Integer( s.substring(0, s.indexOf(" ") ) );
			while (s != null)
			{
				s= b.readLine();
			}
			process.waitFor();
			process.destroy();
		}
		catch ( IOException e ) { }
		catch ( InterruptedException e ) {}

		return nb_lignes.intValue();

	}*/


	/**
	 * Lit le contenu du fichier identidfié par la chaîne 'fichier',
	 * ajoute les données sur les acteurs et les films aux répertoires
	 * 'lesActeurs' et 'lesFilms'
	 */
	public static void lireDonnees( String fichier, 
			Repertoire<Acteur> lesActeurs, 
			Repertoire<Film> lesFilms )
	{
		LecteurLigneParLigne lll = new LecteurLigneParLigne( fichier );

		System.out.println("# Lecture du fichier de données : " + fichier );
		//int nb_lignes_total = getNombreDeLignes( fichier ); ne sert a rien (servait pour la barre de progression)
		while ( ! lll.lectureTermine() )
		{
			String s = lll.ligneSuivante();
			if ( s.length() > 0 )
			{
				int debut = s.indexOf( "{" );
				int fin = s.indexOf( "}", debut);
				String nom = s.substring( debut+1, fin );
				Acteur A = new Acteur( nom );
				lesActeurs.ajouter( A );
				while ( -1 != ( debut = s.indexOf( "{", fin ) ) )
				{
					fin = s.indexOf( "}", debut );
					String titre = s.substring( debut+1, fin );
					Film F = lesFilms.rechercher( titre );
					if (F == null )
					{
						F = new Film( titre );
					}
					lesFilms.ajouter( F );
					A.ajouterFilm( F );
					F.ajouterActeur( A );
				}
			}
		}
		System.out.println( "Lecture terminée" );
	}

}
