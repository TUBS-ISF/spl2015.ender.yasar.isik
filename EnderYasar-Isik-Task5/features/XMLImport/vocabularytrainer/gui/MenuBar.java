package vocabularytrainer.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import vocabularytrainer.persistence.XMLFileImporter;
import vocabularytrainer.persistence.FileImporter;

public class MenuBar {
	
	private List<FileImporter> fileImporterList;
	
	private void setFileMenu() {
		original();
		fileImporterList.add(new XMLFileImporter());
	}
	
}
