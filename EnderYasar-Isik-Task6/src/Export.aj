import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import vocabularytrainer.core.WordList;
import vocabularytrainer.gui.WordListTableModel;

public abstract aspect Export {
	
	static List<FileExporter> exporterList = new ArrayList<FileExporter>();
	
	static public void addFileExporter(FileExporter fileExporter) {
		exporterList.add(fileExporter);
	}
	
//	Da alle Kinder diese Eigenschaften vererben und auf diese Weise dreimal die Icons in der Toolbar und die Menüeintrage
//	auftreten, wurde dieser Codeausschnitt sinngemäß in XML verschoben (siehe Konfigurationen).
	
//	before(ToolBar toolBar) : call(private void vocabularytrainer.gui.ToolBar.setToolBar()) && this(toolBar){
//		JButton fileSaveAsButton = new JButton(new ImageIcon(getClass().getResource("/save-as.png")));
//		fileSaveAsButton.setBorderPainted(false);
//		toolBar.getToolBar().add(fileSaveAsButton);
//		fileSaveAsButton.addActionListener(new FileSaveAsListener(toolBar.getTableModel(), exporterList));
//	}
//	
//	before(MenuBar menuBar) : call(private void vocabularytrainer.gui.MenuBar.setFileMenu()) && this(menuBar) {
//		JMenuItem fileSaveAsItem = new JMenuItem("Vokabelliste speichern als");
//		menuBar.getFileMenu().add(fileSaveAsItem);
//		fileSaveAsItem.addActionListener(new FileSaveAsListener(menuBar.getTableModel(), exporterList));
//		menuBar.getFileMenu().addSeparator();
//	}
	
	interface FileExporter {
		public void exportFile(WordList wordList, String filename);
		public FileFilter getFileFilter();
		public String getFileSuffix();
	}
	
	class FileSaveAsListener implements ActionListener {

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
		
		public WordListTableModel getTableModel() {
			return tableModel;
		}

		public List<FileExporter> getFileExporterList() {
			return fileExporterList;
		}

	}
}