//#ifdef Export
package vocabularytrainer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import vocabularytrainer.gui.WordListTableModel;
//#ifdef CSVExport
import vocabularytrainer.persistence.CSVFileExporter;
//#endif
import vocabularytrainer.persistence.FileExporter;
//#ifdef XMLExport
import vocabularytrainer.persistence.XMLFileExporter;
//#endif

public class FileSaveAsListener implements ActionListener {

	private WordListTableModel tableModel;
	
	public FileSaveAsListener(WordListTableModel tableModel) {
		this.tableModel = tableModel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(tableModel.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Es sind keine Vokabeln in der Liste hinterlegt.", "Warnung", JOptionPane.WARNING_MESSAGE);
		} else {
			JFileChooser fileChooser = new JFileChooser();
			//#ifdef CSVExport
			FileFilter csvType = new FileNameExtensionFilter("CSV-Dateiformat", "csv");
			fileChooser.addChoosableFileFilter(csvType);
			//#endif
			
			//#ifdef XMLExport
			FileFilter xmlType = new FileNameExtensionFilter("XML-Dateiformat", "xml");
			fileChooser.addChoosableFileFilter(xmlType);
			//#endif
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.showSaveDialog(fileChooser);
			
			FileExporter fileExporter = null;
			String filename = null;
			//#ifdef CSVExport
			if(fileChooser.getFileFilter() == csvType) {
				if(!fileChooser.getSelectedFile().getName().endsWith(".csv")) {
					filename = fileChooser.getSelectedFile().getAbsolutePath() + ".csv";
				} else {
					filename = fileChooser.getSelectedFile().getAbsolutePath();
				}
				fileExporter = new CSVFileExporter();
			}
			//#endif
			//#ifdef XMLExport
			if(fileChooser.getFileFilter() == xmlType) {
				if(!fileChooser.getSelectedFile().getName().endsWith(".xml")) {
					filename = fileChooser.getSelectedFile().getAbsolutePath() + ".xml";
				} else {
					filename = fileChooser.getSelectedFile().getAbsolutePath();
				}
				fileExporter = new XMLFileExporter();
			}
			//#endif
			
			fileExporter.exportFile(tableModel.getData(), filename);
		}		
	}

}
//#endif
