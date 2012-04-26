package interfaceGraphique;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WindowImage extends Window{
	private final int NB_IMAGES = 5;

	private static final long serialVersionUID = 1L;


	public WindowImage(){
		super(null);
		this.setSize(480, 200);
		this.setVisible(true);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
		        (screenSize.width-this.getWidth())/2,
		        (screenSize.height-this.getHeight())/2
		        );
		
	}
	
	public int randomImage(){
		int random = (int)(Math.random()*NB_IMAGES);
		System.out.println("Je suis la methode random et je m'execute :"+random);
		return random;
	}
	
	
	public void paint(Graphics g){
		try {
			String image = String.valueOf(randomImage())+".png";
            Image img = ImageIO.read(new File("images/"+image));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			} catch (IOException e) {
            
		}
	}
}
