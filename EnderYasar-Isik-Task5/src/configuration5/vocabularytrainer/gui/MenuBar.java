package vocabularytrainer.gui; 

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

import java.util.ArrayList; 
import java.util.List; 

import javax.swing.JMenu; 
import javax.swing.JMenuBar; 
import javax.swing.JMenuItem; 
import javax.swing.JTable; 

import vocabularytrainer.gui.controller.RemoveWordPairListener; 

import vocabularytrainer.persistence.FileExporter; 
import vocabularytrainer.persistence.FileImporter; 
import vocabularytrainer.gui.controller.FileOpenListener; 

import vocabularytrainer.persistence.CSVFileImporter; 

import vocabularytrainer.persistence.XMLFileImporter; 

import vocabularytrainer.gui.controller.FileSaveAsListener; 

import vocabularytrainer.persistence.CSVFileExporter; 
import vocabularytrainer.persistence.XMLFileExporter; 

public   class  MenuBar {
	
	
	private JMenuBar mainMenuBar  ;

	
	private WordListTableModel tableModel  ;

	
	private JTable wordListTable  ;

	
	private JMenu fileMenu  ;

	
	
	public MenuBar  (WordListTableModel tableModel, JTable wordListTable) {
		this.tableModel = tableModel;
		this.wordListTable = wordListTable;
		mainMenuBar = new JMenuBar();
		fileMenu = new JMenu("Datei");
		setFileMenu();
		mainMenuBar.add(getEditMenu());
	
	
		fileExporterList = new ArrayList<FileExporter>();
	
	
	}

	
	
	public JMenuBar getMenuBar() {
		return mainMenuBar;
	}

	
	
	 private void  setFileMenu__wrappee__Vokabelverwaltung  () {
		JMenuItem shutdownProgramItem = new JMenuItem("Vokabeltrainer beenden");
		fileMenu.add(shutdownProgramItem);
		shutdownProgramItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mainMenuBar.add(fileMenu);
	}

	
	
	 private void  setFileMenu__wrappee__Import  () {
		JMenuItem fileOpenItem = new JMenuItem("Vokabelliste öffnen");
		fileMenu.add(fileOpenItem);
		fileOpenItem.addActionListener(new FileOpenListener(tableModel, fileImporterList));
		setFileMenu__wrappee__Vokabelverwaltung();
	}

	
	
	 private void  setFileMenu__wrappee__CSVImport  () {
		fileImporterList.add(new CSVFileImporter());
		setFileMenu__wrappee__Import();
	}

	
	
	 private void  setFileMenu__wrappee__XMLImport  () {
		setFileMenu__wrappee__CSVImport();
		fileImporterList.add(new XMLFileImporter());
	}

	
	
	 private void  setFileMenu__wrappee__Export  () {
		JMenuItem fileSaveAsItem = new JMenuItem("Vokabelliste speichern als");
		fileMenu.add(fileSaveAsItem);
		fileSaveAsItem.addActionListener(new FileSaveAsListener(tableModel, fileExporterList));
		setFileMenu__wrappee__XMLImport();
	}

	
	
	 private void  setFileMenu__wrappee__CSVExport  () {
		fileExporterList.add(new CSVFileExporter());
		setFileMenu__wrappee__Export();
	}

	
	
	private void setFileMenu() {
		setFileMenu__wrappee__CSVExport();
		fileExporterList.add(new XMLFileExporter());
	}

	
	
	private JMenu getEditMenu() {
		JMenu editMenu = new JMenu("Bearbeiten");
		JMenuItem addWordItem = new JMenuItem("Vokabel hinzufügen");
		editMenu.add(addWordItem);
		addWordItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AddModifyDialog addDialog = new AddModifyDialog(tableModel, -1);
			}
		});

		JMenuItem modifyWordItem = new JMenuItem("Vokabel bearbeiten");
		editMenu.add(modifyWordItem);
		modifyWordItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AddModifyDialog modifyDialog = new AddModifyDialog(tableModel, wordListTable.getSelectedRow());
			}
		});

		JMenuItem removeWordItem = new JMenuItem("Vokabel entfernen");
		editMenu.add(removeWordItem);
		removeWordItem.addActionListener(new RemoveWordPairListener(wordListTable, tableModel));

		JMenuItem searchWordItem = new JMenuItem("Vokabel suchen");
		editMenu.addSeparator();
		editMenu.add(searchWordItem);
		searchWordItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SearchDialog searchDialog = new SearchDialog(tableModel, wordListTable);
			}
		});
		
		return editMenu;
	}

	
	
	private List<FileImporter> fileImporterList  = new ArrayList<FileImporter>();

	
	
	private List<FileExporter> fileExporterList  = new ArrayList<FileExporter>();


}
