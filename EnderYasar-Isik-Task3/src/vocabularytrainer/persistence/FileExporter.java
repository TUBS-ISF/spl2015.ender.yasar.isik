//#ifdef Export
package vocabularytrainer.persistence;

import vocabularytrainer.core.WordList;

public interface FileExporter {
	
	public abstract void exportFile(WordList wordList, String filename);
	
}
//#endif
