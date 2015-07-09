package vocabularytrainer.gui; 

import javax.swing.table.AbstractTableModel; 

import vocabularytrainer.core.WordList; 
import vocabularytrainer.core.WordPair; 

public  class  WordListTableModel  extends AbstractTableModel {
	
	
	private static final long serialVersionUID = -4470252934115920428L;

	
	private WordList wordList;

	
	private String[] columnNames = {"Deutsch", "Fremdsprache"};

	
	
	public WordListTableModel(WordList wordList) {
		super();
		this.wordList = wordList;
	}

	

	public int getRowCount() {
		return wordList.getWordList().size();
	}

	

	public int getColumnCount() {
		return columnNames.length;
	}

	

	public String getValueAt(int rowIndex, int columnIndex) {
		WordPair wordPair = wordList.getWordList().get(rowIndex);
		if(columnIndex == 0) {
			return wordPair.getGermanWord();
		} else if(columnIndex == 1) {
			return wordPair.getForeignWord();
		} else {
			return null;
		}
	}

	
	
	public void setValueAt(String word, int rowIndex, int columnIndex) {
		WordPair wordPair = wordList.getWordList().get(rowIndex);
		if(columnIndex == 0) {
			wordPair.setGermanWord(word);
		} else if(columnIndex == 1) {
			wordPair.setForeignWord(word);
		}
		fireTableDataChanged();
	}

	
	
	public void addRow(WordPair wordPair) {
		wordList.getWordList().add(wordPair);
		fireTableDataChanged();
	}

	
	
	public void removeRow(int index) {
		wordList.getWordList().remove(index);
		fireTableDataChanged();
	}

	
	
	public WordList getData() {
		return wordList;
	}

	
	
	@Override
	public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
	}


}
