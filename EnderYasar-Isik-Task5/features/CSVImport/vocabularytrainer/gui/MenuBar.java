package vocabularytrainer.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import vocabularytrainer.persistence.CSVFileImporter;
import vocabularytrainer.persistence.FileImporter;

public class MenuBar {
	
	private List<FileImporter> fileImporterList;
	
	private void setFileMenu() {
		fileImporterList.add(new CSVFileImporter());
		original();
	}
	
}
