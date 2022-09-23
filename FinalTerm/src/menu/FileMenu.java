package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import frame.DrawingPanel;
import global.Constants.EFileItem;

public class FileMenu extends Menu {
	private static final long serialVersionUID = 1L;
	private File dir;
	private File file;

	private DrawingPanel drawingPanel;

	public FileMenu(String name) {
		super(name);
		
		for(EFileItem eFileItem: EFileItem.values()) {
			JMenuItem menuItem = eFileItem.getMenuItem();
			menuItem.setActionCommand(eFileItem.getActionCommend());
			menuItem.addActionListener(actionHandler);
			menuItem.setFont(new Font("맑은 고딕", Font.ITALIC,15));
			menuItem.setBackground(Color.LIGHT_GRAY);
			menuItem.setForeground(Color.pink);
			this.add(menuItem);
		}
		EFileItem.eNew.getMenuItem().setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK));
		EFileItem.eClose.getMenuItem().setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
		EFileItem.eExit.getMenuItem().setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_MASK));
		EFileItem.eOpen.getMenuItem().setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_MASK));
		EFileItem.ePrint.getMenuItem().setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_MASK));
		EFileItem.eSave.getMenuItem().setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
		EFileItem.eSaveAS.getMenuItem().setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_MASK));
		
	}

	public void initialize(DrawingPanel drawingPanel) {
		super.initialize(drawingPanel);
		this.drawingPanel = drawingPanel;
		this.file = null;
		this.dir = null;
		
	}
	private boolean checkSave(){
		boolean cancel = false;
		int reply = JOptionPane.NO_OPTION;
		if (this.drawingPanel.isUpdated()) {
			reply = JOptionPane.showConfirmDialog(this.drawingPanel, "변경내용을 저장하시겠습니까?");
			if (reply == JOptionPane.CANCEL_OPTION) {
				cancel = true;
			}
		}

		if (!cancel) {
			if (reply == JOptionPane.OK_OPTION) {
				this.save();
			}
		}
		return cancel;
	}
	
	
		
	public void nnew() {
		boolean cancel = this.checkSave();
		if (!cancel) {
			this.drawingPanel.setShapes(null);
		}
	}

	public void open() {
		boolean cancel = this.checkSave();
		if (!cancel) {
		JFileChooser chooser = new JFileChooser(this.dir);
		chooser.setSelectedFile(file);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "gvs");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this.drawingPanel);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.dir = chooser.getCurrentDirectory();
			this.file = chooser.getSelectedFile();
			this.readObject();
			}
		}
	}

	public void readObject() {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(this.file)));
			Object object = objectInputStream.readObject();
			this.drawingPanel.setShapes(object);
			objectInputStream.close();
		} catch (IOException e2) {
			((Throwable) e2).printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void writeObject() {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			objectOutputStream.writeObject(this.drawingPanel.getShapes());
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		if (this.file == null) {
			this.saveAs();
		}
		else{
			this.writeObject();
		}
	}

	public void saveAs() {
		JFileChooser chooser = new JFileChooser(this.dir);
		chooser.setSelectedFile(file);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "gvs");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(this.drawingPanel);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.dir = chooser.getCurrentDirectory();
			this.file = chooser.getSelectedFile();
			this.writeObject();
		}
		
	}

	public void close() {
		boolean cancel = this.checkSave();
		if (!cancel) {
			this.drawingPanel.setShapes(null);
		}
	}

	public void print() {
		
	}

	public void exit() {
		boolean cancel = this.checkSave();
		if (!cancel) {
			System.exit(0);
		}
	}
	
}
