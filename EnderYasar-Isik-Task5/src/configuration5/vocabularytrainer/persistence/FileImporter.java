package vocabularytrainer.persistence; 

import javax.swing.filechooser.FileFilter; 

import vocabularytrainer.core.WordList; 

public  interface  FileImporter {
	

	public WordList importFile(String filename);

	
	public FileFilter getFileFilter();

	
	public String getFileSuffix();


}
