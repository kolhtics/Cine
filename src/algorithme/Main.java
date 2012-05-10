package algorithme;


import interfaceGraphique.*;
import cinemaPackage.*;

public class Main {
	
	public static void main( String[] args ) throws InterruptedException{
		// Initialisation des r√©pertoires
		Repertoire<Acteur> lesActeurs = new Repertoire<Acteur>();
		Repertoire<Film> lesFilms = new Repertoire<Film>();
				
		WindowImage windowDemarrage = new WindowImage();
		
		LecteurBD.lireDonnees( new String("data/actresses.short"), lesActeurs, lesFilms );
		LecteurBD.lireDonnees( new String("data/actors.short"), lesActeurs, lesFilms );

		windowDemarrage.dispose();

		new CineFrame(lesActeurs, lesFilms);
		
		//System.out.print(AlgoRecherche.recherche_acteur("robert",lesActeurs).toString());
		//System.out.println("\n Distance : "+AlgoRecherche.levenshtein("Clooney, Gerge", "Sellon, Charles"));
		
		/*Iterator<Acteur> iti = lesActeurs.iterator();
		Iterator<Acteur> itj = lesActeurs.iterator();
		int nb_err = 0;
		while(iti.hasNext()){
			Acteur courant=iti.next();
			while(iti.hasNext()){
				Acteur suivant=itj.next();
				try{
					AlgoBot.plusCourteChaine(lesActeurs, lesFilms, courant , suivant );
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("erreur n∞" + nb_err +"couple :"+courant.getNom()+"  et :"+suivant.getNom());
					nb_err++;
				}
			}
		}*/
	}
}


