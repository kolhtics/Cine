package ecouteurs;


import java.awt.*;
import java.awt.event.*;


public class FermerFenetreEcouteur extends WindowAdapter {
	private Frame f;
	
	public FermerFenetreEcouteur(Frame f){
		this.f=f;
	}
	

	public void windowClosing(WindowEvent e){
		f.dispose();
	}
}
