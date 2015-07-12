package vocabularytrainer.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

import vocabularytrainer.core.WordList;
import vocabularytrainer.gui.MenuBar;
import vocabularytrainer.gui.ToolBar;
import vocabularytrainer.gui.controller.FileOpenListener;

public abstract aspect Import {
	
	before(ToolBar toolBar) : call(private void vocabularytrainer.gui.ToolBar.setToolBar()) && this(toolBar) {
		JButton fileOpenButton = new JButton(new ImageIcon(getClass().getResource("/folder.png")));
		fileOpenButton.setBorderPainted(false);
		toolBar.getToolBar().add(fileOpenButton);
		fileOpenButton.addActionListener(new FileOpenListener(toolBar.getTableModel(), importerList));
	}
	
	before(MenuBar menuBar) : call(private void vocabularytrainer.gui.MenuBar.setFileMenu()) && this(menuBar) {
		JMenuItem fileOpenItem = new JMenuItem("Vokabelliste öffnen");
		menuBar.getFileMenu().add(fileOpenItem);
		fileOpenItem.addActionListener(new FileOpenListener(menuBar.getTableMode(), importerList));
	}
	
	static List<Import> importerList;
	
	public Import() {
		importerList = new ArrayList<Import>();
	}
	
	public abstract WordList importFile(String filename);
	public abstract FileFilter getFileFilter();
	public abstract String getFileSuffix();
	
	public static void setImporterList(Import importer) {
		importerList.add(importer);
	}

}
