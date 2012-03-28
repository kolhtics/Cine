package CinemaPackage;

import java.awt.*;

/* changer la disposition des boutons */
public class CineFrame1 extends Frame{
	final static int HAUTEUR = 600;
	final static int LARGEUR = 550;
	private Repertoire<Acteur> lesActeurs;
	private Repertoire<Film> lesFilms;
	
	public CineFrame1(Repertoire<Acteur> lesActeurs, Repertoire<Film> lesFilms){
		this.setTitle("Cinema -> Menu");
		this.setSize(LARGEUR, HAUTEUR);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
		        (screenSize.width-this.getWidth())/2,
		        (screenSize.height-this.getHeight())/2
		        );
		
		Panel panelPrincipal = new Panel(new GridLayout(1,2));
		
		ImagePanel panelImage = new ImagePanel();
		
		Panel panelBouton = new Panel(new GridLayout(2,1));
		Button bot = new Button("Mode Bot");
		Button jeu = new Button("Mode Jeu");
		
		panelBouton.add(bot);
		panelBouton.add(jeu);
		
		panelPrincipal.add(panelImage);
		panelPrincipal.add(panelBouton);
		
		this.add(panelPrincipal, BorderLayout.CENTER);
		
		/* ecouteur */
		this.addWindowListener(new FermerFenetreEcouteur(this));
		jeu.addActionListener(new BoutonEcouteur(this, lesActeurs, lesFilms));
		bot.addActionListener(new BoutonEcouteur(this, lesActeurs, lesFilms));
		
		this.setVisible(true);
		
		
	}
	
}
