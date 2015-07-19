package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import vocabularytrainer.core.InquiryDirection;
import vocabularytrainer.core.InquiryOrder;
import vocabularytrainer.core.Statistics;
import vocabularytrainer.core.WordList;
import vocabularytrainer.gui.controller.CheckWordPairFromGermanToForeignListener;
import vocabularytrainer.gui.controller.CheckWordPairFromForeignToGermanListener;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class PracticeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private WordList wordList;
	private InquiryDirection inquiryDirection;
	private InquiryOrder inquiryOrder;
	private JLabel wordsValueLable;
	private JLabel unpracticedWordsValueLabel;
	private JLabel correctPracticesValueLabel;
	private JLabel incorrectPracticesValueLabel;
	private JLabel correctnessRateValueLabel;
	private JTextField germanTextField;
	private JTextField foreignTextField;
	private JTextField correctnessField;
	private JPanel panel;
	private Statistics statistics;
	
	public PracticeFrame(WordList wordList, InquiryOrder inquiryOrder, InquiryDirection inquiryDirection) {
		super();
		super.setTitle("Vokabeln üben");
		
		this.wordList = wordList;
		this.inquiryDirection = inquiryDirection;
		this.inquiryOrder = inquiryOrder;
		this.inquiryOrder.setWordList(wordList);
		this.statistics = null;

		panel = new JPanel(new MigLayout(new LC().wrap().alignX("center")));
		panel.add(getTranslationPanel(), new CC().growX());
		panel.add(getCorrectnessPanel(), new CC().growX());
		panel.add(getButtonsPanel(), new CC().growX());
		
		setContentPane(panel);
		setResizable(false);
	    setLocationRelativeTo(null);
		setVisible(true);
		pack();
	}
	
	private JPanel getTranslationPanel() {
		JPanel translationPanel = new JPanel(new MigLayout(new LC().wrapAfter(2)));
		JLabel germanLabel = new JLabel("Deutsch");
		JLabel foreignLabel = new JLabel("Fremdsprache");
		germanTextField = new JTextField();
		foreignTextField = new JTextField();
		
		// DeutschFremdsprache
		if(inquiryDirection == InquiryDirection.GERMAN_TO_FOREIGN) {
			germanTextField.setText(inquiryOrder.getNextWordPair().getGermanWord());
			germanTextField.setEnabled(false);
		}
		// FremdspracheDeutsch
		if(inquiryDirection == InquiryDirection.FOREIGN_TO_GERMAN) {
			foreignTextField.setText(inquiryOrder.getNextWordPair().getForeignWord());
			foreignTextField.setEnabled(false);
		}

		translationPanel.add(germanLabel);
		translationPanel.add(germanTextField, new CC().width(":300:").pushX().growX());
		translationPanel.add(foreignLabel);
		translationPanel.add(foreignTextField, new CC().width(":300:").pushX().growX());
		
		TitledBorder border = new TitledBorder("Übersetzung");
		translationPanel.setBorder(border);

		return translationPanel;
	}
	
	private JPanel getCorrectnessPanel() {
		JPanel correctnessPanel = new JPanel(new MigLayout());
		correctnessField = new JTextField();
		correctnessField.setEnabled(false);
		correctnessPanel.add(correctnessField, new CC().width(":400:"));
		
		TitledBorder border = new TitledBorder("Korrektheitsprüfung");
		correctnessPanel.setBorder(border);
		
		return correctnessPanel;
	}
	
	private JPanel getButtonsPanel() {
		JPanel buttonPanel = new JPanel(new MigLayout(new LC().alignX("center")));
		
		JButton nextWordButton = new JButton("Überprüfen und nächste Abfrage");
		buttonPanel.add(nextWordButton);
		// DeutschFremdsprache
		if(inquiryDirection == InquiryDirection.GERMAN_TO_FOREIGN) {
			nextWordButton.addActionListener(new CheckWordPairFromGermanToForeignListener(germanTextField, foreignTextField, correctnessField, unpracticedWordsValueLabel, correctPracticesValueLabel, incorrectPracticesValueLabel, correctnessRateValueLabel, inquiryOrder, getStatistics()));
		}
		// FremdspracheDeutsch
		if(inquiryDirection == InquiryDirection.FOREIGN_TO_GERMAN) {
			nextWordButton.addActionListener(new CheckWordPairFromForeignToGermanListener(germanTextField, foreignTextField, correctnessField, unpracticedWordsValueLabel, correctPracticesValueLabel, incorrectPracticesValueLabel, correctnessRateValueLabel, inquiryOrder, getStatistics()));
		}
		
		JButton cancelButton = new JButton("Abbrechen");
		buttonPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inquiryOrder.resetIndex();
				dispose();
			}
		});

		return buttonPanel;
	}
	
	public WordList getWordList() {
		return wordList;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	public InquiryDirection getInquiryDirection() {
		return inquiryDirection;
	}

	public void setInquiryDirection(InquiryDirection inquiryDirection) {
		this.inquiryDirection = inquiryDirection;
	}

	public InquiryOrder getInquiryOrder() {
		return inquiryOrder;
	}

	public void setInquiryOrder(InquiryOrder inquiryOrder) {
		this.inquiryOrder = inquiryOrder;
	}

	public JLabel getWordsValueLable() {
		return wordsValueLable;
	}

	public void setWordsValueLable(JLabel wordsValueLable) {
		this.wordsValueLable = wordsValueLable;
	}

	public JLabel getUnpracticedWordsValueLabel() {
		return unpracticedWordsValueLabel;
	}

	public void setUnpracticedWordsValueLabel(JLabel unpracticedWordsValueLabel) {
		this.unpracticedWordsValueLabel = unpracticedWordsValueLabel;
	}

	public JLabel getCorrectPracticesValueLabel() {
		return correctPracticesValueLabel;
	}

	public void setCorrectPracticesValueLabel(JLabel correctPracticesValueLabel) {
		this.correctPracticesValueLabel = correctPracticesValueLabel;
	}

	public JLabel getIncorrectPracticesValueLabel() {
		return incorrectPracticesValueLabel;
	}

	public void setIncorrectPracticesValueLabel(JLabel incorrectPracticesValueLabel) {
		this.incorrectPracticesValueLabel = incorrectPracticesValueLabel;
	}

	public JLabel getCorrectnessRateValueLabel() {
		return correctnessRateValueLabel;
	}

	public void setCorrectnessRateValueLabel(JLabel correctnessRateValueLabel) {
		this.correctnessRateValueLabel = correctnessRateValueLabel;
	}

	public JTextField getGermanTextField() {
		return germanTextField;
	}

	public void setGermanTextField(JTextField germanTextField) {
		this.germanTextField = germanTextField;
	}

	public JTextField getForeignTextField() {
		return foreignTextField;
	}

	public void setForeignTextField(JTextField foreignTextField) {
		this.foreignTextField = foreignTextField;
	}

	public JTextField getCorrectnessField() {
		return correctnessField;
	}

	public void setCorrectnessField(JTextField correctnessField) {
		this.correctnessField = correctnessField;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setWordList(WordList wordList) {
		this.wordList = wordList;
	}

	
}
