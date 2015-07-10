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

public class CheckWordPairFromForeignToGermanListener implements ActionListener {
	
	private InquiryOrder inquiryOrder;
	private JTextField germanTextField;
	private JTextField foreignTextField;
	private JTextField correctnessField;
	private JLabel unpracticedWordsValueLabel;
	private JLabel correctPracticesValueLabel;
	private JLabel incorrectPracticesValueLabel;
	private JLabel correctnessRateValueLabel;

	public CheckWordPairFromForeignToGermanListener(JTextField germanTextField, JTextField foreignTextField, JTextField correctnessField, JLabel unpracticedWordsValueLabel, JLabel correctPracticesValueLabel, JLabel incorrectPracticesValueLabel, JLabel correctnessRateValueLabel, InquiryOrder inquiryOrder) {
		this.inquiryOrder = inquiryOrder;
		this.germanTextField = germanTextField;
		this.foreignTextField = foreignTextField;
		this.correctnessField = correctnessField;
		this.unpracticedWordsValueLabel = unpracticedWordsValueLabel;
		this.correctPracticesValueLabel = correctPracticesValueLabel;
		this.incorrectPracticesValueLabel = incorrectPracticesValueLabel;
		this.correctnessRateValueLabel = correctnessRateValueLabel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(germanTextField.getText().equals(inquiryOrder.getNextWordPair().getGermanWord())) {
			correctnessField.setBackground(Color.GREEN);
		} else {
			correctnessField.setBackground(Color.RED);
		}
		inquiryOrder.incrementIndex();
		if(!inquiryOrder.isEndOfListReached()) {
			foreignTextField.setText(inquiryOrder.getNextWordPair().getForeignWord());
			germanTextField.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Übung erfolgreich abgeschlossen.", "Information", JOptionPane.INFORMATION_MESSAGE);
			Component component = (Component) e.getSource();
	        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
	        frame.dispose();
		}
	}
	
}
