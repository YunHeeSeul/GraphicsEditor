package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JMenu;

import frame.DrawingPanel;

public class Menu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	private DrawingPanel drawingPanel;
	ActionHandler actionHandler;
	
	public Menu(String name){
		super(name);
		this.actionHandler = new ActionHandler();
	}
	
	public void initialize(DrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}

	protected DrawingPanel getDrawingPanel(){
		return this.drawingPanel;
	}
	
	private void invokeMethod(String methodName){
		try {
			this.getClass().getMethod(methodName).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			invokeMethod(event.getActionCommand());
		}
	}
}
