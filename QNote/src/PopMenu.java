import java.awt.Component;
import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopMenu extends JPopupMenu{

	protected JMenu themeM = new JMenu("Theme");
	protected JMenuItem lightM = new JMenuItem("Light");
	protected JMenuItem darkM = new JMenuItem("Dark");
	
	protected JMenuItem diaM = new JMenuItem("Diaphaneity");
	protected JMenuItem hideM = new JMenuItem("Hide the Title Bar");
	protected JMenuItem helpM = new JMenuItem("Help");
	
	public boolean hide = false;
	
	public PopMenu() {
		themeM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		lightM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		darkM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		diaM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		hideM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		helpM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		
		themeM.add(lightM);
		themeM.add(darkM);
		
		this.add(themeM);
		this.add(diaM);
		this.add(hideM);
		this.addSeparator();
		this.add(helpM);
	}
	
	public void showPopupMenu(Component invoker, int x, int y) {
		this.show(invoker, x, y);
	}
	
}
