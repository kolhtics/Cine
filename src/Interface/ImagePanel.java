package Interface;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import CinemaPackage.*;
import Interface.*;
import Algorithme.*;
import Ecouteurs.*;

import javax.imageio.ImageIO;

public class ImagePanel extends Panel{
	
	public ImagePanel(){
	}
	
	public void paint(Graphics g){
		try {
            Image img = ImageIO.read(new File("Cinema.jpg"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			} catch (IOException e) {
            
		}

	}

}
