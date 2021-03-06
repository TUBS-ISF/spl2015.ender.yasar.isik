package vocabularytrainer.core;

public class RealStatistics implements Statistics {
	
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
