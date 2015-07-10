package vocabularytrainer.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import vocabularytrainer.persistence.CSVFileImporter;
import vocabularytrainer.persistence.FileImporter;

public class ToolBar {
	
	private List<FileImporter> fileImporterList;
	
	public ToolBar(WordListTableModel tableModel, JTable wordListTable) {
		fileImporterList.add(new CSVFileImporter());
	}
	
}