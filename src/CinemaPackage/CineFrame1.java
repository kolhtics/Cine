package CinemaPackage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/* changer la disposition des boutons */
public class CineFrame1 extends Frame{
	final static int HAUTEUR = 600;
	final static int LARGEUR = 564;
	
	public CineFrame1(){
		this.setTitle("Cinema -> Menu");
		this.setSize(LARGEUR, HAUTEUR);
		this.setLayout(new BorderLayout());
		
		Panel panelPrincipal = new Panel(new GridLayout(1,2));
		
		ImagePanel panelImage = new ImagePanel();
		
		Panel panelBouton = new Panel(new GridLayout(2,1));
		Button bot= new Button("Mode Bot");
		Button jeu =new Button("Mode Jeu");
		
		panelBouton.add(bot);
		panelBouton.add(jeu);
		
		panelPrincipal.add(panelImage);
		panelPrincipal.add(panelBouton);
		
		this.add(panelPrincipal, BorderLayout.CENTER);
		
		/* ecouteur */
		this.addWindowListener(new FermerFenetreEcouteur(this));
		this.setVisible(true);
		
		
	}
	
}
