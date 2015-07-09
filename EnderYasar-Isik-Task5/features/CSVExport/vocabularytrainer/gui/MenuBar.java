package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;

import vocabularytrainer.gui.controller.FileOpenListener;
import vocabularytrainer.gui.controller.FileSaveAsListener;
import vocabularytrainer.gui.controller.RemoveWordPairListener;
import vocabularytrainer.persistence.CSVFileExporter;
import vocabularytrainer.persistence.FileExporter;
import vocabularytrainer.persistence.FileImporter;

public class MenuBar {
	
//	private List<FileExporter> fileExporterList;
	
	public MenuBar(WordListTableModel tableModel, JTable wordListTable) {
		original(null, null);
		fileExporterList.add(new CSVFileExporter());
	}
	
}
