package vocabularytrainer.gui.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import vocabularytrainer.core.InquiryOrder;
//import vocabularytrainer.core.Statistics;

public class CheckWordPairFromGermanToForeignListener implements ActionListener {
	
	private InquiryOrder inquiryOrder;
//	private Statistics statistics;
	private JTextField germanTextField;
	private JTextField foreignTextField;
	private JTextField correctnessField;
	private JLabel unpracticedWordsValueLabel;
	private JLabel correctPracticesValueLabel;
	private JLabel incorrectPracticesValueLabel;
	private JLabel correctnessRateValueLabel;

	public CheckWordPairFromGermanToForeignListener(JTextField germanTextField, JTextField foreignTextField, JTextField correctnessField, JLabel unpracticedWordsValueLabel, JLabel correctPracticesValueLabel, JLabel incorrectPracticesValueLabel, JLabel correctnessRateValueLabel, InquiryOrder inquiryOrder) {
		this.inquiryOrder = inquiryOrder;
//		this.statistics = statistics;
		this.germanTextField = germanTextField;
		this.foreignTextField = foreignTextField;
		this.correctnessField = correctnessField;
		this.unpracticedWordsValueLabel = unpracticedWordsValueLabel;
		this.correctPracticesValueLabel = correctPracticesValueLabel;
		this.incorrectPracticesValueLabel = incorrectPracticesValueLabel;
		this.correctnessRateValueLabel = correctnessRateValueLabel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(foreignTextField.getText().equals(inquiryOrder.getNextWordPair().getForeignWord())) {
			correctnessField.setBackground(Color.GREEN);
//			if(statistics != null) {
//				statistics.incrementCorrectPractices();
//				correctPracticesValueLabel.setText(Integer.toString(statistics.getCorrectPractices()));
//			}
		} else {
			correctnessField.setBackground(Color.RED);
//			if(statistics != null) {
//				statistics.incrementIncorrectPractices();
//				incorrectPracticesValueLabel.setText(Integer.toString(statistics.getIncorrectPractices()));
//			}
		}
		inquiryOrder.incrementIndex();
		if(!inquiryOrder.isEndOfListReached()) {
			germanTextField.setText(inquiryOrder.getNextWordPair().getGermanWord());
			foreignTextField.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Übung erfolgreich abgeschlossen.", "Information", JOptionPane.INFORMATION_MESSAGE);
			Component component = (Component) e.getSource();
	        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
	        frame.dispose();
		}
//		if(statistics != null) {
//			unpracticedWordsValueLabel.setText(Integer.toString(statistics.getNumberOfWords()-statistics.getNumberOfPracticedWords()));
//			correctnessRateValueLabel.setText(String.format("%.2f %%", statistics.getCorrectnessRate()));
//		}
	}
}
