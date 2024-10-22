package interfaceGraphique;

import java.awt.*;
import java.awt.event.*;


public class BoiteDialog extends Dialog implements ActionListener, WindowListener{

	private static final long serialVersionUID = 1L;

	public BoiteDialog(Frame f, String titre, String message){
		super(f, titre, true);
		this.setLayout(new BorderLayout());
			
		Label l = new Label(message, Label.CENTER);
		Button ok = new Button("Fermer");
		this.add(l, BorderLayout.CENTER);
		this.add(ok, BorderLayout.SOUTH);
		
		this.setSize(message.length()*5+50,150);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
		        (screenSize.width-this.getWidth())/2,
		        (screenSize.height-this.getHeight())/2
		        );
		
		this.addWindowListener(this);
		ok.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public BoiteDialog(Frame f, String message){
		this(f, "Attention", message);
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
