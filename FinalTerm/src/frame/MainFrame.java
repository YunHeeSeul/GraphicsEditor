package frame;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import global.Constants;

public class MainFrame extends JFrame {
	// 加己 attributes
	private static final long serialVersionUID = 1L;
	// 何前 components
	private MenuBar menuBar;
	private ToolBar toolBar;
	private DrawingPanel drawingPanel;
	private WindowHandler windowHandler;

	public MainFrame() {
		// attributes
		this.setLocation(Constants.MAINFRAME_X, Constants.MAINFRAME_Y);
		this.setSize(Constants.MAINFRAME_W, Constants.MAINFRAME_H);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.windowHandler = new WindowHandler();
		this.addWindowListener(windowHandler);
		this.setLayout(new BorderLayout());	
		
		// components
		this.menuBar = new MenuBar();
		this.setJMenuBar(this.menuBar);

		this.toolBar = new ToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);

		this.drawingPanel = new DrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		// initialize
		this.drawingPanel.initialize();
		this.menuBar.initialize(this.drawingPanel);
		this.toolBar.initialize(this.drawingPanel);
	}

	private class WindowHandler implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			menuBar.windowClosing();
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
