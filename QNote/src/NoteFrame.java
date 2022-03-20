import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class NoteFrame extends JFrame implements ActionListener, WindowFocusListener, WindowListener{

	private TitleBar bar = new TitleBar(350, 35);
	private boolean onTop = false;
	private boolean editable = true;
	private NoteContent file = new NoteContent();
	private NoteArea text = new NoteArea(326, 460);
	
	public NoteFrame() {
		file.fileCreate();
		
		/* Set the window */
		this.setTitle("QNote");
		this.setLayout(null);
		this.setResizable(false); //Forbid changing the size of the frame
		this.setSize(350, 505);
		this.setLocation(1450, 50); //Set initial position
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 80));
		Image icon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icons/Fox.png"));
		this.setIconImage(icon);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Set the Components*/
		this.getContentPane().add(bar); //Add the title bar
		this.jspCreation(); //Add the scroll pane
		file.readOperation(text);
		
		/* Add actions & listeners*/
		this.buttonActions();
		this.addWindowListener(this);
		this.addWindowFocusListener(this);

		this.validate();
		
	}
	
	private void jspCreation() {
		JScrollPane jsp = new JScrollPane(
	            text,
	            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
	    );
		jsp.setSize(text.getWidth(), text.getHeight());
		jsp.setLocation(12,35);
		jsp.setBorder(null);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		this.getContentPane().add(jsp);
	}
	
	private void buttonActions() {
		bar.quit.addActionListener(this);
		bar.mini.addActionListener(this);
		bar.top.addActionListener(this);
		bar.lock.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bar.quit) {
			file.saveOperation(text);
			System.exit(0);
		}
		
		if (e.getSource() == bar.mini) {
			this.setExtendedState(ICONIFIED);
		}
		
		if (e.getSource() == bar.top) {
			onTop = !onTop; //change the mode between non-onTop &onTop
			this.setAlwaysOnTop(onTop);
			if (onTop) { //change the icon to express different mode
				ImageIcon icon = new ImageIcon(this.getClass().getResource("/icons/top1.png"));
				bar.top.setIcon(icon);
			} else {
				ImageIcon icon = new ImageIcon(this.getClass().getResource("/icons/top0.png"));
				bar.top.setIcon(icon);
			}
		}
		
		if (e.getSource() == bar.lock) {
			editable = !editable; //change the mode between non-editable and editable mode
			text.modeChange(editable);
			file.saveOperation(text); // save the data
		}
		
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		text.translucentChange(!editable); //set the color. In non-editable mode, text bar is translucent.
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		text.translucentChange(true);
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		text.requestFocusInWindow();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		file.saveOperation(text);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		file.saveOperation(text);
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
