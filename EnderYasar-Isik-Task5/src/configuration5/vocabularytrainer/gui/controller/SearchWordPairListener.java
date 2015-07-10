package vocabularytrainer.gui.controller; 

import java.awt.Component; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

import javax.swing.JDialog; 
import javax.swing.JTable; 
import javax.swing.JTextField; 
import javax.swing.SwingUtilities; 

import vocabularytrainer.core.WordPair; 
import vocabularytrainer.gui.WordListTableModel; 

public  class  SearchWordPairListener  implements ActionListener {
	
	
	private JTable wordListTable;

	
	private WordListTableModel tableModel;

	
	private JTextField searchWordTextField;

	
	
	public SearchWordPairListener(JTable wordListTable, WordListTableModel tableModel, JTextField searchWordTextField) {
		this.wordListTable = wordListTable;
		this.tableModel = tableModel;
		this.searchWordTextField = searchWordTextField;
	}

	

	public void actionPerformed(ActionEvent e) {
		int index = 0;
		for(WordPair wordPair : tableModel.getData().getWordList()) {
			if(wordPair.getGermanWord().equals(searchWordTextField.getText()) | wordPair.getForeignWord().equals(searchWordTextField.getText())) {
				break;
			} else {
				index++;
			}
		}
		wordListTable.changeSelection(index, 0, false, false);
		
		Component component = (Component) e.getSource();
		JDialog dialog = (JDialog) SwingUtilities.getRoot(component);
		dialog.dispose();
	}


}
