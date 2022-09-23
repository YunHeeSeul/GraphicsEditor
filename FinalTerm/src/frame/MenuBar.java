package frame;
import javax.swing.JMenuBar;

import global.Constants.EMenu;
import menu.FileMenu;

public class MenuBar extends JMenuBar {
	//¼Ó¼º
	private static final long serialVersionUID = 1L;

	//associate
	private DrawingPanel drawingPanel;
	
	public MenuBar() {
		
		for(EMenu eMenu: EMenu.values()){
			this.add(eMenu.getMenu());
		}
	}
	
	public void initialize(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		
		for(EMenu eMenu: EMenu.values()){
			eMenu.getMenu().initialize(this.drawingPanel);
		}
	}

	public void windowClosing() {
		((FileMenu)(EMenu.eFileMenu.getMenu())).exit();
	}
	
	
	
}
