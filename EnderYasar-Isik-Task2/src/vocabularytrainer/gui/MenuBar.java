package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import vocabularytrainer.core.ProductLineConfiguration;
import vocabularytrainer.persistence.XMLFileImporter;
import vocabularytrainer.persistence.XMLFileExporter;

public class MenuBar {
	
	private JMenuBar mainMenuBar;
	private DefaultTableModel tableModel;
	private JTable wordListTable;

	public MenuBar(DefaultTableModel tableModel, JTable wordListTable) {
		this.tableModel = tableModel;
		this.wordListTable = wordListTable;
		mainMenuBar = new JMenuBar();
		mainMenuBar.add(getFileMenu());
		mainMenuBar.add(getEditMenu());
	}
	
	public JMenuBar getMenuBar() {
		return mainMenuBar;
	}
	
	private JMenu getFileMenu() {
		JMenu fileMenu = new JMenu("Datei");
//		JMenuItem newFileItem = new JMenuItem("Neue Vokabelliste");
		JMenuItem fileOpenItem = new JMenuItem("Vokabelliste öffnen");
		JMenuItem fileSaveAsItem = new JMenuItem("Vokabelliste speichern als");
		JMenuItem shutdownProgramItem = new JMenuItem("Vokabeltrainer beenden");
		
		if(!ProductLineConfiguration.importXML) {
			fileOpenItem.setEnabled(false);
		}
		if(!ProductLineConfiguration.exportXML) {
			fileSaveAsItem.setEnabled(false);
		}
		
//		fileMenu.add(newFileItem);
		fileMenu.add(fileOpenItem);
		fileMenu.add(fileSaveAsItem);
		fileMenu.addSeparator();
		fileMenu.add(shutdownProgramItem);
		
		fileOpenItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(fileChooser);
				XMLFileImporter xmlImporter = new XMLFileImporter(fileChooser.getSelectedFile().getAbsolutePath());

				if(!tableModel.getDataVector().isEmpty()) {
					tableModel.getDataVector().removeAllElements();
				}
				for(Vector<String> wordPair : xmlImporter.importWordList()) {
					tableModel.addRow(wordPair);
				}
			}
		});
		
		fileSaveAsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showSaveDialog(fileChooser);
				FileFilter filter = new FileNameExtensionFilter("Dateityp", "xml");
				fileChooser.addChoosableFileFilter(filter);
				@SuppressWarnings("unchecked")
				XMLFileExporter xmlExporter = new XMLFileExporter(tableModel.getDataVector(), fileChooser.getSelectedFile().getAbsolutePath());
				xmlExporter.exportWordList();
			}
		});
		
		shutdownProgramItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
				
		return fileMenu;
	}
	
	private JMenu getEditMenu() {
		JMenu editMenu = new JMenu("Bearbeiten");
		JMenuItem addWordItem = new JMenuItem("Vokabel hinzufügen");
		JMenuItem modifyWordItem = new JMenuItem("Vokabel bearbeiten");
		JMenuItem removeWordItem = new JMenuItem("Vokabel entfernen");
		JMenuItem searchWordItem = new JMenuItem("Vokabel suchen");
		
		editMenu.add(addWordItem);
		editMenu.add(modifyWordItem);
		editMenu.add(removeWordItem);
		editMenu.addSeparator();
		editMenu.add(searchWordItem);
		
		addWordItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AddModifyDialog addDialog = new AddModifyDialog(tableModel, -1);
			}
		});
		modifyWordItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AddModifyDialog modifyDialog = new AddModifyDialog(tableModel, wordListTable.getSelectedRow());
			}
		});
		removeWordItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WordListManagement wordListManagement = new WordListManagement(tableModel);
				wordListManagement.removeWordPair(wordListTable.getSelectedRows());
			}
		});
		searchWordItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SearchDialog searchDialog = new SearchDialog(tableModel, wordListTable);
			}
		});
		
		return editMenu;
	}
}
