package Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Algorithme.AlgoBot;
import CinemaPackage.Acteur;
import CinemaPackage.Film;
import CinemaPackage.Repertoire;
import Ecouteurs.FermerFenetreEcouteur;

public class SolutionFrame extends Frame implements ActionListener{
	private final int HAUTEUR=300;
	private final int LARGEUR=400;
	
	public SolutionFrame (String s) {
		this.setTitle("Solution");
		this.setSize(LARGEUR, HAUTEUR);
		this.setBackground(Color.LIGHT_GRAY);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
		        (screenSize.width-this.getWidth())/2,
		        (screenSize.height-this.getHeight())/2
		        );
		Panel p= new Panel(new BorderLayout());
		TextArea t= new TextArea();
		Button fermer=new Button("Fermer");
		t.setEditable(false);
		p.add(t,BorderLayout.CENTER);
		p.add(fermer,BorderLayout.SOUTH);
		this.add(p);
		this.setVisible(true);
		t.setText(s);
		this.addWindowListener(new FermerFenetreEcouteur(this));
		fermer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.dispose();
		
	}
}
