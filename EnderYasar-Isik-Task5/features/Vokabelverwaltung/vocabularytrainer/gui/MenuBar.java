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

public class MenuBar {
	
	private JMenuBar mainMenuBar;
	private WordListTableModel tableModel;
	private JTable wordListTable;
	private JMenu fileMenu;
	
	public MenuBar(WordListTableModel tableModel, JTable wordListTable) {
		this.tableModel = tableModel;
		this.wordListTable = wordListTable;
		mainMenuBar = new JMenuBar();
		fileMenu = new JMenu("Datei");
		setFileMenu();
		mainMenuBar.add(getEditMenu());
	}
	
	public JMenuBar getMenuBar() {
		return mainMenuBar;
	}
	
	private void setFileMenu() {
		JMenuItem shutdownProgramItem = new JMenuItem("Vokabeltrainer beenden");
		fileMenu.add(shutdownProgramItem);
		shutdownProgramItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mainMenuBar.add(fileMenu);
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

		return editMenu;
	}
}
