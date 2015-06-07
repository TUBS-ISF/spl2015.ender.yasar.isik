package vocabularytrainer.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;;

public class WordList {
	
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
	
	public List<WordPair> clone() {
		List<WordPair> clonedList = new ArrayList<WordPair>(list.size());
		for(WordPair wordPair : list) {
			clonedList.add(wordPair);
		}
		
		return clonedList;
	}
	
	public WordList getSortedClonedWordList() {
		List<WordPair> sortedList = clone();
		WordList newList = new WordList();
		
		Collections.sort(sortedList, new Comparator<WordPair>(){
		    public int compare(WordPair wordPair1, WordPair wordPair2) {
		        return wordPair1.getGermanWord().compareTo(wordPair2.getGermanWord());
		}});
	
		newList.setWordList(sortedList);
		
		return newList;
	}

}
