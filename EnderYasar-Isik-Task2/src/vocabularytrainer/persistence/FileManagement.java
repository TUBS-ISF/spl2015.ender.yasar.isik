package vocabularytrainer.persistence;

import java.util.Vector;

public class FileManagement {
	
	private FileType type;
	
	public FileManagement(FileType type) {
		this.type = type;
	}
	
	public Vector<Vector<String>> importFile(String filename) {
		if(type == FileType.XML) {
			XMLFileImporter xmlImporter = new XMLFileImporter(filename);
			return xmlImporter.importWordList();
		} else {
			return null;
		}
	}
	
	public void exportFile(Vector<Vector<String>> wordList, String filename) {
		if(type == FileType.XML) {
			XMLFileExporter xmlExporter = new XMLFileExporter(wordList, filename);
			xmlExporter.exportWordList();
		}
	}

}
