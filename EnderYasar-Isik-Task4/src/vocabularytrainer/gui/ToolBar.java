package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JToolBar;

import vocabularytrainer.gui.controller.FileOpenListener;
import vocabularytrainer.gui.controller.FileSaveAsListener;
import vocabularytrainer.gui.controller.RemoveWordPairListener;
import vocabularytrainer.persistence.FileExporter;
import vocabularytrainer.persistence.FileImporter;

public class ToolBar {
	
	private JToolBar mainToolBar;
	
	public ToolBar(final WordListTableModel tableModel, final JTable wordListTable, List<FileImporter> fileImporterList, List<FileExporter> fileExporterList) {
		mainToolBar = new JToolBar();
		mainToolBar.setFloatable(false);
		
		if(fileImporterList != null) {
			JButton fileOpenButton = new JButton(new ImageIcon(getClass().getResource("/folder.png")));
			fileOpenButton.setBorderPainted(false);
			mainToolBar.add(fileOpenButton);
			fileOpenButton.addActionListener(new FileOpenListener(tableModel, fileImporterList));
		}
		
		if(fileExporterList != null) {
			JButton fileSaveAsButton = new JButton(new ImageIcon(getClass().getResource("/save-as.png")));
			fileSaveAsButton.setBorderPainted(false);
			mainToolBar.add(fileSaveAsButton);
			fileSaveAsButton.addActionListener(new FileSaveAsListener(tableModel, fileExporterList));
			mainToolBar.addSeparator();
		}

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

		JButton searchWordButton = new JButton(new ImageIcon(getClass().getResource("/search.png")));
		searchWordButton.setBorderPainted(false);
		mainToolBar.add(searchWordButton);
		searchWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SearchDialog searchDialog = new SearchDialog(tableModel, wordListTable);
			}
		});
		
//		JButton executeButton = new JButton(new ImageIcon(getClass().getResource("/execute.png")));
//		executeButton.setBorderPainted(false);
//		mainToolBar.addSeparator();
//		mainToolBar.add(executeButton);
//		
//		JButton configurationButton = new JButton(new ImageIcon(getClass().getResource("/configuration.png")));
//		configurationButton.setBorderPainted(false);
//		mainToolBar.add(configurationButton);
	}
	
	public JToolBar getToolBar() {
		return mainToolBar;
	}
	
}
