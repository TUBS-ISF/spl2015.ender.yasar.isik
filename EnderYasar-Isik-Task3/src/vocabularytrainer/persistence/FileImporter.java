//#ifdef Import
package vocabularytrainer.persistence;

import vocabularytrainer.core.WordList;

public interface FileImporter {

	public abstract WordList importFile(String filename);

}
//#endif
