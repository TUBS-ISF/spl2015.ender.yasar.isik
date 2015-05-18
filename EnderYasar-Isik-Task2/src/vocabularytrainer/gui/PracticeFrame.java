package vocabularytrainer.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import vocabularytrainer.core.ProductLineConfiguration;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class PracticeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private int index = 0, numberOfCorrectPractices = 0, numberOfIncorrectPractices = 0;
	private Vector<Vector<String>> wordList;
	private InquiryType inquiryType;
	private InquiryOrder inquiryOrder;
	private InquiryDirection inquiryDirection;
	private JLabel numberOfWordsValueLabel, numberOfUnpracticedWordsValueLabel, numberOfCorrectAnswersValueLabel, numberOfIncorrectAnswersValueLabel;
	private JTextField germanTextField, foreignTextField, correctnessField;
	private JPanel panel;
	
	@SuppressWarnings("unchecked")
	public PracticeFrame(final Vector<Vector<String>> wordList, InquiryType inquiryType, InquiryOrder inquiryOrder, InquiryDirection inquiryDirection) {
		super();
		super.setTitle("Vokabeln üben");
		
		this.wordList = (Vector<Vector<String>>) wordList.clone();
		this.inquiryDirection = inquiryDirection;
		this.inquiryOrder = inquiryOrder;
		this.inquiryType = inquiryType;
		
		if(inquiryOrder == InquiryOrder.SORTED) {
			sortWordList();
		}

		panel = new JPanel(new MigLayout(new LC().wrap().alignX("center")));
		setTranslationPanel();
		setCorrectnessPanel();
		setButtonsPanel();
		
		if(ProductLineConfiguration.statistics) {
			setStatisticsPanel();
		}
		
		setContentPane(panel);
		setResizable(false);
	    setLocationRelativeTo(null);
		setVisible(true);
		pack();
	}
	
	private void setTranslationPanel() {
		JPanel translationPanel = new JPanel(new MigLayout(new LC().wrapAfter(2)));
		JLabel germanLabel = new JLabel("Deutsch");
		JLabel foreignLabel = new JLabel("Fremdsprache");
		germanTextField = new JTextField();
		foreignTextField = new JTextField();
		
		if(inquiryDirection == InquiryDirection.FOREIGN_TO_GERMAN) {
			foreignTextField.setText(wordList.get(0).elementAt(1));
			foreignTextField.setEnabled(false);
		} else if(inquiryDirection == InquiryDirection.GERMAN_TO_FOREIGN) {
			germanTextField.setText(wordList.get(0).elementAt(0));
			germanTextField.setEnabled(false);
		}
		
		
		germanTextField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkWordPair();
				}
			}
			
			public void keyPressed(KeyEvent e) {
			}
		});
		foreignTextField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkWordPair();
				}
			}
			
			public void keyPressed(KeyEvent e) {
			}
		});
		
		translationPanel.add(germanLabel);
		translationPanel.add(germanTextField, new CC().width(":300:").pushX());
		translationPanel.add(foreignLabel);
		translationPanel.add(foreignTextField, new CC().width(":300:").pushX());
		
		TitledBorder border = new TitledBorder("Übersetzung");
		translationPanel.setBorder(border);
		
		panel.add(translationPanel);
	}
	
	private void setCorrectnessPanel() {
		correctnessField = new JTextField();
		correctnessField.setEnabled(false);
		
		panel.add(correctnessField, new CC().grow().spanX());
	}
	
	private void setButtonsPanel() {
		JButton cancelButton = new JButton("Abbrechen");
		JButton nextWordButton = new JButton("Überprüfen und nächste Abfrage");
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		nextWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkWordPair();
			}
		});
		
		panel.add(nextWordButton, new CC().split().width(":150:").alignX("center"));
		panel.add(cancelButton, new CC().width(":150:").wrap());
	}
	
	private void setStatisticsPanel() {
		JPanel statisticsPanel = new JPanel(new MigLayout(new LC().wrapAfter(2)));
		JLabel numberOfWordsLabel = new JLabel("Gesamtanzahl Vokabeln:");
		numberOfWordsValueLabel = new JLabel(Integer.toString(wordList.size()));
		JLabel numberOfUnpracticedWordsLabel = new JLabel("Verbleibende Anzahl an Vokabeln:");
		numberOfUnpracticedWordsValueLabel = new JLabel(Integer.toString(wordList.size()));
		JLabel numberOfCorrectAnswersLabel = new JLabel("Anzahl richtiger Antworten:");
		numberOfCorrectAnswersValueLabel = new JLabel("0");
		JLabel numberOfIncorrectAnswersLabel = new JLabel("Anzahl falscher Antworten:");
		numberOfIncorrectAnswersValueLabel = new JLabel("0");
		
		statisticsPanel.add(numberOfWordsLabel);
		statisticsPanel.add(numberOfWordsValueLabel, new CC().pushX().alignX("right"));
		statisticsPanel.add(numberOfUnpracticedWordsLabel);
		statisticsPanel.add(numberOfUnpracticedWordsValueLabel, new CC().pushX().alignX("right"));
		statisticsPanel.add(numberOfCorrectAnswersLabel);
		statisticsPanel.add(numberOfCorrectAnswersValueLabel, new CC().pushX().alignX("right"));
		statisticsPanel.add(numberOfIncorrectAnswersLabel);
		statisticsPanel.add(numberOfIncorrectAnswersValueLabel, new CC().pushX().alignX("right"));
		
		TitledBorder border = new TitledBorder("Statistik");
		statisticsPanel.setBorder(border);

		panel.add(statisticsPanel, new CC().grow());
	}
	
	private void checkWordPair() {
		if(index == wordList.size()-1) {
			setVisible(false);
		} else {
			if(inquiryDirection == InquiryDirection.GERMAN_TO_FOREIGN) {
				checkWordPairFromGermanToForeign();
			} else if(inquiryDirection == InquiryDirection.FOREIGN_TO_GERMAN) {
				checkWordPairFromForeignToGerman();
			}
		}
	}
	
	private void sortWordList() {
		Collections.sort(wordList, new Comparator<Vector<String>>(){
		    public int compare(Vector<String> vector1, Vector<String> v2) {
		        return vector1.get(0).compareTo(v2.get(0));
		}});
	}
	
	private void checkWordPairFromGermanToForeign() {
		if(foreignTextField.getText().equals(wordList.get(index).elementAt(1))) {
			correctnessField.setBackground(Color.GREEN);
			if(ProductLineConfiguration.statistics) {
				numberOfCorrectPractices++;
				numberOfCorrectAnswersValueLabel.setText(Integer.toString(numberOfCorrectPractices));
			}
		} else {
			correctnessField.setBackground(Color.RED);
			if(ProductLineConfiguration.statistics) {
				numberOfIncorrectPractices++;
				numberOfIncorrectAnswersValueLabel.setText(Integer.toString(numberOfIncorrectPractices));
			}
		}
		index++;
		if(index < wordList.size()) {
			germanTextField.setText(wordList.get(index).elementAt(0));
			foreignTextField.setText("");
		}
		if(ProductLineConfiguration.statistics) {
			numberOfUnpracticedWordsValueLabel.setText(Integer.toString(wordList.size()-index));
		}
	}
	
	private void checkWordPairFromForeignToGerman() {
		if(germanTextField.getText().equals(wordList.get(index).elementAt(0))) {
			correctnessField.setBackground(Color.GREEN);
			if(ProductLineConfiguration.statistics) {
				numberOfCorrectPractices++;
				numberOfCorrectAnswersValueLabel.setText(Integer.toString(numberOfCorrectPractices));
			}
		} else {
			correctnessField.setBackground(Color.RED);
			if(ProductLineConfiguration.statistics) {
				numberOfIncorrectPractices++;
				numberOfIncorrectAnswersValueLabel.setText(Integer.toString(numberOfIncorrectPractices));
			}
		}
		index++;
		if(index < wordList.size()) {
			foreignTextField.setText(wordList.get(index).elementAt(1));
			germanTextField.setText("");
		}
		if(ProductLineConfiguration.statistics) {
			numberOfUnpracticedWordsValueLabel.setText(Integer.toString(wordList.size()-index));
		}
	}
}
