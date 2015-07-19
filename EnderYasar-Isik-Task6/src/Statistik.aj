import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import vocabularytrainer.core.Statistics;
import vocabularytrainer.gui.PracticeFrame;

public aspect Statistik {
	
	before(PracticeFrame frame) : call(private JPanel vocabularytrainer.gui.PracticeFrame.getTranslationPanel()) && this(frame) {
		frame.setStatistics(new RealStatistics(frame.getWordList().getWordList().size()));
	}
	
	after(PracticeFrame frame) : execution(private JPanel vocabularytrainer.gui.PracticeFrame.getCorrectnessPanel()) && this(frame) {
		JPanel statisticsPanel = new JPanel(new MigLayout(new LC().wrapAfter(2)));
		JLabel wordsLabel = new JLabel("Gesamtanzahl Vokabeln:");
		frame.setWordsValueLable(new JLabel(Integer.toString(frame.getWordList().getWordList().size())));
		JLabel unpracticedWordsLabel = new JLabel("Verbleibende Anzahl an Vokabeln:");
		frame.setUnpracticedWordsValueLabel(new JLabel(Integer.toString(frame.getWordList().getWordList().size())));
		JLabel correctPracticesLabel = new JLabel("Anzahl richtiger Antworten:");
		frame.setCorrectPracticesValueLabel(new JLabel("0"));
		JLabel incorrectPracticesLabel = new JLabel("Anzahl falscher Antworten:");
		frame.setIncorrectPracticesValueLabel(new JLabel("0"));
		JLabel correctnessRateLabel = new JLabel("Bewertung:");
		frame.setCorrectnessRateValueLabel(new JLabel("100,00 %"));
		
		statisticsPanel.add(wordsLabel);
		statisticsPanel.add(frame.getWordsValueLable(), new CC().pushX().alignX("right"));
		statisticsPanel.add(unpracticedWordsLabel);
		statisticsPanel.add(frame.getUnpracticedWordsValueLabel(), new CC().pushX().alignX("right"));
		statisticsPanel.add(correctPracticesLabel);
		statisticsPanel.add(frame.getCorrectPracticesValueLabel(), new CC().pushX().alignX("right"));
		statisticsPanel.add(incorrectPracticesLabel);
		statisticsPanel.add(frame.getIncorrectPracticesValueLabel(), new CC().pushX().alignX("right"));
		statisticsPanel.add(correctnessRateLabel);
		statisticsPanel.add(frame.getCorrectnessRateValueLabel(), new CC().pushX().alignX("right"));
		
		TitledBorder border = new TitledBorder("Statistik");
		statisticsPanel.setBorder(border);
		
		frame.getPanel().add(statisticsPanel);
	}
	
	class RealStatistics implements Statistics {
		
		private int correctPractices;
		private int incorrectPractices;
		private int numberOfWords;
		private int numberOfPracticedWords;
		private float correctnessRate;

		public RealStatistics() {
			correctPractices = 0;
			incorrectPractices = 0;
			numberOfPracticedWords = 0;
			correctnessRate = 100;
		}
		
		public RealStatistics (int numberOfWords) {
			correctPractices = 0;
			incorrectPractices = 0;
			this.numberOfWords = numberOfWords;
			numberOfPracticedWords = 0;
			correctnessRate = 100;
		}

		public int getCorrectPractices() {
			return correctPractices;
		}
		
		public int getIncorrectPractices() {
			return incorrectPractices;
		}
		
		public int getNumberOfWords() {
			return numberOfWords;
		}

		public int getNumberOfPracticedWords() {
			return numberOfPracticedWords;
		}
		
		public float getCorrectnessRate() {
			return correctnessRate;
		}
		
		public void incrementCorrectPractices() {
			correctPractices++;
			incrementNumberOfPracticedWords();
			setCorrectnessRate();
		}

		public void incrementIncorrectPractices() {
			incorrectPractices++;
			incrementNumberOfPracticedWords();
			setCorrectnessRate();
		}
		
		private void setCorrectnessRate() {
			correctnessRate = ((float) correctPractices/(float) numberOfPracticedWords)*100;
		}
		
		private void incrementNumberOfPracticedWords() {
			numberOfPracticedWords++;
		}

		public void setNumberOfWords(int numberOfWords) {
			this.numberOfWords = numberOfWords;
		}
		
	}
	
}