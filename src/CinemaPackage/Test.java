package CinemaPackage;


import java.util.*;

public class Test {
	
	
	//Methode de test graphique qui marche
	public static void testGraphique(String act1, String act2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		Acteur a1= lesActeurs.rechercher(act1);
		Acteur a2= lesActeurs.rechercher(act2);
		plusCourteChaine(lesActeurs,lesFilms,a1,a2); //qui affiche encore le resultat en console
	}
	
	public static void menuConsole(Repertoire<Acteur> lesActeurs,Repertoire<Film> lesFilms){
		System.out.println("Acteur 1");
		Acteur a1= lesActeurs.rechercher(Keyboard.getString());
		System.out.println("Acteur 2");
		Acteur a2= lesActeurs.rechercher(Keyboard.getString());
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
			System.out.println("Aucune chaîne n'est possible");
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
						antecedents.put(f2,f);
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
		Acteur dernierAct=a2;
		while (a2!= null ){
			Film f=a1.filmEnCommun(a2);
			System.out.println(f.getTitre());
			System.out.println(a2.getNom());
			a1=a2;
			a2=solution.poll();
			if (a2!=null){dernierAct = a2; }
		}
		Film f=dernierAct.filmEnCommun(cible);
		System.out.println(f.getTitre());
		System.out.println(cible.getNom());
	}
	
	public static void main( String[] args )
	{
		// Initialisation des rÃ©pertoires
		Repertoire<Acteur> lesActeurs = new Repertoire<Acteur>();
		Repertoire<Film> lesFilms = new Repertoire<Film>();
		
		//LecteurBD.lireDonnees( new String("C:/eclipse/Workspace/actors.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("C:/eclipse/Workspace/actresses.short"), lesActeurs, lesFilms );
		
		//LecteurBD.lireDonnees( new String("H:/workspace/Cinema/src/data/actors.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("H:/workspace/Cinema/src/data/actresses.short"), lesActeurs, lesFilms );
		//LecteurBD.lireDonnees( new String("H:/workspace/Cinema/src/data/bidon.short"), lesActeurs, lesFilms );
		
		LecteurBD.lireDonnees( new String("D:/Programmation/Java/Cine/src/data/bidon.short"), lesActeurs, lesFilms );
		
		
		//menuConsole(lesActeurs,lesFilms);
		new CineFrame1(lesActeurs, lesFilms);

	}
	
}


