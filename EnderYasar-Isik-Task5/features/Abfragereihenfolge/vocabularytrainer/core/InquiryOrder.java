package vocabularytrainer.core;

public interface InquiryOrder {
	
	public void setWordList(WordList wordList);
	public WordPair getNextWordPair();
	public int getIndex();
	public void incrementIndex();
	public void resetIndex();
	public boolean isEndOfListReached();
	public String getInquiryOrderLabel();

}
