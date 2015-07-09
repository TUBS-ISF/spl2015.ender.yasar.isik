package vocabularytrainer.persistence;

import javax.swing.filechooser.FileFilter;

import vocabularytrainer.core.WordList;

public interface FileExporter {
	
	public void exportFile(WordList wordList, String filename);
	public FileFilter getFileFilter();
	public String getFileSuffix();
	
}
