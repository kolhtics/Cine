package algorithme;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import cinemaPackage.*;


public class AlgoBot {
	
	public static void menuConsole(Repertoire<Acteur> lesActeurs,Repertoire<Film> lesFilms){
		System.out.println("Acteur 1");
		Acteur a1= lesActeurs.rechercher(Keyboard.getString());
		System.out.println("Acteur 2");
		Acteur a2= lesActeurs.rechercher(Keyboard.getString());
		String s = plusCourteChaine(lesActeurs,lesFilms,a1,a2);
		System.out.println(s);
	}
	
	public static String jouer(String act1, String act2, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		Acteur a1= lesActeurs.rechercher(act1);
		Acteur a2= lesActeurs.rechercher(act2);
		String s="";
		if (a1 == null && a2 == null){
			s = s+act1+" n'existe pas\n";
			s= s+act2+" n'existe pas\n";
		}
		else if(a1 == null){
			s = s+act1+" n'existe pas\n";
		}
		else if(a2 == null){
			s= s+act2+" n'existe pas\n";
		}
		else s=plusCourteChaine(lesActeurs,lesFilms,a1,a2);
		return s;
		
	}
	
	
	public static String plusCourteChaine(Repertoire<Acteur> lesActeurs,Repertoire<Film> lesFilms,Acteur acteurDepart,Acteur cible){
		Film film= cible.filmEnCommun(acteurDepart);
		if ( film != null){
			return  "==============================================\n" +
					"==================== SOLUTION ================\n" +
					"==============================================\n\n" +
					"L'acteur "+acteurDepart.getNom()+"\n" +
				    "a joue dans : "+film.getId()+"\n" +
				    "avec : "+cible.getNom();
		}
		else {
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
				return getChaine(acteurDepart,solution,cible);
			}
			else{
				return "Aucune chaine n'est possible";
			}
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
		return solution_inverse;
	}

	public static String getChaine(Acteur acteurDepart,Queue<Acteur> solution,Acteur cible){
		String s =  "==============================================\n" +
				    "==================== SOLUTION =================\n" +
				    "==============================================\n\n " + 
				    "L'acteur de depart : "+acteurDepart.getNom()+"\n" +
				    "a  joue dans : ";
		Acteur a1=acteurDepart;
		Acteur a2=solution.poll();
		Acteur dernierAct=a2;
		while (a2!= null ){
			Film f=a1.filmEnCommun(a2);
			try{
				s += f.getTitre()+"\navec : ";  
			}catch (NullPointerException e){
				return "Une erreur est survenue lors de la recherche : Pas de films en communs";
			}
			s += a2.getNom()+"\nqui a  joue dans : ";
			a1=a2;
			a2=solution.poll();
			if (a2!=null){dernierAct = a2; }
		}
		Film f=dernierAct.filmEnCommun(cible);
		s += f.getTitre()+"\navec l'acteur cible : ";
		s += cible.getNom();
		return s;
	}

}
