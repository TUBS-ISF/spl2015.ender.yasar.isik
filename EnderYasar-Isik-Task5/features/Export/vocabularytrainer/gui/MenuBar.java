package vocabularytrainer.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;

import vocabularytrainer.gui.controller.FileSaveAsListener;
import vocabularytrainer.persistence.FileExporter;

public class MenuBar {
	
	private WordListTableModel tableModel;
	private JMenu fileMenu;
	private List<FileExporter> fileExporterList = new ArrayList<FileExporter>();
	
	private void setFileMenu() {
		JMenuItem fileSaveAsItem = new JMenuItem("Vokabelliste speichern als");
		fileMenu.add(fileSaveAsItem);
		fileSaveAsItem.addActionListener(new FileSaveAsListener(tableModel, fileExporterList));
		original();
	}
	
}
