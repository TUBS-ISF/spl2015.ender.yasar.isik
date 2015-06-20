package vocabularytrainer.gui.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import vocabularytrainer.core.WordPair;
import vocabularytrainer.gui.WordListTableModel;

public class AddWordPairListener implements ActionListener {

	private WordListTableModel tableModel;
	private JTextField germanTextField;
	private JTextField foreignTextField;
	
	public AddWordPairListener(WordListTableModel tableModel, JTextField germanTextField, JTextField foreignTextField) {
		this.tableModel = tableModel;
		this.germanTextField = germanTextField;
		this.foreignTextField = foreignTextField;
	}
	
	public void actionPerformed(ActionEvent e) {
		tableModel.addRow(new WordPair(germanTextField.getText(), foreignTextField.getText()));
		
		Component component = (Component) e.getSource();
        JDialog dialog = (JDialog) SwingUtilities.getRoot(component);
		dialog.dispose();
	}

}
