package Interface;

import java.awt.TextArea;

public class Console extends TextArea {
	
	
	public Console(String s){
		super(s);
		this.setEditable(false);
	}

}
