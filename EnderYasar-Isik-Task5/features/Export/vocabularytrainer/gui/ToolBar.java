package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JToolBar;

import vocabularytrainer.gui.controller.FileSaveAsListener;
import vocabularytrainer.persistence.FileExporter;

public class ToolBar {
	
	private List<FileExporter> fileExporterList = new ArrayList<FileExporter>();
	
	public ToolBar(WordListTableModel tableModel, JTable wordListTable) {
	}
	
	private void setToolBar() {
		original();
		JButton fileSaveAsButton = new JButton(new ImageIcon(getClass().getResource("/save-as.png")));
		fileSaveAsButton.setBorderPainted(false);
		mainToolBar.add(fileSaveAsButton);
		fileSaveAsButton.addActionListener(new FileSaveAsListener(tableModel, fileExporterList));
	}
}
