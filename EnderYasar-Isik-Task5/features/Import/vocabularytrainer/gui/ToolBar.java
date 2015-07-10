package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JToolBar;

import vocabularytrainer.gui.controller.FileOpenListener;
import vocabularytrainer.persistence.FileImporter;

public class ToolBar {
	
	private List<FileImporter> fileImporterList = new ArrayList<FileImporter>();
	
	private void setToolBar() {
		original();
		JButton fileOpenButton = new JButton(new ImageIcon(getClass().getResource("/folder.png")));
		fileOpenButton.setBorderPainted(false);
		mainToolBar.add(fileOpenButton);
		fileOpenButton.addActionListener(new FileOpenListener(tableModel, fileImporterList));
	}
	
}
