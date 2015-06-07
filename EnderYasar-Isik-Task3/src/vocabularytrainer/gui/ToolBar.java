package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JToolBar;

//#ifdef Import
import vocabularytrainer.gui.controller.FileOpenListener;
//#endif
//#ifdef Export
import vocabularytrainer.gui.controller.FileSaveAsListener;
//#endif
import vocabularytrainer.gui.controller.RemoveWordPairListener;

public class ToolBar {
	
	private JToolBar mainToolBar;
	@SuppressWarnings("unused")
	private WordListTableModel tableModel;
	@SuppressWarnings("unused")
	private JTable wordListTable;
	
	public ToolBar(final WordListTableModel tableModel, final JTable wordListTable) {
		this.tableModel = tableModel;
		this.wordListTable = wordListTable;
		mainToolBar = new JToolBar();
		mainToolBar.setFloatable(false);
		
		//#ifdef Import
		JButton fileOpenButton = new JButton(new ImageIcon(getClass().getResource("/folder.png")));
		fileOpenButton.setBorderPainted(false);
		mainToolBar.add(fileOpenButton);
		fileOpenButton.addActionListener(new FileOpenListener(tableModel));
		//#endif
		
		//#ifdef Export
		JButton fileSaveAsButton = new JButton(new ImageIcon(getClass().getResource("/save-as.png")));
		fileSaveAsButton.setBorderPainted(false);
		mainToolBar.add(fileSaveAsButton);
		fileSaveAsButton.addActionListener(new FileSaveAsListener(tableModel));
		//#endif

		JButton addWordButton = new JButton(new ImageIcon(getClass().getResource("/add.png")));
		mainToolBar.addSeparator();
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

		//#ifdef Suchen
		JButton searchWordButton = new JButton(new ImageIcon(getClass().getResource("/search.png")));
		searchWordButton.setBorderPainted(false);
		mainToolBar.add(searchWordButton);
		searchWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SearchDialog searchDialog = new SearchDialog(tableModel, wordListTable);
			}
		});
		//#endif
	}
	
	public JToolBar getToolBar() {
		return mainToolBar;
	}

}
