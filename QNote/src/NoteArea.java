import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

public class NoteArea extends JTextArea{
	
	public NoteArea (int width, int height) {
		this.setSize(width, height);
		this.setEditable(true);
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		this.setFont(new Font("Adobe ºÚÌå Std R",Font.PLAIN,17));
		this.setForeground(Color.white);
		this.translucentChange(true);
		this.setCaretColor(Color.white);
	}
		
	protected void translucentChange(boolean translucent) { //translucent = true => be translucent
		if (translucent) {
			this.setBackground(new Color(0, 0, 0, 0));
			this.setOpaque(false);
		} else {
			this.setBackground(Color.black);
			this.setOpaque(true);
		}
	}

	protected void modeChange(boolean e) {
		this.setEditable(e); //set the corresponding state of this bar
		this.translucentChange(!e); //e = true => translucent = false
	}
}
