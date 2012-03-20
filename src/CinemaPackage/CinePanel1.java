package CinemaPackage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class CinePanel1 extends Panel{
	private Image img=null;
	
	
	public void paintComponent(Graphics g){//MARCHE PAS
       try {
           Image img = ImageIO.read(new File("Cinema.jpg"));
           g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public CinePanel1(){
		this.setLayout(new BorderLayout());
		
		Button b =new Button("LANCER JEU");
		this.add(b, BorderLayout.SOUTH);
		
		b.addActionListener(new BoutonEcouteur(b, this));
		} 
	
}
