package vocabularytrainer.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import vocabularytrainer.persistence.CSVFileExporter;
import vocabularytrainer.persistence.FileExporter;

public class MenuBar {
	
	private List<FileExporter> fileExporterList;

	public MenuBar(WordListTableModel tableModel, JTable wordListTable) {
		fileExporterList = new ArrayList<FileExporter>();
	}
	
	private void setFileMenu() {
		fileExporterList.add(new CSVFileExporter());
		original();
	}
}
