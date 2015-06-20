package vocabularytrainer.core;

public interface IStatistics {
	
	public abstract int getCorrectPractices();
	public abstract int getIncorrectPractices();
	public abstract int getNumberOfWords();
	public abstract int getNumberOfPracticedWords();
	public abstract float getCorrectnessRate();
	public abstract void incrementCorrectPractices();
	public abstract void incrementIncorrectPractices();
	
}
