package vocabularytrainer.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;

import vocabularytrainer.persistence.FileExporter;
import vocabularytrainer.persistence.FileImporter;
import vocabularytrainer.gui.controller.FileOpenListener;


public class MenuBar {
	
	private WordListTableModel tableModel;
	private JMenu fileMenu;
	private List<FileImporter> fileImporterList = new ArrayList<FileImporter>();
	
	private void setFileMenu() {
		JMenuItem fileOpenItem = new JMenuItem("Vokabelliste öffnen");
		fileMenu.add(fileOpenItem);
		fileOpenItem.addActionListener(new FileOpenListener(tableModel, fileImporterList));
		original();
	}
	
}
