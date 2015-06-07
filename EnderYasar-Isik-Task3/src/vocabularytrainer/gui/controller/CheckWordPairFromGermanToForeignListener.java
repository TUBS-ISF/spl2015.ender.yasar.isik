//#ifdef DeutschFremdsprache
package vocabularytrainer.gui.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

//#ifdef Statistik
import vocabularytrainer.core.Statistics;
//#endif
import vocabularytrainer.core.WordList;

public class CheckWordPairFromGermanToForeignListener implements ActionListener {
	
	private int index;
	//#ifdef Statistik
	private Statistics statistics;
	//#endif
	private WordList wordList;
	private JTextField germanTextField;
	private JTextField foreignTextField;
	private JTextField correctnessField;
	private JLabel unpracticedWordsValueLabel;
	private JLabel correctPracticesValueLabel;
	private JLabel incorrectPracticesValueLabel;
	private JLabel correctnessRateValueLabel;

	public CheckWordPairFromGermanToForeignListener(WordList wordList, JTextField germanTextField, JTextField foreignTextField, JTextField correctnessField, JLabel unpracticedWordsValueLabel, JLabel correctPracticesValueLabel, JLabel incorrectPracticesValueLabel, JLabel correctnessRateValueLabel) {
		index = 0;
		//#ifdef Statistik
		statistics = new Statistics(wordList.getWordList().size());
		//#endif
		this.wordList = wordList;
		this.germanTextField = germanTextField;
		this.foreignTextField = foreignTextField;
		this.correctnessField = correctnessField;
		this.unpracticedWordsValueLabel = unpracticedWordsValueLabel;
		this.correctPracticesValueLabel = correctPracticesValueLabel;
		this.incorrectPracticesValueLabel = incorrectPracticesValueLabel;
		this.correctnessRateValueLabel = correctnessRateValueLabel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(foreignTextField.getText().equals(wordList.getWordList().get(index).getForeignWord())) {
			correctnessField.setBackground(Color.GREEN);
			//#ifdef Statistik
			statistics.incrementCorrectPractices();
			correctPracticesValueLabel.setText(Integer.toString(statistics.getCorrectPractices()));
			//#endif
		} else {
			correctnessField.setBackground(Color.RED);
			//#ifdef Statistik
			statistics.incrementIncorrectPractices();
			incorrectPracticesValueLabel.setText(Integer.toString(statistics.getIncorrectPractices()));
			//#endif
		}
		index++;
		if(index < wordList.getWordList().size()) {
			germanTextField.setText(wordList.getWordList().get(index).getGermanWord());
			foreignTextField.setText("");
		}
		//#ifdef Statistik
		unpracticedWordsValueLabel.setText(Integer.toString(statistics.getNumberOfWords()-statistics.getNumberOfPracticedWords()));
		correctnessRateValueLabel.setText(String.format("%.2f %%", statistics.getCorrectnessRate()));
		//#endif
	}
}
//#endif
