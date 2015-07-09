package vocabularytrainer.core; 

import java.util.List; 
import java.util.ArrayList;; 

public  class  WordList {
	
	
	private List<WordPair> list;

	
	
	public WordList() {
		this.list = new ArrayList<WordPair>();
	}

	
	
	public List<WordPair> getWordList() {
		return list;
	}

	
	
	public void setWordList(List<WordPair> list) {
		this.list = list;
	}


}
