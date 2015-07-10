package vocabularytrainer.gui; 

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

import javax.swing.ImageIcon; 
import javax.swing.JButton; 
import javax.swing.JTable; 
import javax.swing.JToolBar; 
import vocabularytrainer.gui.controller.RemoveWordPairListener; 

import java.util.ArrayList; 
import java.util.List; 

import vocabularytrainer.gui.controller.FileOpenListener; 
import vocabularytrainer.persistence.FileImporter; 

import vocabularytrainer.persistence.CSVFileImporter; 

import vocabularytrainer.persistence.XMLFileImporter; 
import vocabularytrainer.gui.controller.FileSaveAsListener; 
import vocabularytrainer.persistence.FileExporter; 

import vocabularytrainer.persistence.CSVFileExporter; 

import vocabularytrainer.persistence.XMLFileExporter; 

public   class  ToolBar {
	
	
	private JToolBar mainToolBar  ;

	
	private WordListTableModel tableModel;

	
	private JTable wordListTable;

	

	public ToolBar  (WordListTableModel tableModel, JTable wordListTable) {	
		this.tableModel = tableModel;
		this.wordListTable = wordListTable;
		mainToolBar = new JToolBar();
		mainToolBar.setFloatable(false);
		setToolBar();
	
		fileImporterList.add(new XMLFileImporter());
	
	
		fileExporterList.add(new CSVFileExporter());
	
		fileExporterList.add(new XMLFileExporter());
	
	}

	
	
	 private void  setToolBar__wrappee__Vokabelverwaltung() {
		JButton addWordButton = new JButton(new ImageIcon(getClass().getResource("/add.png")));
		addWordButton.setBorderPainted(false);
		mainToolBar.add(addWordButton);
		addWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AddModifyDialog addDialog = new AddModifyDialog(tableModel, -1);
			}
		});

		JButton modifyWordButton = new JButton(new ImageIcon(getClass().getResource("/modify.png")));
		modifyWordButton.setBorderPainted(false);
		mainToolBar.add(modifyWordButton);
		modifyWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AddModifyDialog modifyDialog = new AddModifyDialog(tableModel, wordListTable.getSelectedRow());
			}
		});
		
		JButton removeWordButton = new JButton(new ImageIcon(getClass().getResource("/delete.png")));
		removeWordButton.setBorderPainted(false);
		mainToolBar.add(removeWordButton);
		removeWordButton.addActionListener(new RemoveWordPairListener(wordListTable, tableModel));
	}

	
	
	 private void  setToolBar__wrappee__Import  () {
		setToolBar__wrappee__Vokabelverwaltung();
		JButton fileOpenButton = new JButton(new ImageIcon(getClass().getResource("/folder.png")));
		fileOpenButton.setBorderPainted(false);
		mainToolBar.add(fileOpenButton);
		fileOpenButton.addActionListener(new FileOpenListener(tableModel, fileImporterList));
	}

	
	
	 private void  setToolBar__wrappee__CSVImport  () {
		fileImporterList.add(new CSVFileImporter());
		setToolBar__wrappee__Import();
	}

	
	
	 private void  setToolBar__wrappee__Export() {
		setToolBar__wrappee__CSVImport();
		JButton fileSaveAsButton = new JButton(new ImageIcon(getClass().getResource("/save-as.png")));
		fileSaveAsButton.setBorderPainted(false);
		mainToolBar.add(fileSaveAsButton);
		fileSaveAsButton.addActionListener(new FileSaveAsListener(tableModel, fileExporterList));
	}

	
	
	private void setToolBar() {
		setToolBar__wrappee__Export();
		
		JButton searchWordButton = new JButton(new ImageIcon(getClass().getResource("/search.png")));
		searchWordButton.setBorderPainted(false);
		mainToolBar.add(searchWordButton);
		searchWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SearchDialog searchDialog = new SearchDialog(tableModel, wordListTable);
			}
		});
	}

	
	
	public JToolBar getToolBar() {
		return mainToolBar;
	}

	
	
	private List<FileImporter> fileImporterList  = new ArrayList<FileImporter>();

	
	
	private List<FileExporter> fileExporterList  = new ArrayList<FileExporter>();


}
