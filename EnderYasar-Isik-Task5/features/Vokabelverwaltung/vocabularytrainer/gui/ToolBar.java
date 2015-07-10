package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JToolBar;

import vocabularytrainer.gui.controller.RemoveWordPairListener;

public class ToolBar {
	
	private JToolBar mainToolBar;
	private WordListTableModel tableModel;
	private JTable wordListTable;
	
	public ToolBar(WordListTableModel tableModel, JTable wordListTable) {	
		this.tableModel = tableModel;
		this.wordListTable = wordListTable;
		mainToolBar = new JToolBar();
		mainToolBar.setFloatable(false);
		setToolBar();
	}
	
	private void setToolBar() {
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
	
	public JToolBar getToolBar() {
		return mainToolBar;
	}
	
}
