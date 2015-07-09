package vocabularytrainer.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import vocabularytrainer.core.WordList;
import vocabularytrainer.persistence.CSVFileExporter;
import vocabularytrainer.persistence.CSVFileImporter;
import vocabularytrainer.persistence.FileExporter;
import vocabularytrainer.persistence.FileImporter;
import vocabularytrainer.persistence.XMLFileExporter;
import vocabularytrainer.persistence.XMLFileImporter;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final String title = "Vokabeltrainer";
	
	public MainFrame() {
		super();
		WordList wordList = new WordList();
		WordListTableModel tableModel = new WordListTableModel(wordList);
		
		// Aktivierung der Import und Export Plugins - Wenn ein Import aktiviert wird, dann muss auch ein Export aktiviert werden.
		List<FileImporter> fileImporterList = new ArrayList<FileImporter>();
		fileImporterList.add(new CSVFileImporter());
		fileImporterList.add(new XMLFileImporter());
//		List<FileImporter> fileImporterList = null;
		List<FileExporter> fileExporterList = new ArrayList<FileExporter>();
		fileExporterList.add(new CSVFileExporter());
		fileExporterList.add(new XMLFileExporter());
//		List<FileExporter> fileExporterList = null;
		
		JPanel panel = new JPanel(new MigLayout(new LC().wrap().insets("0")));
		MainPanel mainPanel = new MainPanel(tableModel);
//		MenuBar mainMenuBar = new MenuBar(tableModel, mainPanel.getWordListTable(), fileImporterList, fileExporterList);
//		ToolBar mainToolBar = new ToolBar(tableModel, mainPanel.getWordListTable(), fileImporterList, fileExporterList);
		
		MenuBar mainMenuBar = new MenuBar(tableModel, mainPanel.getWordListTable());
		ToolBar mainToolBar = new ToolBar(tableModel, mainPanel.getWordListTable());
		
		panel.add(mainMenuBar.getMenuBar(), new CC().grow().pushX());
		panel.add(mainToolBar.getToolBar(), new CC().grow().pushX());
		panel.add(mainPanel.getMainPanel(), new CC().grow().gapLeft("5"));
		
		setContentPane(panel);
		setLocationRelativeTo(null);
		setTitle(title);
	}
	
}
