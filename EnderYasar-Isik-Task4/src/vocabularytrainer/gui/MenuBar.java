package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;



//#ifdef Import
import vocabularytrainer.gui.controller.FileOpenListener;
//#endif
//#ifdef Export
import vocabularytrainer.gui.controller.FileSaveAsListener;
//#endif
import vocabularytrainer.gui.controller.RemoveWordPairListener;
import vocabularytrainer.persistence.FileExporter;
import vocabularytrainer.persistence.FileImporter;

public class MenuBar {
	
	private JMenuBar mainMenuBar;
	private WordListTableModel tableModel;
	private JTable wordListTable;
	private FileImporter fileImporter;
	private FileExporter fileExporter;
	
	public MenuBar(WordListTableModel tableModel, JTable wordListTable, FileImporter fileImporter, FileExporter fileExporter) {
		this.tableModel = tableModel;
		this.wordListTable = wordListTable;
		this.fileImporter = fileImporter;
		this.fileExporter = fileExporter;
		mainMenuBar = new JMenuBar();
		mainMenuBar.add(getFileMenu());
		mainMenuBar.add(getEditMenu());
	}
	
	public JMenuBar getMenuBar() {
		return mainMenuBar;
	}
	
	private JMenu getFileMenu() {
		JMenu fileMenu = new JMenu("Datei");
		
		if(fileImporter != null) {
			JMenuItem fileOpenItem = new JMenuItem("Vokabelliste öffnen");
			fileMenu.add(fileOpenItem);
			fileOpenItem.addActionListener(new FileOpenListener(tableModel));
		}

		if(fileExporter != null) {
			JMenuItem fileSaveAsItem = new JMenuItem("Vokabelliste speichern als");
			fileMenu.add(fileSaveAsItem);
			fileSaveAsItem.addActionListener(new FileSaveAsListener(tableModel));
		}

		JMenuItem shutdownProgramItem = new JMenuItem("Vokabeltrainer beenden");
		fileMenu.addSeparator();
		fileMenu.add(shutdownProgramItem);
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

		//#ifdef Suchen
		JMenuItem searchWordItem = new JMenuItem("Vokabel suchen");
		editMenu.addSeparator();
		editMenu.add(searchWordItem);
		searchWordItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SearchDialog searchDialog = new SearchDialog(tableModel, wordListTable);
			}
		});
		//#endif
		
		return editMenu;
	}
}
