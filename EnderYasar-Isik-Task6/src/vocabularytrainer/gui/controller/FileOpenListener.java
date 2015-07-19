//package vocabularytrainer.gui.controller;
//
//import java.util.List;
//
//import vocabularytrainer.gui.WordListTableModel;
//import vocabularytrainer.persistence.FileImporter;
//
//public class FileOpenListener {
//	
//	private WordListTableModel tableModel;
//	private List<FileImporter> fileImporterList;
//	
//	public FileOpenListener(WordListTableModel tableModel, List<FileImporter> fileImporterList) {
//		this.tableModel = tableModel;
//		this.fileImporterList = fileImporterList;
//	}
//
////	public void actionPerformed(ActionEvent e) {
////		JFileChooser fileChooser = new JFileChooser();
////		FileImporter fileImporter = null;
////		
////		for(FileImporter importer : fileImporterList) {
////			fileChooser.addChoosableFileFilter(importer.getFileFilter());
////		}
////		fileChooser.setAcceptAllFileFilterUsed(false);
////		fileChooser.showOpenDialog(fileChooser);
////		for(FileImporter importer : fileImporterList) {
////			if(fileChooser.getSelectedFile().getName().endsWith(importer.getFileSuffix())) {
////				fileImporter = importer;
////			}
////		}
////		
////		if(!tableModel.getData().getWordList().isEmpty()) {
////			tableModel.getData().getWordList().removeAll(tableModel.getData().getWordList());					
////		}
////		for(WordPair wordPair : fileImporter.importFile(fileChooser.getSelectedFile().getAbsolutePath()).getWordList()) {
////			tableModel.addRow(wordPair);
////		}
////	}
//	
//	public WordListTableModel getTableModel() {
//		return tableModel;
//	}
//
//	public List<FileImporter> getFileImporterList() {
//		return fileImporterList;
//	}
//
//}
