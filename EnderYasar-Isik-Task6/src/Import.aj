import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import vocabularytrainer.core.WordList;
import vocabularytrainer.core.WordPair;
import vocabularytrainer.gui.WordListTableModel;

public abstract aspect Import {
	
	static List<FileImporter> importerList = new ArrayList<FileImporter>();
	
	static public void addFileImporter(FileImporter fileImporter) {
		importerList.add(fileImporter);
	}

//	Da alle Kinder diese Eigenschaften vererben und auf diese Weise dreimal die Icons in der Toolbar und die Menüeintrage
//	auftreten, wurde dieser Codeausschnitt sinngemäß in XML verschoben (siehe Konfigurationen).
	
//	before(ToolBar toolBar) : call(private void vocabularytrainer.gui.ToolBar.setToolBar()) && this(toolBar) {
//		JButton fileOpenButton = new JButton(new ImageIcon(getClass().getResource("/folder.png")));
//		fileOpenButton.setBorderPainted(false);
//		toolBar.getToolBar().add(fileOpenButton);
//		fileOpenButton.addActionListener(new FileOpenListener(toolBar.getTableModel(), importerList));
//	}
//	
//	before(MenuBar menuBar) : call(private void vocabularytrainer.gui.MenuBar.setFileMenu()) && this(menuBar) {
//		JMenuItem fileOpenItem = new JMenuItem("Vokabelliste öffnen");
//		menuBar.getFileMenu().add(fileOpenItem);
//		fileOpenItem.addActionListener(new FileOpenListener(menuBar.getTableModel(), importerList));
//	}
	
	interface FileImporter {
		public WordList importFile(String filename);
		public FileFilter getFileFilter();
		public String getFileSuffix();
	}
	
	class FileOpenListener implements ActionListener {
		
		private WordListTableModel tableModel;
		private List<FileImporter> fileImporterList;
		
		public FileOpenListener(WordListTableModel tableModel, List<FileImporter> fileImporterList) {
			this.tableModel = tableModel;
			this.fileImporterList = fileImporterList;
		}

		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			FileImporter fileImporter = null;
			
			for(FileImporter importer : fileImporterList) {
				fileChooser.addChoosableFileFilter(importer.getFileFilter());
			}
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.showOpenDialog(fileChooser);
			for(FileImporter importer : fileImporterList) {
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
		
		public WordListTableModel getTableModel() {
			return tableModel;
		}

		public List<FileImporter> getFileImporterList() {
			return fileImporterList;
		}
	}
}