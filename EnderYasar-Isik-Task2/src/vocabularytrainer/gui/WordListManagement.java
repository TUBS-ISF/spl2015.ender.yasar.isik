package vocabularytrainer.gui;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class WordListManagement {
	
	private DefaultTableModel tableModel;
	
	public WordListManagement(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
	
	public void addWordPair(String germanWord, String foreignWord) {
		Vector<String> wordPair = new Vector<String>();
		wordPair.add(germanWord);
		wordPair.add(foreignWord);
		tableModel.addRow(wordPair);
	}
	
	public void modifyWordPair(int index, String germanWord, String foreignWord) {
		tableModel.setValueAt(germanWord, index, 0);
		tableModel.setValueAt(foreignWord, index, 1);
	}
	
	public void removeWordPair(int[] indices) {
		for(int i = 0; i < indices.length; i++) {
			tableModel.removeRow(indices[0]);
		}
	}
	
	@SuppressWarnings("unchecked")
	public int searchWord(String word) {
		int index = 0;
		for(Vector<String> vector : (Vector<Vector<String>>) tableModel.getDataVector()) {
			if(vector.elementAt(0).equals(word) | vector.elementAt(1).equals(word)) {
				return index;
			} else {
				index++;
			}
		}
		return -1;
	}
	
}
