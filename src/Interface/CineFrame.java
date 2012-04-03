package Interface;

import java.awt.*;

import CinemaPackage.*;
import Ecouteurs.*;


public class CineFrame extends Frame{
	final static int HAUTEUR = 600;
	final static int LARGEUR = 600;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;
	private Panel panelImage;
	private Panel panelBot;
	private Panel panelJeu;
	private final static int IMAGE = 0;
	private final static int BOT = 1;
	private final static int JEU = 2;
	private int panelCourant;
	
	
	public CineFrame(Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.setTitle("Cinema -> Menu");
		this.setSize(LARGEUR, HAUTEUR);
		this.setBackground(Color.LIGHT_GRAY);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
		        (screenSize.width-this.getWidth())/2,
		        (screenSize.height-this.getHeight())/2
		        );
		
		
		panelImage = new ImagePanel();
		panelBot = new PanelBot(lesActeurs, lesFilms);
		panelJeu = new PanelJeu(lesActeurs, lesFilms);

		
		Panel panelBouton = new Panel(new FlowLayout(FlowLayout.CENTER));
		Button bot = new Button("Mode Bot");
		Button jeu = new Button("Mode Jeu");
		Button menu = new Button("Menu");
		
		panelBouton.add(bot);
		panelBouton.add(jeu);
		panelBouton.add(menu);
		
		this.panelCourant=IMAGE;
		this.add(this.getPanel(), BorderLayout.CENTER);
		this.add(panelBouton, BorderLayout.NORTH);
		
		/* ecouteur */
		this.addWindowListener(new FermerFenetreEcouteur(this));
		jeu.addActionListener(new BoutonEcouteur(this));
		bot.addActionListener(new BoutonEcouteur(this));
		menu.addActionListener(new BoutonEcouteur(this));
		
		this.setVisible(true);
		
		
	}
	
	public Panel getPanel(){
		switch(this.panelCourant){
			case 0 : return panelImage;
			case 1 : return panelBot;
			case 2 : return panelJeu;
			default : return panelImage;
		}
		
	}
	
	public void setPanelBot(){
		this.remove(this.getPanel());
		this.panelCourant=BOT;
		this.add(this.panelBot, BorderLayout.CENTER);
	}
	
	public void setPanelJeu(){
		this.remove(this.getPanel());
		this.panelCourant=JEU;
		this.add(this.panelJeu, BorderLayout.CENTER);
	}
	
	public void setPanelImage(){
		this.remove(this.getPanel());
		this.panelCourant=IMAGE;
		this.add(this.panelImage, BorderLayout.CENTER);
	}
	
	
	// a tester sur windows parce que sur linux ca merde un peu
	public void reconstruire(){ 
		//this.getPanel().doLayout(); // les deux doLayout n'ont pas l'air indispensable 
		this.getPanel().validate(); // (ils ont meme l'air de servire a rien)
		this.getPanel().repaint(); // repainter ici ne sert que pour le reaffichage de l'image du panelImage
		//this.doLayout();
		this.validate();
		//this.repaint(); //repainter le frame ne sert a rien puisque qu'on repaint deja le panelImage au dessus
		
	}
	
}
