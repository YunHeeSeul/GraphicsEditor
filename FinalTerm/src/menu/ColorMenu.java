package menu;

import java.awt.Color;
import java.awt.event.InputEvent;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import frame.DrawingPanel;
import global.Constants.EColorMenu;
import global.Constants.EEditMenu;

public class ColorMenu extends Menu {
	private static final long serialVersionUID = 1L;

	private DrawingPanel drawingPanel;
	

	public ColorMenu(String name) {
		super(name);

		for (EColorMenu eColorMenu : EColorMenu.values()) {
			JMenuItem menuItem = eColorMenu.getMenuItem();
			menuItem.setActionCommand(eColorMenu.getActionCommend());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
		EColorMenu.eFill.getMenuItem().setAccelerator(KeyStroke.getKeyStroke('F', InputEvent.SHIFT_MASK));
		EColorMenu.eLine.getMenuItem().setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.SHIFT_MASK));
	}

	public void line() {
		Color color=JColorChooser.showDialog(this.getDrawingPanel(), "Line Color", this.getDrawingPanel().getForeground());
		this.getDrawingPanel().setLineColor(color);
	}

	public void fill() {
		Color color=JColorChooser.showDialog(this.getDrawingPanel(), "Fill Color", this.getDrawingPanel().getForeground());
		this.getDrawingPanel().setFillColor(color);
	}

	public void initialize(DrawingPanel drawingPanel) {
		super.initialize(drawingPanel);
	}
}
