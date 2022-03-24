import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;

public class NoteArea extends JTextArea implements MouseListener{

	private PopMenu menu = new PopMenu();
		
	public NoteArea (int width, int height, NoteContent file, PopMenu menu) {
		this.setSize(width, height);
		this.setEditable(true);
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		this.setFont(new Font("Adobe ºÚÌå Std R",Font.PLAIN,17));
		this.setForeground(Color.white);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setOpaque(false);
		this.setCaretColor(Color.white);

		file.readOperation(this);
		
		this.menu = menu;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isMetaDown()) {
            menu.showPopupMenu(e.getComponent(), e.getX(), e.getY());
        }
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
