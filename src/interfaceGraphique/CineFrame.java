package interfaceGraphique;

import java.awt.*;

import javax.swing.JTabbedPane;


import cinemaPackage.*;
import ecouteurs.*;



public class CineFrame extends Frame{
	private static final long serialVersionUID = 1L;
	final static int HAUTEUR = 600;
	final static int LARGEUR = 920;
	private Panel panelRechercher;
	private Panel panelBot;
	private Panel panelJeu;
	
	private JTabbedPane onglet;
	
	
	public CineFrame(Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.setTitle("Cinema -> Menu");
		this.setSize(LARGEUR, HAUTEUR);
		this.setBackground(Color.LIGHT_GRAY);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
		        (screenSize.width-this.getWidth())/2,
		        (screenSize.height-this.getHeight())/2
		        );
		
		
		panelRechercher = new PanelRechercher(lesActeurs, lesFilms);
		panelBot = new PanelBot(lesActeurs, lesFilms);
		panelJeu = new PanelJeu(lesActeurs, lesFilms);
		
		onglet = new JTabbedPane();
		onglet.add("Rechercher", panelRechercher);
		onglet.add("Jouer", panelJeu);
		onglet.add("Robot", panelBot);
		this.add(onglet);
		

		/* ecouteur */
		this.addWindowListener(new FermerFenetreEcouteur(this));
		this.setVisible(true);
		
		
	}
	public void reconstruire(){ 
		this.validate();
	}
	
}
