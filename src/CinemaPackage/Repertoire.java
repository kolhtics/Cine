package CinemaPackage;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Classe représentant un dictionnaire d'objets.
 * @author Xavier Provençal
 *
 * Les objets contenus dans un Repertoire doivent être identifiables grâce à
 * une fonction 'getId' retournant une String.
 *
 */
public class Repertoire <Item extends Identifiable>
{

	/**
	 * Constructeur par défaut.
	 */
	public Repertoire() 
	{
		lesObjects = new HashMap<String, Item>();
   	}

	/**
	 * Ajoute un item à au répertoire.
	 *
	 * Retourne 'true' si l'item a été ajouté, 'false' s'il était déjà présent
	 */
	public boolean ajouter( Item it )
	{
		if ( lesObjects.containsKey( it.getId() ) )
		{
			return false;
		}
		lesObjects.put( it.getId(), it );
		return true;
	}

	/**
	 * Recheter un item identifié par le nom 's'
	 *
	 * Retourne l'item recherché ou 'null' si l'item n'est pas dans le répertoire.
	 */
	public Item rechercher ( String s )
	{
		return lesObjects.get(s);
	}

	/**
	 * Retourne le nombre d'items dans le répertoire.
	 */
	public int taille( )
	{
		return lesObjects.size();
	}

	/**
	 * Service d'itération
	 */
	public Iterator<Item> iterator()
	{
		return lesObjects.values().iterator();
	}

	private Map<String, Item> lesObjects;
}

