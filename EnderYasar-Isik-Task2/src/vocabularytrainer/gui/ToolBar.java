package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import vocabularytrainer.core.ProductLineConfiguration;
import vocabularytrainer.persistence.FileManagement;
import vocabularytrainer.persistence.FileType;

public class ToolBar {
	
	private JToolBar mainToolBar;
	@SuppressWarnings("unused")
	private DefaultTableModel tableModel;
	@SuppressWarnings("unused")
	private JTable wordListTable;
	
	public ToolBar(final DefaultTableModel tableModel, final JTable wordListTable) {
		this.tableModel = tableModel;
		this.wordListTable = wordListTable;
		mainToolBar = new JToolBar();
		mainToolBar.setFloatable(false);
		
//		JButton newFileButton = new JButton(new ImageIcon("Icons/file.png"));
//		JButton fileOpenButton = new JButton(new ImageIcon("Icons/folder.png"));
//		JButton fileSaveAsButton = new JButton(new ImageIcon("Icons/save-as.png"));
//		JButton addWordButton = new JButton(new ImageIcon("Icons/add.png"));
//		JButton modifyWordButton = new JButton(new ImageIcon("Icons/modify.png"));
//		JButton removeWordButton = new JButton(new ImageIcon("Icons/delete.png"));
//		JButton searchWordButton = new JButton(new ImageIcon("Icons/search.png"));
		
		JButton fileOpenButton = new JButton(new ImageIcon(getClass().getResource("/folder.png")));
		JButton fileSaveAsButton = new JButton(new ImageIcon(getClass().getResource("/save-as.png")));
		JButton addWordButton = new JButton(new ImageIcon(getClass().getResource("/add.png")));
		JButton modifyWordButton = new JButton(new ImageIcon(getClass().getResource("/modify.png")));
		JButton removeWordButton = new JButton(new ImageIcon(getClass().getResource("/delete.png")));
		JButton searchWordButton = new JButton(new ImageIcon(getClass().getResource("/search.png")));
		
		if(!ProductLineConfiguration.importXML) {
			fileOpenButton.setEnabled(false);
		}
		if(!ProductLineConfiguration.exportXML) {
			fileSaveAsButton.setEnabled(false);
		}
		
//		mainToolBar.add(newFileButton);
		mainToolBar.add(fileOpenButton);
		mainToolBar.add(fileSaveAsButton);
		mainToolBar.addSeparator();
		mainToolBar.add(addWordButton);
		mainToolBar.add(modifyWordButton);
		mainToolBar.add(removeWordButton);
		mainToolBar.add(searchWordButton);
		
		fileOpenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(fileChooser);
				FileManagement fileManagementListener = new FileManagement(FileType.XML);
				
				if(!tableModel.getDataVector().isEmpty()) {
					tableModel.getDataVector().removeAllElements();
				}
				for(Vector<String> wordPair : fileManagementListener.importFile(fileChooser.getSelectedFile().getAbsolutePath())) {
					tableModel.addRow(wordPair);
				}
			}
		});
		fileSaveAsButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				if(tableModel.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Es sind keine Vokabeln in der Liste hinterlegt.", "Warnung", JOptionPane.WARNING_MESSAGE);
				} else {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.showSaveDialog(fileChooser);
					FileFilter filter = new FileNameExtensionFilter("Dateityp", "xml");
					fileChooser.addChoosableFileFilter(filter);
					FileManagement fileManagementListener = new FileManagement(FileType.XML);
					fileManagementListener.exportFile(tableModel.getDataVector(), fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		addWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AddModifyDialog addDialog = new AddModifyDialog(tableModel, -1);
			}
		});
		modifyWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AddModifyDialog modifyDialog = new AddModifyDialog(tableModel, wordListTable.getSelectedRow());
			}
		});
		removeWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WordListManagement wordListManagement = new WordListManagement(tableModel);
				wordListManagement.removeWordPair(wordListTable.getSelectedRows());
			}
		});
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

}
