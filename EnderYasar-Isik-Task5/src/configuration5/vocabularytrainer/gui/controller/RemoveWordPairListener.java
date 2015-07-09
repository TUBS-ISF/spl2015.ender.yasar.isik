package vocabularytrainer.gui.controller; 

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

import javax.swing.JTable; 

import vocabularytrainer.gui.WordListTableModel; 

public  class  RemoveWordPairListener  implements ActionListener {
	

	private JTable wordListTable;

	
	private WordListTableModel tableModel;

	
	
	public RemoveWordPairListener(JTable wordListTable, WordListTableModel tableModel) {
		this.wordListTable = wordListTable;
		this.tableModel = tableModel;
	}

	
	
	public void actionPerformed(ActionEvent e) {
		int[] indices = wordListTable.getSelectedRows();
		for(int i = 0; i < indices.length; i++) {
			tableModel.removeRow(indices[0]);
		}
	}


}
