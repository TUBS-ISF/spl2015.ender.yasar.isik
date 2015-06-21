package vocabularytrainer.persistence;

import javax.swing.filechooser.FileNameExtensionFilter;

import vocabularytrainer.core.WordList;

public interface FileExporter {
	
	public abstract void exportFile(WordList wordList, String filename);
	public abstract FileNameExtensionFilter getFileNameExtensionFilter();
	public abstract String getFileSuffix();
	
}
