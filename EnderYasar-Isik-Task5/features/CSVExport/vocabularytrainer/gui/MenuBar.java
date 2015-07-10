package vocabularytrainer.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import vocabularytrainer.persistence.CSVFileExporter;
import vocabularytrainer.persistence.FileExporter;

public class MenuBar {
	
	private List<FileExporter> fileExporterList;

	private void setFileMenu() {
		fileExporterList.add(new CSVFileExporter());
		original();
	}
}
