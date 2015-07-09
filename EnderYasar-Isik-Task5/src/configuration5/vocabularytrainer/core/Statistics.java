package vocabularytrainer.core; 

public  interface  Statistics {
	
	
	public int getCorrectPractices();

	
	public int getIncorrectPractices();

	
	public int getNumberOfWords();

	
	public int getNumberOfPracticedWords();

	
	public float getCorrectnessRate();

	
	public void setNumberOfWords(int numberOfWords);

	
	public void incrementCorrectPractices();

	
	public void incrementIncorrectPractices();


}
