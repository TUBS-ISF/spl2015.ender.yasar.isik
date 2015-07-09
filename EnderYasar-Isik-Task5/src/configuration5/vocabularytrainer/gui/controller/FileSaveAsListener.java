package vocabularytrainer.gui.controller; 

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.util.List; 

import javax.swing.JFileChooser; 
import javax.swing.JOptionPane; 

import vocabularytrainer.gui.WordListTableModel; 
import vocabularytrainer.persistence.FileExporter; 

public  class  FileSaveAsListener  implements ActionListener {
	

	private WordListTableModel tableModel;

	
	private List<FileExporter> fileExporterList;

	
	
	public FileSaveAsListener(WordListTableModel tableModel, List<FileExporter> fileExporterList) {
		this.tableModel = tableModel;
		this.fileExporterList = fileExporterList;
	}

	
	
	public void actionPerformed(ActionEvent e) {
		if(tableModel.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Es sind keine Vokabeln in der Liste hinterlegt.", "Warnung", JOptionPane.WARNING_MESSAGE);
		} else {
			JFileChooser fileChooser = new JFileChooser();
			for(FileExporter fileExporter : fileExporterList) {
				fileChooser.addChoosableFileFilter(fileExporter.getFileFilter());
			}
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.showSaveDialog(fileChooser);
			
			String filename = null;
			for(FileExporter fileExporter : fileExporterList) {
				if(fileChooser.getFileFilter().getDescription().equals(fileExporter.getFileFilter().getDescription())) {
					if(!fileChooser.getSelectedFile().getName().endsWith(fileExporter.getFileSuffix())) {
						filename = fileChooser.getSelectedFile().getAbsolutePath() + fileExporter.getFileSuffix();
					} else {
						filename = fileChooser.getSelectedFile().getAbsolutePath();
					}
					fileExporter.exportFile(tableModel.getData(), filename);
				}
			}
		}		
	}


}
