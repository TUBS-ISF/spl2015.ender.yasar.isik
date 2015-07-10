package vocabularytrainer.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import vocabularytrainer.persistence.FileExporter;
import vocabularytrainer.persistence.XMLFileExporter;

public class MenuBar {
	
	private List<FileExporter> fileExporterList;

	private void setFileMenu() {
		original();
		fileExporterList.add(new XMLFileExporter());
	}
	
}
