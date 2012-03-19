
import in.keyboard.Keyboard;

import java.util.*;

// PENSER A ECRIRE LES METHODES COMPARE TO DES CLASSES ACTEUR ET FILM

public class Test {
	
	public static void menuConsole(Repertoire<Acteur> lesActeurs,Repertoire<Film> lesFilms){
		System.out.println("Acteur 1");
		Acteur a1=new Acteur(Keyboard.getString());					// PENSER A RECHERCHER SI L'ACTEUR EXISTE 
		System.out.println("Acteur 2");
		Acteur a2=new Acteur(Keyboard.getString());
		plusCourteChaine(lesActeurs,lesFilms,a1,a2);
		
	}
	public static void plusCourteChaine(Repertoire<Acteur> lesActeurs,Repertoire<Film> lesFilms,Acteur acteurDepart,Acteur cible){
		
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
				trouve=true;															// TROUVE NEST JAMAIS MIS A TRUE 
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
			System.out.println("Aucune cha�ne n'est possible");						// SAFFICHE QUELQUE SOIT LES ACTEURS CHERCHES
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
						fileAttente.offer(f2);
						antecedents.put(f2,f);					// pas s�r de cette ligne
					}
				}
			}
		}
	}
	
	public static Queue<Acteur> construire_chaine(Map<Film,Film> antecedents,Film dernier){
		Queue<Acteur> solution_inverse=new LinkedList<Acteur>();
		Film f=dernier;
		while (antecedents.containsKey(f)){
			Film f2=antecedents.get(f);
			Acteur a=f.acteurEnCommun(f2);
			solution_inverse.offer(a);
			f=f2;
		}
		Acteur[] tableau_solution=(Acteur[]) solution_inverse.toArray();
		Queue<Acteur> solution=new LinkedList<Acteur>();
		for (int i= tableau_solution.length -1; i>=0;i--){				// EXISTE T'IL UNE SOLUTION PLUS PROPRE
			solution.add(tableau_solution[i]);
		}
		return solution;
	}
	
	public static void afficher_chaine(Acteur acteurDepart,Queue<Acteur> solution,Acteur cible){
		System.out.println(acteurDepart.getNom());
		Acteur a1=acteurDepart;
		Acteur a2=solution.poll();
		while (a2!= null ){
			Film f=a1.filmEnCommun(a2);
			System.out.println(f.getTitre());
			System.out.println(a2.getNom());
			a1=a2;
			a2=solution.poll();
		}
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
		
		LecteurBD.lireDonnees( new String("E:/SOURCE JAVA/actors.short"), lesActeurs, lesFilms );
		LecteurBD.lireDonnees( new String("E:/SOURCE JAVA/actresses.short"), lesActeurs, lesFilms );

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
		
		menuConsole(lesActeurs,lesFilms);
	}
	
}


