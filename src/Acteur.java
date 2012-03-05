import java.util.ArrayList;
import java.util.Iterator;



/**
 * Classe représentant un film.
 * @author Xavier Provençal
 *
 * Un acteur est la donnée d'un nom et d'un ensemble de films dans
 * lesquels l'acteur a joué.
 */

public class Acteur implements Identifiable 
{

	/**
	 * Constructeur à partir d'un nom.
	 */
	public Acteur( String leNom )
	{
		nom = leNom;
		lesFilms = new ArrayList<Film>();
	}

	/**
	 * Retourne le nom de l'acteur.
	 */
	public String getNom()
	{
		return nom;
	}

	/**
	 * Le nom est utilisé comme identificateur. Il est à noter que la base de
	 * données d'IMDB est conçue de manière à ce que deux acteurs de même nom
	 * se voient attribués des identificateurs (I), (II), (III), ... Assurant
	 * ainsi que les chaînes de caractères sont toutes distinctes.
	 *
	 * Retourne le nom de l'acteur.
	 */
	public String getId()
	{
		return nom;
	}

	/**
	 * Ajoute un film à la liste des films de cet acteur 
	 */
	public void ajouterFilm( Film f )
	{
		lesFilms.add(f);
	}

	/**
	 * Étant donné un deuxième acteur, détermine s'ils ont un film en commun.
	 * Si oui, retourne un des films en commum.
	 * Si non, retourne 'null';
	 */
	public Film filmEnCommun( Acteur autre )
	{
		/* À compléter */
		return null;
	}

	/**
	 * Retourne un itérateur sur la liste de films dans lesquels a joué cet acteur.
	 */
	public Iterator<Film> iterator()
	{
		return lesFilms.iterator();
	}

	/**
	 * Conversion en string. Est entre autre appellée lors par la fonction
	 * 'System.out.println'
	 */
	public String toString()
	{
		Iterator<Film> i = iterator();
		String s = getNom();
		while ( i.hasNext() )
		{
			Film F = i.next();
			s += "\n  |--> " + F.getTitre();
		}
		return s;
	}
	


	private String nom;
	private ArrayList<Film> lesFilms;
}




