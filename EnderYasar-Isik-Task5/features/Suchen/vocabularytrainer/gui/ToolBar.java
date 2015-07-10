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

	public ToolBar(WordListTableModel tableModel, JTable wordListTable) {
	}
	
	private void setToolBar() {
		original();
		
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
	
}
