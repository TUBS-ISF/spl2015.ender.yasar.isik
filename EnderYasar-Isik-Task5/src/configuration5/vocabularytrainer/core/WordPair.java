package vocabularytrainer.core; 

public  class  WordPair {
	
	
	private String germanWord;

	
	private String foreignWord;

	
	
	public WordPair() {
		
	}

	
	
	public WordPair(String germanWord, String foreignWord) {
		this.germanWord = germanWord;
		this.foreignWord = foreignWord;
	}

	

	public String getGermanWord() {
		return germanWord;
	}

	

	public void setGermanWord(String germanWord) {
		this.germanWord = germanWord;
	}

	

	public String getForeignWord() {
		return foreignWord;
	}

	

	public void setForeignWord(String foreignWord) {
		this.foreignWord = foreignWord;
	}


}
