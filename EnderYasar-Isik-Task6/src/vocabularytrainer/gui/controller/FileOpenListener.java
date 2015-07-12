package vocabularytrainer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFileChooser;

import vocabularytrainer.core.WordPair;
import vocabularytrainer.gui.WordListTableModel;
import vocabularytrainer.persistence.Import;

public class FileOpenListener implements ActionListener {
	
	private WordListTableModel tableModel;
	private List<Import> fileImporterList;
	
	public FileOpenListener(WordListTableModel tableModel, List<Import> fileImporterList) {
		this.tableModel = tableModel;
		this.fileImporterList = fileImporterList;
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		Import fileImporter = null;
		
		for(Import importer : fileImporterList) {
			fileChooser.addChoosableFileFilter(importer.getFileFilter());
		}
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.showOpenDialog(fileChooser);
		for(Import importer : fileImporterList) {
			if(fileChooser.getSelectedFile().getName().endsWith(importer.getFileSuffix())) {
				fileImporter = importer;
			}
		}
		
		if(!tableModel.getData().getWordList().isEmpty()) {
			tableModel.getData().getWordList().removeAll(tableModel.getData().getWordList());					
		}
		for(WordPair wordPair : fileImporter.importFile(fileChooser.getSelectedFile().getAbsolutePath()).getWordList()) {
			tableModel.addRow(wordPair);
		}
	}

}
