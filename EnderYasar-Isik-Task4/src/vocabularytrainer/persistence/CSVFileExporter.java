package vocabularytrainer.persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import vocabularytrainer.core.WordList;
import vocabularytrainer.core.WordPair;

public class CSVFileExporter implements FileExporter {

	public void exportFile(WordList wordList, String filename) {
		try {
			CSVWriter csvWriter = new CSVWriter(new FileWriter(filename));
			csvWriter.writeAll(convertWordListToListOfStringArrays(wordList));
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<String[]> convertWordListToListOfStringArrays(WordList wordList) {
		List<String[]> list = new ArrayList<String[]>();
		
		for(WordPair wordPair : wordList.getWordList()) {
			list.add(new String[] {wordPair.getGermanWord(), wordPair.getForeignWord()});
		}
		
		return list;
	}

}
