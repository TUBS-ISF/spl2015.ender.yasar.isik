package vocabularytrainer.persistence;

import javax.swing.filechooser.FileNameExtensionFilter;

import vocabularytrainer.core.WordList;

public interface FileImporter {

	public abstract WordList importFile(String filename);
	public abstract FileNameExtensionFilter getFileNameExtensionFilter();

}
