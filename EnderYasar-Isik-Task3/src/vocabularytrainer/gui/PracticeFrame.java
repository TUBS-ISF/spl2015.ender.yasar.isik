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
import vocabularytrainer.core.WordList;
//#ifdef DeutschFremdsprache
import vocabularytrainer.gui.controller.CheckWordPairFromGermanToForeignListener;
//#endif
//#ifdef FremdspracheDeutsch
//@import vocabularytrainer.gui.controller.CheckWordPairFromForeignToGermanListener;
//#endif

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class PracticeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private WordList wordList;
	private InquiryDirection inquiryDirection;
	private JLabel wordsValueLable;
	private JLabel unpracticedWordsValueLabel;
	private JLabel correctPracticesValueLabel;
	private JLabel incorrectPracticesValueLabel;
	private JLabel correctnessRateValueLabel;
	private JTextField germanTextField;
	private JTextField foreignTextField;
	private JTextField correctnessField;
	private JPanel panel;
	
	public PracticeFrame(WordList wordList, InquiryOrder inquiryOrder, InquiryDirection inquiryDirection) {
		super();
		super.setTitle("Vokabeln üben");
		
		this.inquiryDirection = inquiryDirection;
		//#ifdef Chronologisch
		this.wordList = wordList;
		//#endif
		//#ifdef Sortiert
//@		if(inquiryOrder == InquiryOrder.SORTED) {
//@			this.wordList = wordList.getSortedClonedWordList();
//@		}
		//#endif

		panel = new JPanel(new MigLayout(new LC().wrap().alignX("center")));
		panel.add(getTranslationPanel(), new CC().growX());
		panel.add(getCorrectnessPanel(), new CC().growX());
		//#ifdef Statistik
//@		panel.add(getStatisticsPanel(), new CC().growX());
		//#endif
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
		
		//#ifdef DeutschFremdsprache
		if(inquiryDirection == InquiryDirection.GERMAN_TO_FOREIGN) {
			germanTextField.setText(wordList.getWordList().get(0).getGermanWord());
			germanTextField.setEnabled(false);
		}
		//#endif
		//#ifdef FremdspracheDeutsch
//@		if(inquiryDirection == InquiryDirection.FOREIGN_TO_GERMAN) {
//@			foreignTextField.setText(wordList.getWordList().get(0).getForeignWord());
//@			foreignTextField.setEnabled(false);
//@		}
		//#endif

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
	
	//#ifdef Statistik
//@	private JPanel getStatisticsPanel() {
//@		JPanel statisticsPanel = new JPanel(new MigLayout(new LC().wrapAfter(2)));
//@		JLabel wordsLabel = new JLabel("Gesamtanzahl Vokabeln:");
//@		wordsValueLable = new JLabel(Integer.toString(wordList.getWordList().size()));
//@		JLabel unpracticedWordsLabel = new JLabel("Verbleibende Anzahl an Vokabeln:");
//@		unpracticedWordsValueLabel = new JLabel(Integer.toString(wordList.getWordList().size()));
//@		JLabel correctPracticesLabel = new JLabel("Anzahl richtiger Antworten:");
//@		correctPracticesValueLabel = new JLabel("0");
//@		JLabel incorrectPracticesLabel = new JLabel("Anzahl falscher Antworten:");
//@		incorrectPracticesValueLabel = new JLabel("0");
//@		JLabel correctnessRateLabel = new JLabel("Bewertung:");
//@		correctnessRateValueLabel = new JLabel("100,00 %");
//@		
//@		statisticsPanel.add(wordsLabel);
//@		statisticsPanel.add(wordsValueLable, new CC().pushX().alignX("right"));
//@		statisticsPanel.add(unpracticedWordsLabel);
//@		statisticsPanel.add(unpracticedWordsValueLabel, new CC().pushX().alignX("right"));
//@		statisticsPanel.add(correctPracticesLabel);
//@		statisticsPanel.add(correctPracticesValueLabel, new CC().pushX().alignX("right"));
//@		statisticsPanel.add(incorrectPracticesLabel);
//@		statisticsPanel.add(incorrectPracticesValueLabel, new CC().pushX().alignX("right"));
//@		statisticsPanel.add(correctnessRateLabel);
//@		statisticsPanel.add(correctnessRateValueLabel, new CC().pushX().alignX("right"));
//@		
//@		TitledBorder border = new TitledBorder("Statistik");
//@		statisticsPanel.setBorder(border);
//@
//@		return statisticsPanel;
//@	}
	//#endif
	
	private JPanel getButtonsPanel() {
		JPanel buttonPanel = new JPanel(new MigLayout(new LC().alignX("center")));
		
		JButton nextWordButton = new JButton("Überprüfen und nächste Abfrage");
		buttonPanel.add(nextWordButton);
		//#ifdef DeutschFremdsprache
		if(inquiryDirection == InquiryDirection.GERMAN_TO_FOREIGN) {
			nextWordButton.addActionListener(new CheckWordPairFromGermanToForeignListener(wordList, germanTextField, foreignTextField, correctnessField, unpracticedWordsValueLabel, correctPracticesValueLabel, incorrectPracticesValueLabel, correctnessRateValueLabel));
		}
		//#endif
		//#ifdef FremdspracheDeutsch
//@		if(inquiryDirection == InquiryDirection.FOREIGN_TO_GERMAN) {
//@			nextWordButton.addActionListener(new CheckWordPairFromForeignToGermanListener(wordList, germanTextField, foreignTextField, correctnessField, unpracticedWordsValueLabel, correctPracticesValueLabel, incorrectPracticesValueLabel, correctnessRateValueLabel));
//@		}
		//#endif
		
		JButton cancelButton = new JButton("Abbrechen");
		buttonPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		return buttonPanel;
	}
}
