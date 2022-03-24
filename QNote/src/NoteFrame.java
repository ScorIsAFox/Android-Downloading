import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NoteFrame extends JFrame implements ActionListener, ChangeListener, MouseMotionListener{

	protected PopMenu menu = new PopMenu();
	private JSlider slider;
	private TitleBar bar = new TitleBar(350, 35);
	private NoteContent file = new NoteContent();
	private NoteArea text;	

	private boolean onTop = false;
	private boolean editable = true;
	private int lastX,lastY;
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
		
		/* Add listeners*/
		this.buttonActions();
		this.menuActions();
		this.windowActions();

		this.validate();
		
	}
	
	private void jspCreation() {
		text = new NoteArea(326, 460, file, menu);
		MyScrollPane jsp = new MyScrollPane(
	            text,
	            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
	    );
		this.getContentPane().add(jsp);
	}
	
	private void buttonActions() {
		bar.quit.addActionListener(this);
		bar.mini.addActionListener(this);
		bar.top.addActionListener(this);
		bar.lock.addActionListener(this);
		bar.addMouseMotionListener(this);
	}
	
	private void menuActions() {
		menu.lightM.addActionListener(this);
		menu.darkM.addActionListener(this);
		menu.diaM.addActionListener(this);
		menu.hideM.addActionListener(this);
		menu.helpM.addActionListener(this);
	}
	
	private void windowActions() {
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				text.requestFocusInWindow();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				file.saveOperation(text);
				
			}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}

		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* Buttons Actions*/
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
			file.saveOperation(text);
			text.setEditable(editable);
		}
		
		/* PopMenu Actions*/
		if (e.getSource() == menu.lightM) {
			this.setBackground(new Color(255, 69, 0, 80));
			//this.setBackground(new Color(0, 191, 255, 80));// Blue Color,better use in summer mode
		}
		if (e.getSource() == menu.darkM) {
			this.setBackground(new Color(0, 0, 0, 80));
		}
		
		if (e.getSource() == menu.diaM) {
			NoteDialog sliderW = new NoteDialog("Diaphaneity", this.getLocation().x, this.getLocation().y);
			sliderW.createSliderDialog();
			sliderW.slider.addChangeListener(this);
			slider = sliderW.slider;
			sliderW.setVisible(true);
		}

		if (e.getSource() == menu.hideM) {
			bar.setVisible(menu.hide);
			menu.hide = !menu.hide;
			if (menu.hide) {
				menu.hideM.setText("Hide the Title Bar ¡Ì");
			} else {
				menu.hideM.setText("Hide the Title Bar");
			}
		}
		
		if (e.getSource() == menu.helpM) {
			NoteDialog helpW = new NoteDialog("Help", this.getLocation().x, this.getLocation().y);
			helpW.createHelpDialog();
			helpW.setVisible(true);
		}
		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		this.setBackground(new Color(getBackground().getRed(),
				getBackground().getGreen(),
				getBackground().getBlue(),
				slider.getValue()));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.setLocation(this.getLocation().x + e.getX() - lastX, 
				this.getLocation().y + e.getY() - lastY);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		lastX = e.getX();
		lastY = e.getY();
	}
	
}
