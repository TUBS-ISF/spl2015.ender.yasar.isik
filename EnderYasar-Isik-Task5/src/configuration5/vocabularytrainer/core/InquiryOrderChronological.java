package vocabularytrainer.core; 

public  class  InquiryOrderChronological  implements InquiryOrder {
	
	
	private int index;

	
	private WordList wordList;

	
	
	public InquiryOrderChronological() {
		index = 0;
		wordList = null;
	}

	
	
	public InquiryOrderChronological(WordList wordList) {
		index = 0;
		this.wordList = wordList;
	}

	
	
	public void setWordList(WordList wordList) {
		this.wordList = wordList;
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
		return "Chronologisch";
	}

	

	public void incrementIndex() {
		index++;
	}

	

	public void resetIndex() {
		index = 0;
	}


}
