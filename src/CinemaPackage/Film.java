package CinemaPackage;

import java.util.ArrayList;
import java.util.Iterator;



/**
 * Classe représentant des films.
 * @author Xavier Provençal
 *
 * Un film est la donnée d'un titre et d'un ensemble d'acteurs lesquels
 * figurent dans ce film.
 */

public class Film implements Identifiable , Comparable<Film>
{

	/**
	 * Constructeur à partir d'un nom.
	 */
	public Film( String leTitre )
	{
		titre = leTitre;
		lesActeurs = new ArrayList<Acteur>();
	}

	/**
	 * Retourne le titre du film.
	 */
	public String getTitre()
	{
		return titre;
	}

	/**
	 * Le titre est utilisé comme identificateur.
	 * Retourne le titre du film.
	 */
	public String getId()
	{
		return titre;
	}

	/**
	 * Ajoute un acteur à l'ensemble des acteurs figurant dans le film.
	 */
	public void ajouterActeur( Acteur a )
	{
		lesActeurs.add(a);
	}

	/**
	 * Étant donné un deuxième film, détermine s'ils ont un acteur en commun.
	 * Si oui, retourne un des acteurs en commum.
	 * Si non, retourne 'null';
	 */
	public Acteur acteurEnCommun ( Film autre )
	{
		Iterator<Acteur> it = iterator();
		while (it.hasNext()){
			Acteur a1=it.next();
			Iterator<Acteur> it2 = iterator();
			while (it2.hasNext()){
				if (a1.equals(it2.next())){
					return a1;
				}
			}
		}
		return null;
	}


	/**
	 * Retourne un itérateur sur la liste de acteurs figurant dans le film.
	 */
	public Iterator<Acteur> iterator()
	{
		return lesActeurs.iterator();
	}

	/**
	 * Conversion en string. Est entre autre appellée lors par la fonction
	 * 'System.out.println'
	 */
	public String toString()
	{
		Iterator<Acteur> i = iterator();
		String s = getTitre();
		while ( i.hasNext() )
		{
			Acteur A = i.next();
			s += "\n  |--> " + A.getNom();
		}
		return s;
	}
	
	@Override
	public int compareTo(Film o) {
		return getId().compareTo(o.getId());
	}
	
	private String titre;
	private ArrayList<Acteur> lesActeurs;

}





