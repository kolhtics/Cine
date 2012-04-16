package Interface;

import java.awt.*;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class PanelImage extends Panel{
	
	private static final long serialVersionUID = 1L;

	public PanelImage(){
	}
	
	public void paint(Graphics g){
		try {
            Image img = ImageIO.read(new File("Cinema.jpg"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			} catch (IOException e) {
            
		}

	}

}
