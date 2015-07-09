package vocabularytrainer.core; 

import java.util.ArrayList; 
import java.util.Collections; 
import java.util.Comparator; 
import java.util.List; 

public  class  InquiryOrderSorted  implements InquiryOrder {
	
	
	private int index;

	
	private WordList wordList;

	
	
	public InquiryOrderSorted() {
		index = 0;
		wordList = null;
	}

	
	
	public InquiryOrderSorted(WordList wordList) {
		index = 0;
		this.wordList = sortClonedWordList(wordList);
	}

	

	public void setWordList(WordList wordList) {
		this.wordList = sortClonedWordList(wordList);
	}

	

	public WordPair getNextWordPair() {
		WordPair wordPair = wordList.getWordList().get(index);
		return wordPair;
	}

	

	public int getIndex() {
		return index;
	}

	
	
	public boolean isEndOfListReached() {
		if(index < wordList.getWordList().size()) {
			return false;
		} else {
			return true;
		}
	}

	

	public String getInquiryOrderLabel() {
		return "Sortiert";
	}

	
	
	private List<WordPair> cloneWordList(WordList wordList) {
		List<WordPair> clonedList = new ArrayList<WordPair>(wordList.getWordList().size());
		for(WordPair wordPair : wordList.getWordList()) {
			clonedList.add(wordPair);
		}
		
		return clonedList;
	}

	
	
	private WordList sortClonedWordList(WordList wordList) {
		List<WordPair> sortedList = cloneWordList(wordList);
		WordList newList = new WordList();
		
		Collections.sort(sortedList, new Comparator<WordPair>(){
		    public int compare(WordPair wordPair1, WordPair wordPair2) {
		        return wordPair1.getGermanWord().compareTo(wordPair2.getGermanWord());
		}});
	
		newList.setWordList(sortedList);
		
		return newList;
	}

	
	
	public void incrementIndex() {
		index++;
	}

	
	
	public void resetIndex() {
		index = 0;
	}


}
