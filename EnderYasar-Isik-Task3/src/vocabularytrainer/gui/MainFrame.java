package vocabularytrainer.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import vocabularytrainer.core.WordList;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final String title = "Vokabeltrainer";
	
	public MainFrame() {
		super();
		WordList wordList = new WordList();
		WordListTableModel tableModel = new WordListTableModel(wordList);
		
		JPanel panel = new JPanel(new MigLayout(new LC().wrap()));
		MainPanel mainPanel = new MainPanel(tableModel);
		MenuBar mainMenuBar = new MenuBar(tableModel, mainPanel.getWordListTable());
		ToolBar mainToolBar = new ToolBar(tableModel, mainPanel.getWordListTable());
		
		panel.add(mainMenuBar.getMenuBar());
		panel.add(mainToolBar.getToolBar());
		panel.add(mainPanel.getMainPanel());
		
		setContentPane(panel);
		setLocationRelativeTo(null);
		setTitle(title);
		setResizable(false);
	}
	
}
