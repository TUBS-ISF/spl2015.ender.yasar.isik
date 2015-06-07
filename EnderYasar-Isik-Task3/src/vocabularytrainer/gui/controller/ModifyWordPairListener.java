package vocabularytrainer.gui.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import vocabularytrainer.gui.WordListTableModel;

public class ModifyWordPairListener implements ActionListener {
	
	private WordListTableModel tableModel;
	private int index;
	private JTextField germanTextField;
	private JTextField foreignTextField;
	
	public ModifyWordPairListener(WordListTableModel tableModel, int index, JTextField germanTextField, JTextField foreignTextField) {
		this.tableModel = tableModel;
		this.index = index;
		this.germanTextField = germanTextField;
		this.foreignTextField = foreignTextField;
	}

	public void actionPerformed(ActionEvent e) {
		tableModel.setValueAt(germanTextField.getText(), index, 0);
		tableModel.setValueAt(foreignTextField.getText(), index, 1);
		
		Component component = (Component) e.getSource();
        JDialog dialog = (JDialog) SwingUtilities.getRoot(component);
        dialog.dispose();
	}

}
