package menu;
import javax.swing.JMenuBar;

import frame.DrawingPanel;
import global.Constants.EMenu;


public class GMenuBar extends JMenuBar {
	//¼Ó¼º
	private static final long serialVersionUID = 1L;

	//associate
	private DrawingPanel drawingPanel;
	
	public GMenuBar() {
		
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
	
	
	
}
