package vocabularytrainer.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

import vocabularytrainer.core.WordList;
import vocabularytrainer.gui.MenuBar;
import vocabularytrainer.gui.ToolBar;
import vocabularytrainer.gui.controller.FileSaveAsListener;

public abstract aspect Export {
	
	before(ToolBar toolBar) : call(private void vocabularytrainer.gui.ToolBar.setToolBar()) && this(toolBar){
		JButton fileSaveAsButton = new JButton(new ImageIcon(getClass().getResource("/save-as.png")));
		fileSaveAsButton.setBorderPainted(false);
		toolBar.getToolBar().add(fileSaveAsButton);
		fileSaveAsButton.addActionListener(new FileSaveAsListener(toolBar.getTableModel(), exporterList));
		toolBar.getToolBar().addSeparator();
	}
	
	before(MenuBar menuBar) : call(private void vocabularytrainer.gui.MenuBar.setFileMenu()) && this(menuBar) {
		JMenuItem fileSaveAsItem = new JMenuItem("Vokabelliste speichern als");
		menuBar.getFileMenu().add(fileSaveAsItem);
		fileSaveAsItem.addActionListener(new FileSaveAsListener(menuBar.getTableMode(), exporterList));
		menuBar.getFileMenu().addSeparator();
	}

	static List<Export> exporterList;
	
	public Export() {
		exporterList = new ArrayList<Export>();
	}
	
	public abstract void exportFile(WordList wordList, String filename);
	public abstract FileFilter getFileFilter();
	public abstract String getFileSuffix();
	
	public static void setExporterList(Export exporter) {
		exporterList.add(exporter);
	}
	
}
