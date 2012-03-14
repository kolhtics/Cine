
import java.util.*;


/**
 * Exemples de programme qui construit une base de données d'acteurs et de
 * films à partir de fichiers de données.
 *
 * @author Xavier Provençal
 */

public class Test
{
	public void plusCourteChaine(Repertoire<Acteur> lesActeurs,Repertoire<Film> lesFilms,Acteur acteurDepart,Acteur cible){
		
		Set<Film> objectif=new TreeSet<Film>();
		Set<Film> filmVus=new TreeSet<Film>();
		Queue<Film> fileAttente=new LinkedList<Film>();
		Map<Film,Film> antecedents=new HashMap<Film,Film>();
		Set<Acteur> acteurVus=new TreeSet<Acteur>();
		boolean trouve=false;
		
		Iterator<Film> it_cible = cible.iterator();
		Iterator<Film> it_depart = acteurDepart.iterator();
		while (it_cible.hasNext()){
			objectif.add(it_cible.next());
		}
		while (it_depart.hasNext()){
			fileAttente.add(it_depart.next());
			filmVus.add(it_depart.next());
		}
		
		Film f=fileAttente.poll();
		while (!trouve && f != null ){
			
			if (filmVus.contains(f)){
				trouve=true;
			}
			else {
				voisin_en_attente(f, fileAttente, antecedents, filmVus, acteurVus);
			}
			f=fileAttente.poll();
		}
		if (trouve){
			Queue<Acteur> solution=construire_chaine(antecedents,f);
			afficher_chaine(acteurDepart,solution,cible);
		}
		else{
			System.out.println("Aucune cha�ne n'est possible");
		}
	}

	public static void voisin_en_attente(Film f,Queue<Film> fileAttente,Map<Film,Film> antecedents,Set<Film> filmVus,Set<Acteur> acteurVus){
		Iterator<Acteur> it_acteurs = f.iterator();

		while (it_acteurs.hasNext()){
			if (!acteurVus.contains(it_acteurs.next())){
				acteurVus.add(it_acteurs.next());
				Iterator<Film> it_films = it_acteurs.next().iterator();
				while (it_films.hasNext()){
					Film f2=it_films.next();
					if (!filmVus.contains(f2)){
						filmVus.add(f2);
						fileAttente.add(f2);
						antecedents.put(f2,f);   // JE SUIS PAS SUR !! LOL
					}
				}
			}
		}
	}
	
	public Queue<Acteur> construire_chaine(Map<Film,Film> antecedents,Film dernier){
		Queue<Acteur> fileAttente=new LinkedList<Acteur>();
		Film f=dernier;
		while (antecedents.containsKey(f)){
			// TODO auto-generated sub string
		}
		return null;
	}
	
	public void afficher_chaine(Acteur acteurDepart,Queue<Acteur> solution,Acteur cible){
		
	}
	
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
		
		LecteurBD.lireDonnees( new String("C:/eclipse/Workspace/actors.short"), lesActeurs, lesFilms );
		LecteurBD.lireDonnees( new String("C:/eclipse/Workspace/actresses.short"), lesActeurs, lesFilms );

		// exemple recherche 
		
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
		
		Acteur lol= lesActeurs.rechercher("Clooney, George");
		Acteur lol2= lesActeurs.rechercher("Pitt, Brad");
		Film film=lol.filmEnCommun(lol2);
		Film film2=lol2.filmEnCommun(lol);
		System.out.println(film);
		System.out.println(film2);
		
		Film film1= lesFilms.rechercher("And They Are Off (1982)");
		Film film3= lesFilms.rechercher("A River Runs Through It (1992)");
		Acteur acteurlol = film1.acteurEnCommun(film3);
		System.out.println(acteurlol);
	}
	
}


