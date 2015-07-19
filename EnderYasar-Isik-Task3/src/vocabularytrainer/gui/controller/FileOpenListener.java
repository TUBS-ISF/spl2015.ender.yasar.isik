//#ifdef Import
//@package vocabularytrainer.gui.controller;
//@
//@import java.awt.event.ActionEvent;
//@import java.awt.event.ActionListener;
//@
//@import javax.swing.JFileChooser;
//@import javax.swing.filechooser.FileFilter;
//@import javax.swing.filechooser.FileNameExtensionFilter;
//@
//@import vocabularytrainer.core.WordPair;
//@import vocabularytrainer.gui.WordListTableModel;
//#ifdef CSVImport
//@import vocabularytrainer.persistence.CSVFileImporter;
//#endif
//@import vocabularytrainer.persistence.FileImporter;
//#ifdef XMLImport
//@import vocabularytrainer.persistence.XMLFileImporter;
//#endif
//@
//@public class FileOpenListener implements ActionListener {
//@	
//@	private WordListTableModel tableModel;
//@	
//@	public FileOpenListener(WordListTableModel tableModel) {
//@		this.tableModel = tableModel;
//@	}
//@
//@	public void actionPerformed(ActionEvent e) {
//@		JFileChooser fileChooser = new JFileChooser();
		//#ifdef CSVImport
//@		FileFilter csvType = new FileNameExtensionFilter("CSV-Dateiformat", "csv");
//@		fileChooser.addChoosableFileFilter(csvType);
		//#endif
//@		
		//#ifdef XMLImport
//@		FileFilter xmlType = new FileNameExtensionFilter("XML-Dateiformat", "xml");
//@		fileChooser.addChoosableFileFilter(xmlType);
		//#endif
//@		fileChooser.setAcceptAllFileFilterUsed(false);
//@		fileChooser.showOpenDialog(fileChooser);
//@		
//@		FileImporter fileImporter = null;
		//#ifdef CSVImport
//@		if(fileChooser.getSelectedFile().getName().endsWith(".csv")) {
//@			fileImporter = new CSVFileImporter(); 
//@		}
		//#endif
		//#ifdef XMLImport
//@		if(fileChooser.getSelectedFile().getName().endsWith("xml")) {
//@			fileImporter = new XMLFileImporter();
//@		}
		//#endif
//@		
//@		if(!tableModel.getData().getWordList().isEmpty()) {
//@			tableModel.getData().getWordList().removeAll(tableModel.getData().getWordList());					
//@		}
//@		for(WordPair wordPair : fileImporter.importFile(fileChooser.getSelectedFile().getAbsolutePath()).getWordList()) {
//@			tableModel.addRow(wordPair);
//@		}
//@	}
//@
//@}
//#endif
