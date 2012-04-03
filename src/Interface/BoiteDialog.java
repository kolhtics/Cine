package Interface;

import java.awt.*;
import java.awt.event.*;

import CinemaPackage.*;
import Interface.*;
import Algorithme.*;
import Ecouteurs.*;


public class BoiteDialog extends Dialog implements ActionListener, WindowListener{
	private Frame f;
	private String message;
	
	public BoiteDialog(Frame f, String message){
		super(f, "Attention", true);
		this.setLayout(new BorderLayout());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
		        (screenSize.width-this.getWidth())/2,
		        (screenSize.height-this.getHeight())/2
		        );
		
		
		Label l = new Label(message, Label.CENTER);
		Button ok = new Button("Fermer");
		this.add(l, BorderLayout.CENTER);
		this.add(ok, BorderLayout.SOUTH);
		
		this.setSize(message.length()*5+50,150);
		
		this.addWindowListener(this);
		ok.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	public void windowClosing(WindowEvent e){
		this.dispose();
	}

	
	// toutes ces methode ne servent a rien
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
