
import java.util.*;

// PENSER A ECRIRE LES METHODES COMPARE TO DES CLASSES ACTEUR ET FILM

public class Test {
	
	public static void menuConsole(Repertoire<Acteur> lesActeurs,Repertoire<Film> lesFilms){
		System.out.println("Acteur 1");
		Acteur a1= lesActeurs.rechercher(Keyboard.getString());		// PENSER A RECHERCHER SI L'ACTEUR EXISTE 
		System.out.println(a1);
		System.out.println("Acteur 2");
		Acteur a2= lesActeurs.rechercher(Keyboard.getString());
		System.out.println(a2);
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
			Film film_courant = it_depart.next();
			fileAttente.add(film_courant);
			filmVus.add(film_courant);
		}
		
		Film f=fileAttente.poll();
		while (!trouve && f != null ){
			if (objectif.contains(f)){
				System.out.println("JAI TROUVE F");
				trouve=true;
			}
			else {
				System.out.println("je met les voisins en attente ?");
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
			Acteur A = it_acteurs.next();
			if (!acteurVus.contains(A)){
				acteurVus.add(A);
				Iterator<Film> it_films = A.iterator();
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
		return solution_inverse;						// PENSER A INVERSER LA SOLUTION
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
		
		//LecteurBD.lireDonnees( new String("C:/eclipse/Workspace/actors.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("C:/eclipse/Workspace/actresses.short"), lesActeurs, lesFilms );
		LecteurBD.lireDonnees( new String("C:/eclipse/Workspace/bidon.short"), lesActeurs, lesFilms );
		
		
		menuConsole(lesActeurs,lesFilms);
	}
	
}


