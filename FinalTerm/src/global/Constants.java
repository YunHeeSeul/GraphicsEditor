package global;

import menu.ColorMenu;
import menu.EditMenu;
import menu.FileMenu;
import menu.Menu;
import shape.TOval;
import shape.TLine;
import shape.TPolygon;
import shape.TRectangle;
import shape.TShape;

import java.awt.Cursor;
import javax.swing.JMenuItem;

public class Constants {
	public final static int MAINFRAME_X = 200;
	public final static int MAINFRAME_Y = 100;
	public final static int MAINFRAME_W = 600;
	public final static int MAINFRAME_H = 600;

	public enum EMenu {
		eFileMenu(new FileMenu("File")), eEditMenu(new EditMenu("Edit")), eColorMenu(new ColorMenu("Color"));

		private Menu menu;

		private EMenu(Menu menu) {
			this.menu = menu;
		}

		public Menu getMenu() {
			return this.menu;
		}

	}

	public enum EFileItem {
		eNew(new JMenuItem("new"), "nnew"), eOpen(new JMenuItem("open"), "open"), eSave(new JMenuItem("save"),
				"save"), eSaveAS(new JMenuItem("saveAs"), "saveAs"), eClose(new JMenuItem("close"),
						"close"), ePrint(new JMenuItem("print"), "print"), eExit(new JMenuItem("exit"), "exit");

		private JMenuItem menuItem;
		private String actionCommand;

		private EFileItem(JMenuItem menuItem, String actionCommand) {
			this.menuItem = menuItem;
			this.actionCommand = actionCommand;
		}

		public JMenuItem getMenuItem() {
			return this.menuItem;
		}

		public String getActionCommend() {
			return this.actionCommand;
		}
	}

	public enum EEditMenu {
		eGroup(new JMenuItem("group"), "group"), eUnGroup(new JMenuItem("unGroup"), "unGroup"), eCut(
				new JMenuItem("cut"), "cut"), eCopy(new JMenuItem("copy"), "copy"), ePaste(new JMenuItem("paste"),
						"paste"), eDelete(new JMenuItem("delete"),
								"delete"), eDo(new JMenuItem("do"), "ddo"), eUnDo(new JMenuItem("unDo"), "unDo");

		private JMenuItem menuItem;
		private String actionCommand;

		private EEditMenu(JMenuItem menuItem, String actionCommand) {
			this.menuItem = menuItem;
			this.actionCommand = actionCommand;
		}

		public JMenuItem getMenuItem() {
			return this.menuItem;
		}

		public String getActionCommend() {
			return this.actionCommand;
		}
	}

	public enum EColorMenu {
		eLine(new JMenuItem("line"), "line"), eFill(new JMenuItem("fill"), "fill");

		private JMenuItem menuItem;
		private String actionCommand;

		private EColorMenu(JMenuItem menuItem, String actionCommand) {
			this.menuItem = menuItem;
			this.actionCommand = actionCommand;
		}

		public JMenuItem getMenuItem() {
			return this.menuItem;
		}

		public String getActionCommend() {
			return this.actionCommand;
		}
	}

	public enum EToolMenu {
		rectangle("C:/Users/À±Èñ½½/Pictures/RectangleBasic.png","C:/Users/À±Èñ½½/Pictures/RectanglePressed.png", new TRectangle()), 
		ellipse("C:/Users/À±Èñ½½/Pictures/EllipseBasic.png", "C:/Users/À±Èñ½½/Pictures/EllipsePressed.png", new TOval()), 
		line("C:/Users/À±Èñ½½/Pictures/lineBasic.png", "C:/Users/À±Èñ½½/Pictures/linePressed.png",new TLine()), 
		polygon("C:/Users/À±Èñ½½/Pictures/PolygonBasic.png", "C:/Users/À±Èñ½½/Pictures/PolygonPressed.png", new TPolygon());

		private String iconFileName;
		private String iconSLTFileName;
		private TShape selectedTool;

		private EToolMenu(String iconFileName, String iconSLTFileName, TShape selectedTool) {
			this.iconFileName = iconFileName;
			this.iconSLTFileName = iconSLTFileName;
			this.selectedTool = selectedTool;
		}

		public String getIconFileName() {
			return this.iconFileName;
		}

		public String getIconSLTFileName() {
			return this.iconSLTFileName;
		}

		public TShape getSelectedTool() {
			return this.selectedTool;
		}

	}

	public enum EAnchors {
		N(Cursor.N_RESIZE_CURSOR), S(Cursor.S_RESIZE_CURSOR), E(Cursor.E_RESIZE_CURSOR), W(Cursor.W_RESIZE_CURSOR), NE(
				Cursor.NE_RESIZE_CURSOR), NW(Cursor.NW_RESIZE_CURSOR), SE(
						Cursor.SE_RESIZE_CURSOR), SW(Cursor.SW_RESIZE_CURSOR), R(Cursor.W_RESIZE_CURSOR);

		private int cursorType;

		private EAnchors(int cursorType) {
			this.cursorType = cursorType;
		}

		public int getCursor() {
			return this.cursorType;
		}

	};

}
