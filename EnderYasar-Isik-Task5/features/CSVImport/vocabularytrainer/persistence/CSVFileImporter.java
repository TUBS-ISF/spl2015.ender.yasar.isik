package vocabularytrainer.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.opencsv.CSVReader;

import vocabularytrainer.core.WordList;
import vocabularytrainer.core.WordPair;

public class CSVFileImporter implements FileImporter {

	public WordList importFile(String filename) {
		WordList wordList = null;
		
		try {
			CSVReader csvReader = new CSVReader(new FileReader(filename));
			wordList = convertListOfStringArraysToWordList(csvReader.readAll());
			csvReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return wordList;
	}
	
	private WordList convertListOfStringArraysToWordList(List<String[]> importedList) {
		WordList wordList = new WordList();
		
		for(String[] fileRow : importedList) {
			wordList.getWordList().add(new WordPair(fileRow[0], fileRow[1]));
		}
		
		return wordList;
	}

	public FileFilter getFileFilter() {
		FileFilter fileFilter = new FileNameExtensionFilter("CSV-Dateiformat", "csv");
		return fileFilter;
	}

	public String getFileSuffix() {
		return ".csv";
	}
	
	

}
