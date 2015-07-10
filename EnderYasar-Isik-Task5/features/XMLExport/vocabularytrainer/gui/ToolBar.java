package vocabularytrainer.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import vocabularytrainer.persistence.XMLFileExporter;
import vocabularytrainer.persistence.FileExporter;

public class ToolBar {
	
	private List<FileExporter> fileExporterList;
	
	public ToolBar(WordListTableModel tableModel, JTable wordListTable) {
		fileExporterList.add(new XMLFileExporter());
	}

}