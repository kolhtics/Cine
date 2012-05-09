package ecouteurs;

import interfaceGraphique.BoiteDialog;

import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import algorithme.AlgoRecherche;

import cinemaPackage.*;

public class ClavierEcouteur extends KeyAdapter {
	private TextField t;
	private Repertoire<Acteur> lesActeurs;
	private Vector<Acteur> propositions;
	private String texte;
	private int indice=0;

	public ClavierEcouteur(TextField t, Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.lesActeurs=lesActeurs;
		this.t=t;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_ENTER:
				if(texte != null && texte.length()>2){
					propositions = AlgoRecherche.recherche_acteur(texte, lesActeurs);
					try{
						t.setText(propositions.elementAt(indice).getNom());
					}catch (ArrayIndexOutOfBoundsException err){
						indice=0;
					}
					if(indice+1 >= propositions.size()){
						indice=0;
					}else indice++;
				}else new BoiteDialog(null ,"Veuillez saisir 3 lettres ou plus");
			break;
		default :
			System.out.println(" texte avant :" +texte);
			texte=t.getText();
			System.out.println(" texte apres :" +texte);
			break;
		}
	}

}
